package app.model;

import java.util.List;

import app.dto.OfrecerReportajesDTO;
import giis.demo.util.Database;

public class OfrecerReportajesModel {

    private Database db = new Database();

    /**
     * Obtiene los eventos con reportero asignado y sus tematicas.
     */
    public List<OfrecerReportajesDTO> getEventosConReportero(String nombreAgencia) {
        String sql = "SELECT e.id_evento, e.descripcion AS nombre_evento, "
                + "COALESCE(GROUP_CONCAT(DISTINCT r.nombre), 'Sin reportero') AS reportero_asignado, "
                + "COALESCE(GROUP_CONCAT(DISTINCT t.nombre), 'Sin tematica') AS tematicas_evento "
                + "FROM Evento e "
                + "JOIN Asignacion a ON e.id_evento = a.id_evento "
                + "JOIN Reportero r ON a.id_reportero = r.id_reportero "
                + "JOIN Agencia ag ON e.id_agencia = ag.id_agencia "
                + "LEFT JOIN Evento_Tematica et ON et.id_evento = e.id_evento "
                + "LEFT JOIN Tematica t ON t.id_tematica = et.id_tematica "
                + "WHERE ag.nombre = ? "
                + "GROUP BY e.id_evento, e.descripcion";

        return db.executeQueryPojo(OfrecerReportajesDTO.class, sql, nombreAgencia);
    }

    /**
     * Empresas candidatas sin oferta. Si se activa el filtro de especializacion,
     * solo se muestran las que comparten alguna tematica con el evento.
     */
    public List<OfrecerReportajesDTO> getEmpresasSinOferta(int idEvento, boolean soloCoincidentes) {
        String sql = "SELECT DISTINCT ec.id_empresa, ec.nombre AS nombre_empresa "
                + "FROM Empresa_Comunicacion ec ";

        if (soloCoincidentes) {
            sql += "JOIN Empresa_Tematica ect ON ec.id_empresa = ect.id_empresa "
                    + "JOIN Evento_Tematica et ON et.id_tematica = ect.id_tematica AND et.id_evento = ? ";
        }

        sql += "WHERE ec.id_empresa NOT IN ("
                + "  SELECT id_empresa FROM Ofrecimiento WHERE id_evento = ?"
                + ") "
                + "ORDER BY ec.nombre";

        if (soloCoincidentes) {
            return db.executeQueryPojo(OfrecerReportajesDTO.class, sql, idEvento, idEvento);
        }
        return db.executeQueryPojo(OfrecerReportajesDTO.class, sql, idEvento);
    }

    /**
     * Empresas a las que ya se ha ofrecido el reportaje.
     */
    public List<OfrecerReportajesDTO> getEmpresasConOferta(int idEvento) {
        String sql = "SELECT e.id_empresa, e.nombre AS nombre_empresa, o.estado, o.tiene_acceso "
                + "FROM Empresa_Comunicacion e "
                + "JOIN Ofrecimiento o ON e.id_empresa = o.id_empresa "
                + "WHERE o.id_evento = ? "
                + "ORDER BY e.nombre";
        return db.executeQueryPojo(OfrecerReportajesDTO.class, sql, idEvento);
    }

    public OfrecerReportajesDTO getDetalleOfrecimiento(int idEvento, int idEmpresa) {
        String sql = "SELECT o.id_evento, o.id_empresa, o.estado, o.tiene_acceso, e.email "
                + "FROM Ofrecimiento o "
                + "JOIN Empresa_Comunicacion e ON o.id_empresa = e.id_empresa "
                + "WHERE o.id_evento = ? AND o.id_empresa = ?";
        List<OfrecerReportajesDTO> res = db.executeQueryPojo(OfrecerReportajesDTO.class, sql, idEvento, idEmpresa);
        return res.isEmpty() ? null : res.get(0);
    }

    public void insertarOfrecimientos(int idEvento, int idEmpresa) {
        String sql = "INSERT INTO Ofrecimiento (id_evento, id_empresa, estado, tiene_acceso) "
                + "VALUES (?, ?, 'PENDIENTE', 0)";
        db.executeUpdate(sql, idEvento, idEmpresa);
    }

    public void eliminarOfrecimiento(int idEvento, int idEmpresa) {
        String sql = "DELETE FROM Ofrecimiento WHERE id_evento = ? AND id_empresa = ?";
        db.executeUpdate(sql, idEvento, idEmpresa);
    }
}
