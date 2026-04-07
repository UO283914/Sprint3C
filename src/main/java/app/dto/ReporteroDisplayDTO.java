package app.dto;

public class ReporteroDisplayDTO {
	private Integer idReportero;
	private String nombre;
	private String tematicas;
	private String tipoReportero;
	private Integer esResponsable;
	private String estadoAsignacion;

	public Integer getIdReportero() { return idReportero; }
	public void setIdReportero(Integer idReportero) { this.idReportero = idReportero; }
	public void setId_reportero(Integer id_reportero) { this.idReportero = id_reportero; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public String getTematicas() { return tematicas; }
	public void setTematicas(String tematicas) { this.tematicas = tematicas; }

	public String getTipoReportero() { return tipoReportero; }
	public void setTipoReportero(String tipoReportero) { this.tipoReportero = tipoReportero; }
	public void setTipo_reportero(String tipo_reportero) { this.tipoReportero = tipo_reportero; }

	public Integer getEsResponsable() { return esResponsable; }
	public void setEsResponsable(Integer esResponsable) { this.esResponsable = esResponsable; }
	public void setEs_responsable(Integer es_responsable) { this.esResponsable = es_responsable; }

	public String getEstadoAsignacion() { return estadoAsignacion; }
	public void setEstadoAsignacion(String estadoAsignacion) { this.estadoAsignacion = estadoAsignacion; }
	public void setEstado_asignacion(String estado_asignacion) { this.estadoAsignacion = estado_asignacion; }
}
