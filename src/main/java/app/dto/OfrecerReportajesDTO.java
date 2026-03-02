package app.dto;

public class OfrecerReportajesDTO {
    // Atributos de los Eventos
    private int id_evento;
    private String nombre_evento;
    private String reportero_asignado;
    
    // Atributos de las Empresas
    private int id_empresa;
    private String nombre_empresa;
    private String email; // Nuevo: Para notificaciones HU #33531
    
    // Atributos del Ofrecimiento
    private String estado; // Nuevo: PENDIENTE, ACEPTADO, RECHAZADO
    private int tiene_acceso; // Nuevo: 0 o 1 (SQLite)

    // Constructor vacío obligatorio
    public OfrecerReportajesDTO() {}

    // Getters y Setters
    public int getId_evento() { return id_evento; }
    public void setId_evento(int id_evento) { this.id_evento = id_evento; }

    public String getNombre_evento() { return nombre_evento; }
    public void setNombre_evento(String nombre_evento) { this.nombre_evento = nombre_evento; }

    public String getReportero_asignado() { return reportero_asignado; }
    public void setReportero_asignado(String reportero_asignado) { this.reportero_asignado = reportero_asignado; }

    public int getId_empresa() { return id_empresa; }
    public void setId_empresa(int id_empresa) { this.id_empresa = id_empresa; }

    public String getNombre_empresa() { return nombre_empresa; }
    public void setNombre_empresa(String nombre_empresa) { this.nombre_empresa = nombre_empresa; }

    // Nuevos métodos para la HU #33531
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getTiene_acceso() { return tiene_acceso; }
    public void setTiene_acceso(int tiene_acceso) { this.tiene_acceso = tiene_acceso; }

    @Override
    public String toString() {
        // En la lista de ofertas en curso, mostramos nombre y estado entre paréntesis
        if (estado != null) {
            return nombre_empresa + " (" + estado.substring(0,1) + ")";
        }
        return nombre_empresa;
    }
}