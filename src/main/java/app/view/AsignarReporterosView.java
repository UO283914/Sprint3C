package app.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class AsignarReporterosView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabEventos;
	private JTable tabDisponibles;
	private JTable tabAsignados;
	private JButton btnAsignar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JButton btnFinalizarAsignacion;
	private JButton btnElegirResponsable;
	private JLabel lblNombreAgencia;
	private JLabel lblResponsable;
	private JComboBox<String> cbFiltroEventos;
	private JComboBox<String> cbFiltroTematicaReporteros;
	private JComboBox<String> cbFiltroTipoReportero;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AsignarReporterosView frame = new AsignarReporterosView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AsignarReporterosView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1180, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNombreAgencia = new JLabel("Agencia de Prensa : Mi Agencia");
		lblNombreAgencia.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNombreAgencia.setBounds(10, 9, 480, 22);
		contentPane.add(lblNombreAgencia);

		lblResponsable = new JLabel("Reportero responsable: (sin seleccionar)");
		lblResponsable.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblResponsable.setBounds(520, 9, 470, 22);
		contentPane.add(lblResponsable);

		btnElegirResponsable = new JButton("Elegir responsable");
		btnElegirResponsable.setBounds(520, 40, 190, 24);
		contentPane.add(btnElegirResponsable);

		JLabel lblFiltro = new JLabel("Filtro de eventos:");
		lblFiltro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFiltro.setBounds(10, 45, 140, 20);
		contentPane.add(lblFiltro);

		cbFiltroEventos = new JComboBox<String>();
		cbFiltroEventos.setModel(new DefaultComboBoxModel(
				new String[] { "Eventos sin reporteros asignados", "Eventos con reporteros asignados" }));
		cbFiltroEventos.setBounds(160, 45, 300, 22);
		contentPane.add(cbFiltroEventos);

		JLabel lblFiltroTematica = new JLabel("Filtro reporteros:");
		lblFiltroTematica.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFiltroTematica.setBounds(10, 75, 140, 20);
		contentPane.add(lblFiltroTematica);

		cbFiltroTematicaReporteros = new JComboBox<String>();
		cbFiltroTematicaReporteros.setModel(new DefaultComboBoxModel(
				new String[] { "Todos los disponibles", "Solo especializados en temática del evento" }));
		cbFiltroTematicaReporteros.setBounds(160, 75, 300, 22);
		contentPane.add(cbFiltroTematicaReporteros);

		JLabel lblFiltroTipoReportero = new JLabel("Tipo:");
		lblFiltroTipoReportero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFiltroTipoReportero.setBounds(10, 105, 140, 20);
		contentPane.add(lblFiltroTipoReportero);

		cbFiltroTipoReportero = new JComboBox<String>();
		cbFiltroTipoReportero
				.setModel(new DefaultComboBoxModel<>(new String[] { "TODOS", "GRÁFICO", "CAMARÓGRAFO", "BASE" }));
		cbFiltroTipoReportero.setBounds(160, 105, 300, 22);
		contentPane.add(cbFiltroTipoReportero);

		JLabel lblNewLabel_1 = new JLabel("Eventos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 150, 81, 14);
		contentPane.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 175, 430, 390);
		contentPane.add(scrollPane);
		tabEventos = new JTable();
		scrollPane.setViewportView(tabEventos);

		JLabel lblNewLabel_2 = new JLabel("Reporteros Disponibles");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(470, 150, 208, 14);
		contentPane.add(lblNewLabel_2);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(470, 175, 330, 390);
		contentPane.add(scrollPane_1);
		tabDisponibles = new JTable();
		scrollPane_1.setViewportView(tabDisponibles);

		btnAsignar = new JButton("Asignar");
		btnAsignar.setBounds(470, 577, 330, 28);
		contentPane.add(btnAsignar);

		JLabel lblNewLabel_3 = new JLabel("Reporteros Asignados");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(830, 150, 242, 14);
		contentPane.add(lblNewLabel_3);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(830, 175, 330, 390);
		contentPane.add(scrollPane_2);
		tabAsignados = new JTable();
		scrollPane_2.setViewportView(tabAsignados);

		btnEliminar = new JButton("Eliminar Asignación");
		btnEliminar.setBounds(830, 577, 330, 28);
		contentPane.add(btnEliminar);

		btnFinalizarAsignacion = new JButton("Finalizar asignación de evento");
		btnFinalizarAsignacion.setBounds(10, 577, 430, 28);
		contentPane.add(btnFinalizarAsignacion);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(1040, 626, 120, 28);
		contentPane.add(btnCancelar);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(910, 626, 120, 28);
		contentPane.add(btnAceptar);
	}

	public JTable getTabEventos() { return tabEventos; }
	public JTable getTabDisponibles() { return tabDisponibles; }
	public JTable getTabAsignados() { return tabAsignados; }
	public JButton getBtnAsignar() { return btnAsignar; }
	public JButton getBtnEliminar() { return btnEliminar; }
	public JButton getBtnCancelar() { return btnCancelar; }
	public JButton getBtnAceptar() { return btnAceptar; }
	public JButton getBtnFinalizarAsignacion() { return btnFinalizarAsignacion; }
	public JButton getBtnElegirResponsable() { return btnElegirResponsable; }
	public JComboBox<String> getCbFiltroEventos() { return cbFiltroEventos; }
	public JComboBox<String> getCbFiltroTematicaReporteros() { return cbFiltroTematicaReporteros; }
	public JComboBox<String> getCbFiltroTipoReportero() { return cbFiltroTipoReportero; }
	public JFrame getFrame() { return this; }
	public JLabel getLblTituloAgencia() { return this.lblNombreAgencia; }
	public JLabel getLblResponsable() { return this.lblResponsable; }
}
