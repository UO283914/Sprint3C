package app.model;

import giis.demo.util.Database;
import java.util.List;
import app.dto.EventoDisplayDTO;
import app.dto.ReporteroDisplayDTO;

public class AsignacionReporterosModel {

	private Database db = new Database();

	// 1. Eventos SIN asignar, incluyendo sus temáticas
	public List<EventoDisplayDTO> getEventosSinAsignar(String nombreAgencia) {
		String sql = "SELECT e.id_evento, e.descripcion, e.fecha, "
				+ "COALESCE(GROUP_CONCAT(DISTINCT t.nombre), 'Sin temática') AS tematicas "
				+ "FROM Evento e "
				+ "JOIN Agencia a ON e.id_agencia = a.id_agencia "
				+ "LEFT JOIN Evento_Tematica et ON et.id_evento = e.id_evento "
				+ "LEFT JOIN Tematica t ON t.id_tematica = et.id_tematica "
				+ "WHERE a.nombre = ? "
				+ "AND (e.fecha_inicio IS NULL OR e.fecha_fin IS NULL OR e.fecha_inicio = e.fecha_fin) "
				+ "AND NOT EXISTS ("
				+ "   SELECT 1 FROM Asignacion asig WHERE asig.id_evento = e.id_evento"
				+ ") "
				+ "GROUP BY e.id_evento, e.descripcion, e.fecha";
		return db.executeQueryPojo(EventoDisplayDTO.class, sql, nombreAgencia);
	}

	// 2. Eventos CON reporteros asignados, incluyendo sus temáticas
	public List<EventoDisplayDTO> getEventosConAsignacion(String nombreAgencia) {
		String sql = "SELECT e.id_evento, e.descripcion, e.fecha, "
				+ "COALESCE(GROUP_CONCAT(DISTINCT t.nombre), 'Sin temática') AS tematicas "
				+ "FROM Evento e "
				+ "JOIN Agencia a ON e.id_agencia = a.id_agencia "
				+ "JOIN Asignacion asig ON e.id_evento = asig.id_evento "
				+ "LEFT JOIN Evento_Tematica et ON et.id_evento = e.id_evento "
				+ "LEFT JOIN Tematica t ON t.id_tematica = et.id_tematica "
				+ "WHERE a.nombre = ? "
				+ "AND (e.fecha_inicio IS NULL OR e.fecha_fin IS NULL OR e.fecha_inicio = e.fecha_fin) "
				+ "GROUP BY e.id_evento, e.descripcion, e.fecha";
		return db.executeQueryPojo(EventoDisplayDTO.class, sql, nombreAgencia);
	}

	// 3. Reporteros disponibles en la fecha, opcionalmente filtrados por temática del evento
	public List<ReporteroDisplayDTO> getReporterosDisponibles(String fechaEvento, String nombreAgencia,
			Integer idEvento, boolean soloEspecializados, String tipoReportero) {
		StringBuilder sql = new StringBuilder(
				"SELECT r.id_reportero, r.nombre, UPPER(COALESCE(r.tipo_reportero, 'BASE')) AS tipo_reportero, "
				+ "COALESCE(GROUP_CONCAT(DISTINCT t.nombre), 'Sin especialización') AS tematicas "
				+ "FROM Reportero r "
				+ "JOIN Agencia a ON r.id_agencia = a.id_agencia "
				+ "LEFT JOIN Reportero_Tematica rt ON rt.id_reportero = r.id_reportero "
				+ "LEFT JOIN Tematica t ON t.id_tematica = rt.id_tematica "
				+ "WHERE a.nombre = ? AND COALESCE(r.es_freelance, 0) = 0 AND NOT EXISTS ("
				+ "   SELECT 1 FROM Asignacion asig "
				+ "   JOIN Evento e ON asig.id_evento = e.id_evento "
				+ "   WHERE e.fecha = ? AND asig.id_reportero = r.id_reportero"
				+ ") ");

		boolean filtrarPorTipo = tipoReportero != null && !"TODOS".equalsIgnoreCase(tipoReportero);
		if (filtrarPorTipo) {
			sql.append("AND UPPER(COALESCE(r.tipo_reportero, 'BASE')) = UPPER(?) ");
		}

		if (soloEspecializados) {
			sql.append("AND EXISTS ("
					+ "   SELECT 1 FROM Reportero_Tematica rt2 "
					+ "   JOIN Evento_Tematica et2 ON rt2.id_tematica = et2.id_tematica "
					+ "   WHERE rt2.id_reportero = r.id_reportero AND et2.id_evento = ?"
					+ ") ");
		}

		sql.append("GROUP BY r.id_reportero, r.nombre, r.tipo_reportero");

		if (soloEspecializados) {
			if (filtrarPorTipo) {
				return db.executeQueryPojo(ReporteroDisplayDTO.class, sql.toString(), nombreAgencia, fechaEvento,
						tipoReportero, idEvento);
			}
			return db.executeQueryPojo(ReporteroDisplayDTO.class, sql.toString(), nombreAgencia, fechaEvento, idEvento);
		}
		if (filtrarPorTipo) {
			return db.executeQueryPojo(ReporteroDisplayDTO.class, sql.toString(), nombreAgencia, fechaEvento, tipoReportero);
		}
		return db.executeQueryPojo(ReporteroDisplayDTO.class, sql.toString(), nombreAgencia, fechaEvento);
	}

