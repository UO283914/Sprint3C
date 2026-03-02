package app.controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import app.model.OfrecerReportajesModel;
import app.view.OfrecerReportajesView;
import app.dto.OfrecerReportajesDTO;
import giis.demo.util.SwingUtil;

public class OfrecerReportajesController {

	private OfrecerReportajesModel model;
	private OfrecerReportajesView view;
	private String agenciaActual;

	public OfrecerReportajesController(OfrecerReportajesModel m, OfrecerReportajesView v, String agencia) {
		this.model = m;
		this.view = v;
		this.agenciaActual = agencia;
		this.initView();
	}

	public void initView() {
		cargarEventos();

		// Listener: Al seleccionar un evento, actualizamos las tablas de empresas
		view.tableEventos.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				actualizarTablasDependientes();
			}
		});

		// Listener: Al cambiar el filtro (Sin oferta / Con oferta)
		view.comboFiltroEmpresas.addActionListener(e -> actualizarTablasDependientes());

		// Botón OFERTAR (Lógica HU #33526)
		view.btnOfertar.addActionListener(e -> ejecutarOferta());

		// Botón QUITAR OFRECIMIENTO (Lógica Nueva HU #33531)
		view.btnQuitarOfrecimiento.addActionListener(e -> ejecutarQuitarOfrecimiento());

		// Botones de utilidad
		view.btnCancelar.addActionListener(e -> view.dispose());
		view.btnLimpiarSeleccion.addActionListener(e -> {
			view.tableEventos.clearSelection();
			limpiarTablas();
		});

		view.setVisible(true);
	}

	private void actualizarTablasDependientes() {
		int fila = view.tableEventos.getSelectedRow();
		if (fila != -1) {
			int idEvento = (int) view.tableEventos.getValueAt(fila, 0);
			cargarEmpresasSegunFiltro(idEvento);
			cargarOfertasEnCurso(idEvento);
		} else {
			limpiarTablas();
		}
	}

	private void cargarEmpresasSegunFiltro(int idEvento) {
		int filtro = view.comboFiltroEmpresas.getSelectedIndex();
		List<OfrecerReportajesDTO> empresas;
		
		if (filtro == 0) { // OPCIÓN: "Ver empresas SIN OFERTA"
			empresas = model.getEmpresasSinOferta(idEvento);
			configurarTablaConChecks(empresas);
			view.btnOfertar.setEnabled(true);
			view.btnQuitarOfrecimiento.setEnabled(false);
		} else { // OPCIÓN: "Ver empresas YA OFERTADAS"
			empresas = model.getEmpresasConOferta(idEvento);
			String[] columnas = {"id_empresa", "nombre_empresa", "estado"};
			view.tableEmpresas.setModel(SwingUtil.getTableModelFromPojos(empresas, columnas));
			view.btnOfertar.setEnabled(false);
			view.btnQuitarOfrecimiento.setEnabled(true);
		}
		SwingUtil.autoAdjustColumns(view.tableEmpresas);
	}

	private void ejecutarQuitarOfrecimiento() {
		int filaEvento = view.tableEventos.getSelectedRow();
		int filaEmpresa = view.tableEmpresas.getSelectedRow();
		
		if (filaEmpresa == -1) {
			SwingUtil.showMessage("Seleccione una empresa de la lista central para quitar el ofrecimiento.", "Aviso", 1);
			return;
		}

		int idEvento = (int) view.tableEventos.getValueAt(filaEvento, 0);
		int idEmpresa = (int) view.tableEmpresas.getValueAt(filaEmpresa, 0);

		// 1. Validar reglas de la HU #33531
		OfrecerReportajesDTO detalle = model.getDetalleOfrecimiento(idEvento, idEmpresa);

		// Regla: No se puede quitar si ya tiene acceso (1 en SQLite)
		if (detalle.getTiene_acceso() == 1) {
			SwingUtil.showMessage("No se puede quitar el ofrecimiento: La empresa ya tiene acceso concedido.", "Error de Seguridad", 0);
			return;
		}

		// Regla: Si está ACEPTADO, enviar notificación por email
		if ("ACEPTADO".equals(detalle.getEstado())) {
			enviarEmailNotificacion(detalle.getEmail(), detalle.getNombre_empresa());
		}

		// 2. Ejecutar borrado
		model.eliminarOfrecimiento(idEvento, idEmpresa);
		SwingUtil.showMessage("Ofrecimiento retirado con éxito.", "Información", 1);
		actualizarTablasDependientes();
	}

	private void enviarEmailNotificacion(String email, String empresa) {
		// Simulación de envío de email según requisito
		System.out.println("LOG: Enviando email a " + email + " notificando la cancelación del reportaje para " + empresa);
		SwingUtil.showMessage("Se ha enviado una notificación automática a: " + email, "Email enviado", 1);
	}

	// --- MÉTODOS DE APOYO (Carga de datos y tablas) ---

	private void cargarEventos() {
		List<OfrecerReportajesDTO> eventos = model.getEventosConReportero(agenciaActual);
		String[] columnas = {"id_evento", "nombre_evento", "reportero_asignado"};
		view.tableEventos.setModel(SwingUtil.getTableModelFromPojos(eventos, columnas));
	}

	private void cargarOfertasEnCurso(int idEvento) {
		List<OfrecerReportajesDTO> ofertas = model.getEmpresasConOferta(idEvento);
		String[] columnas = {"nombre_empresa", "estado"};
		view.tableOfertasEnCurso.setModel(SwingUtil.getTableModelFromPojos(ofertas, columnas));
	}

	private void ejecutarOferta() {
		int filaEvento = view.tableEventos.getSelectedRow();
		int idEvento = (int) view.tableEventos.getValueAt(filaEvento, 0);
		for (int i = 0; i < view.tableEmpresas.getRowCount(); i++) {
			Boolean check = (Boolean) view.tableEmpresas.getValueAt(i, 2);
			if (check != null && check) {
				model.insertarOfrecimientos(idEvento, (int) view.tableEmpresas.getValueAt(i, 0));
			}
		}
		actualizarTablasDependientes();
	}

	private void configurarTablaConChecks(List<OfrecerReportajesDTO> empresas) {
		String[] columnas = {"id_empresa", "nombre_empresa"};
		DefaultTableModel tableModel = (DefaultTableModel) SwingUtil.getTableModelFromPojos(empresas, columnas);
		tableModel.addColumn("Seleccionar");
		view.tableEmpresas.setModel(new DefaultTableModel() {
			@Override public int getRowCount() { return tableModel.getRowCount(); }
			@Override public int getColumnCount() { return tableModel.getColumnCount(); }
			@Override public Object getValueAt(int r, int c) { return tableModel.getValueAt(r, c); }
			@Override public void setValueAt(Object v, int r, int c) { tableModel.setValueAt(v, r, c); fireTableCellUpdated(r, c); }
			@Override public Class<?> getColumnClass(int c) { return c == 2 ? Boolean.class : Object.class; }
			@Override public boolean isCellEditable(int r, int c) { return c == 2; }
		});
	}

	private void limpiarTablas() {
		view.tableEmpresas.setModel(new DefaultTableModel());
		view.tableOfertasEnCurso.setModel(new DefaultTableModel());
	}
}