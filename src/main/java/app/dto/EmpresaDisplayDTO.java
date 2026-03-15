package app.dto;

public class EmpresaDisplayDTO {
	private Integer idEmpresa;
	private String nombre;
	private Integer tieneAcceso;
	private Integer descargado;
	private String acceso;
	private String descarga;

	public Integer getIdEmpresa() { return idEmpresa; }
	public void setIdEmpresa(Integer idEmpresa) { this.idEmpresa = idEmpresa; }

	// TRUCO: Puerta secreta para SQLite
	public void setId_empresa(Integer id_empresa) { this.idEmpresa = id_empresa; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public Integer getTieneAcceso() { return tieneAcceso; }
	public void setTieneAcceso(Integer tieneAcceso) {
		this.tieneAcceso = tieneAcceso;
		this.acceso = (tieneAcceso != null && tieneAcceso == 1) ? "SÍ" : "NO";
	}

	public Integer getDescargado() { return descargado; }
	public void setDescargado(Integer descargado) {
		this.descargado = descargado;
		this.descarga = (descargado != null && descargado == 1) ? "DESCARGADO" : "NO DESCARGADO";
	}

	public String getAcceso() { return acceso; }
	public String getDescarga() { return descarga; }
}