	// 4. Reporteros que YA están asignados a este evento
	public List<ReporteroDisplayDTO> getReporterosAsignados(Integer idEvento) {
		String sql = "SELECT r.id_reportero, r.nombre, UPPER(COALESCE(r.tipo_reportero, 'BASE')) AS tipo_reportero, "
				+ "COALESCE(a.es_responsable, 0) AS es_responsable, "
				+ "COALESCE(a.estado_asignacion, 'ABIERTA') AS estado_asignacion, "
				+ "COALESCE(GROUP_CONCAT(DISTINCT t.nombre), 'Sin especialización') AS tematicas "
				+ "FROM Reportero r "
				+ "JOIN Asignacion a ON r.id_reportero = a.id_reportero "
				+ "LEFT JOIN Reportero_Tematica rt ON rt.id_reportero = r.id_reportero "
				+ "LEFT JOIN Tematica t ON t.id_tematica = rt.id_tematica "
				+ "WHERE a.id_evento = ? AND COALESCE(r.es_freelance, 0) = 0 "
				+ "GROUP BY r.id_reportero, r.nombre, r.tipo_reportero, a.es_responsable, a.estado_asignacion";
		return db.executeQueryPojo(ReporteroDisplayDTO.class, sql, idEvento);
	}

	public void eliminarAsignacionesPorEvento(Integer idEvento) {
		String sql = "DELETE FROM Asignacion WHERE id_evento = ?";
		db.executeUpdate(sql, idEvento);
	}

	public void guardarAsignacion(Integer idEvento, Integer idReportero) {
		guardarAsignacion(idEvento, idReportero, false, "ABIERTA");
	}

	public void guardarAsignacion(Integer idEvento, Integer idReportero, boolean esResponsable, String estadoAsignacion) {
		if (idEvento == null || idReportero == null) {
			throw new giis.demo.util.ApplicationException("Error interno: El ID del evento o reportero está llegando vacío.");
		}
		String sql = "INSERT INTO Asignacion (id_evento, id_reportero, es_responsable, estado_asignacion, fecha_fin_asignacion) "
				+ "VALUES (?, ?, ?, ?, CASE WHEN ? = 'FINALIZADA' THEN CURRENT_TIMESTAMP ELSE NULL END)";
		db.executeUpdate(sql, idEvento, idReportero, esResponsable ? 1 : 0, estadoAsignacion, estadoAsignacion);
	}

	public boolean isAsignacionFinalizada(Integer idEvento) {
		String sql = "SELECT COUNT(*) FROM Asignacion WHERE id_evento = ? AND estado_asignacion = 'FINALIZADA'";
		List<Object[]> rows = db.executeQueryArray(sql, idEvento);
		return rows != null && !rows.isEmpty() && ((Number) rows.get(0)[0]).intValue() > 0;
	}
}
