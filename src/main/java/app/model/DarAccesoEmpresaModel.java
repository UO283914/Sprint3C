package app.model;

import giis.demo.util.Database;
import app.dto.EventoDisplayDTO;
import app.dto.EmpresaDisplayDTO;
import java.util.List;


public class DarAccesoEmpresaModel {

	private Database db = new Database();

	// 1. Eventos de la agencia que SÍ tienen reportaje entregado
	public List<EventoDisplayDTO> getEventosConReportaje(String nombreAgencia) {
		String sql = "SELECT e.id_evento, e.descripcion, e.fecha, "
				+ "COALESCE(GROUP_CONCAT(DISTINCT t.nombre), 'Sin temática') AS tematicas "
				+ "FROM Evento e "
				+ "JOIN Agencia a ON e.id_agencia = a.id_agencia "
				+ "JOIN Reportaje r ON e.id_evento = r.id_evento "
				+ "LEFT JOIN Evento_Tematica et ON e.id_evento = et.id_evento "
				+ "LEFT JOIN Tematica t ON et.id_tematica = t.id_tematica "
				+ "WHERE a.nombre = ? "
				+ "GROUP BY e.id_evento, e.descripcion, e.fecha";
		return db.executeQueryPojo(EventoDisplayDTO.class, sql, nombreAgencia);
	}

	public List<EmpresaDisplayDTO> getEmpresasAceptadasByAcceso(Integer idEvento, boolean conAcceso) {
		String sql = "SELECT emp.id_empresa, emp.nombre, o.tiene_acceso AS tieneAcceso, o.descargado "
				+ "FROM Empresa_Comunicacion emp "
				+ "JOIN Ofrecimiento o ON emp.id_empresa = o.id_empresa "
				+ "WHERE o.id_evento = ? AND o.estado = 'ACEPTADO' AND o.tiene_acceso = ?";
		return db.executeQueryPojo(EmpresaDisplayDTO.class, sql, idEvento, conAcceso ? 1 : 0);
	}

	public void concederAcceso(Integer idEvento, Integer idEmpresa) {
		if (idEvento == null || idEmpresa == null) {
			throw new giis.demo.util.ApplicationException("Error interno: Los IDs están vacíos.");
		}
		String sql = "UPDATE Ofrecimiento SET tiene_acceso = 1 WHERE id_evento = ? AND id_empresa = ?";
		db.executeUpdate(sql, idEvento, idEmpresa);
	}

	public void revocarAcceso(Integer idEvento, Integer idEmpresa) {
		if (idEvento == null || idEmpresa == null) {
			throw new giis.demo.util.ApplicationException("Error interno: Los IDs están vacíos.");
		}
		String sql = "UPDATE Ofrecimiento SET tiene_acceso = 0 WHERE id_evento = ? AND id_empresa = ? AND descargado = 0";
		db.executeUpdate(sql, idEvento, idEmpresa);
	}
}
