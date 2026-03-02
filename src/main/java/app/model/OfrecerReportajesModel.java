package app.model;

import java.util.List;
import giis.demo.util.Database;
import app.dto.OfrecerReportajesDTO;

public class OfrecerReportajesModel {

    private Database db = new Database();

    /**
     * Obtiene los eventos que tienen reportero asignado, 
     * filtrados por la AGENCIA seleccionada. (Sin cambios)
     */
    public List<OfrecerReportajesDTO> getEventosConReportero(String nombreAgencia) {
        String sql = "SELECT e.id_evento, e.descripcion AS nombre_evento, r.nombre AS reportero_asignado " +
                     "FROM Evento e " +
                     "JOIN Asignacion a ON e.id_evento = a.id_evento " +
                     "JOIN Reportero r ON a.id_reportero = r.id_reportero " +
                     "JOIN Agencia ag ON e.id_agencia = ag.id_agencia " + 
                     "WHERE ag.nombre = ?"; 
        
        return db.executeQueryPojo(OfrecerReportajesDTO.class, sql, nombreAgencia);
    }

    /**
     * OPCIÓN 1 FILTRO: Empresas que aún NO han recibido oferta. (Sin cambios)
     */
    public List<OfrecerReportajesDTO> getEmpresasSinOferta(int idEvento) {
        String sql = "SELECT id_empresa, nombre AS nombre_empresa " + 
                     "FROM Empresa_Comunicacion " +
                     "WHERE id_empresa NOT IN (" +
                     "  SELECT id_empresa FROM Ofrecimiento WHERE id_evento = ?" +
                     ")";
        return db.executeQueryPojo(OfrecerReportajesDTO.class, sql, idEvento);
    }

    /**
     * OPCIÓN 2 FILTRO: Empresas a las que SÍ se les ha ofrecido el reportaje.
     * Requisito HU #33531.
     */
    public List<OfrecerReportajesDTO> getEmpresasConOferta(int idEvento) {
        // Obtenemos empresa, estado y el flag de acceso para validar borrado
        String sql = "SELECT e.id_empresa, e.nombre AS nombre_empresa, o.estado, o.tiene_acceso " +
                     "FROM Empresa_Comunicacion e " +
                     "JOIN Ofrecimiento o ON e.id_empresa = o.id_empresa " +
                     "WHERE o.id_evento = ?";
        return db.executeQueryPojo(OfrecerReportajesDTO.class, sql, idEvento);
    }

    /**
     * Obtiene el detalle de un ofrecimiento específico y el email de la empresa.
     * Necesario para validar acceso y enviar notificaciones.
     */
    public OfrecerReportajesDTO getDetalleOfrecimiento(int idEvento, int idEmpresa) {
        String sql = "SELECT o.id_evento, o.id_empresa, o.estado, o.tiene_acceso, e.email " +
                     "FROM Ofrecimiento o " +
                     "JOIN Empresa_Comunicacion e ON o.id_empresa = e.id_empresa " +
                     "WHERE o.id_evento = ? AND o.id_empresa = ?";
        List<OfrecerReportajesDTO> res = db.executeQueryPojo(OfrecerReportajesDTO.class, sql, idEvento, idEmpresa);
        return res.isEmpty() ? null : res.get(0);
    }

    /**
     * Inserta un nuevo ofrecimiento. (Sin cambios)
     */
    public void insertarOfrecimientos(int idEvento, int idEmpresa) {
        String sql = "INSERT INTO Ofrecimiento (id_evento, id_empresa, estado, tiene_acceso) VALUES (?, ?, 'PENDIENTE', 0)";
        db.executeUpdate(sql, idEvento, idEmpresa);
    }

    /**
     * Elimina físicamente el ofrecimiento de la base de datos.
     * Se llamará desde el controlador tras validar las reglas de negocio.
     */
    public void eliminarOfrecimiento(int idEvento, int idEmpresa) {
        String sql = "DELETE FROM Ofrecimiento WHERE id_evento = ? AND id_empresa = ?";
        db.executeUpdate(sql, idEvento, idEmpresa);
    }
}