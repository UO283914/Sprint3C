package app.model;

import java.util.List;

import app.dto.AppMainDTO;
import giis.demo.util.Database;
import giis.demo.util.DbUtil;

public class AppMainModel {
    
    private Database db = new Database();

    public DbUtil getDbUtil() {
        return db;
    }

    // Obtiene todos los nombres de los reporteros mapeados al DTO
    public List<AppMainDTO> getListaReporteros() {
        String sql = "SELECT nombre FROM Reportero "
                + "WHERE tipo_reportero IS NULL OR UPPER(tipo_reportero)='BASE' "
                + "ORDER BY nombre";
        return db.executeQueryPojo(AppMainDTO.class, sql);
    }

    // Obtiene todos los nombres de las agencias mapeados al DTO
    public List<AppMainDTO> getListaAgencias() {
        String sql = "SELECT nombre FROM Agencia ORDER BY nombre";
        return db.executeQueryPojo(AppMainDTO.class, sql);
    }

    // Obtiene todos los nombres de las empresas mapeados al DTO
    public List<AppMainDTO> getListaEmpresas() {
        String sql = "SELECT nombre FROM Empresa_Comunicacion ORDER BY nombre";
        return db.executeQueryPojo(AppMainDTO.class, sql);
    }
}
