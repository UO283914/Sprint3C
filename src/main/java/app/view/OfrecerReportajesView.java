package app.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class OfrecerReportajesView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// Componentes para el controlador
	public JTable tableEventos;
	public JTable tableEmpresas;
	public JTable tableOfertasEnCurso;

	// Filtro de la HU #33531
	public JComboBox<String> comboFiltroEmpresas;

	// Filtros de la HU #34081
	public JCheckBox chkCoincidenciaTematicas;
	public JLabel lblTematicasEvento;

	// Botones
	public JButton btnOfertar;
	public JButton btnQuitarOfrecimiento;
	public JButton btnCancelar;
	public JButton btnAceptarTodo;
	public JButton btnLimpiarSeleccion;
	public JLabel lblAgenciaSeleccionada;

	public OfrecerReportajesView() {
		setTitle("Gestion y Modificacion de Ofrecimientos de Reportajes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// --- CABECERA ---
		lblAgenciaSeleccionada = new JLabel("Agencia: -");
		lblAgenciaSeleccionada.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAgenciaSeleccionada.setBounds(20, 5, 450, 20);
		contentPane.add(lblAgenciaSeleccionada);

		// --- COLUMNA 1: EVENTOS ---
		JLabel lblEventos = new JLabel("EVENTOS CON REPORTEROS ASIGNADOS:");
		lblEventos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEventos.setBounds(20, 30, 300, 14);
		contentPane.add(lblEventos);

		JScrollPane scrollEventos = new JScrollPane();
		scrollEventos.setBounds(20, 55, 300, 520);
		contentPane.add(scrollEventos);

		tableEventos = new JTable();
		scrollEventos.setViewportView(tableEventos);

		// --- COLUMNA 2: GESTION DE EMPRESAS (OFRECER) ---
		JLabel lblEmpresas = new JLabel("GESTION DE EMPRESAS COMUNICACION:");
		lblEmpresas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmpresas.setBounds(340, 30, 300, 14);
		contentPane.add(lblEmpresas);

		comboFiltroEmpresas = new JComboBox<String>();
		comboFiltroEmpresas.setModel(new DefaultComboBoxModel<String>(new String[] {
			"Ver empresas SIN OFERTA",
			"Ver empresas YA OFERTADAS"
		}));
		comboFiltroEmpresas.setBounds(340, 55, 330, 25);
		contentPane.add(comboFiltroEmpresas);

		chkCoincidenciaTematicas = new JCheckBox("Filtrar por coincidencia de especializacion");
		chkCoincidenciaTematicas.setBounds(340, 90, 330, 23);
		contentPane.add(chkCoincidenciaTematicas);

		lblTematicasEvento = new JLabel("Tematicas del evento: -");
		lblTematicasEvento.setBounds(340, 118, 330, 20);
		contentPane.add(lblTematicasEvento);

		JScrollPane scrollEmpresas = new JScrollPane();
		scrollEmpresas.setBounds(340, 145, 330, 335);
		contentPane.add(scrollEmpresas);

		tableEmpresas = new JTable();
		scrollEmpresas.setViewportView(tableEmpresas);

		btnOfertar = new JButton("OFERTAR");
		btnOfertar.setBounds(340, 490, 330, 35);
		contentPane.add(btnOfertar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(340, 540, 330, 35);
		contentPane.add(btnCancelar);

		// --- COLUMNA 3: OFERTAS EN CURSO (MODIFICAR) ---
		JLabel lblOfertas = new JLabel("OFERTAS EN CURSO (Estado):");
		lblOfertas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOfertas.setBounds(700, 30, 300, 14);
		contentPane.add(lblOfertas);

		JScrollPane scrollOfertas = new JScrollPane();
		scrollOfertas.setBounds(700, 55, 360, 375);
		contentPane.add(scrollOfertas);

		tableOfertasEnCurso = new JTable();
		scrollOfertas.setViewportView(tableOfertasEnCurso);

		btnQuitarOfrecimiento = new JButton("QUITAR OFRECIMIENTO");
		btnQuitarOfrecimiento.setForeground(new Color(200, 0, 0));
		btnQuitarOfrecimiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuitarOfrecimiento.setBounds(700, 440, 360, 35);
		contentPane.add(btnQuitarOfrecimiento);

		btnAceptarTodo = new JButton("ACEPTAR TODO");
		btnAceptarTodo.setBounds(700, 490, 360, 35);
		contentPane.add(btnAceptarTodo);

		btnLimpiarSeleccion = new JButton("LIMPIAR TABLAS");
		btnLimpiarSeleccion.setBounds(700, 540, 360, 35);
		contentPane.add(btnLimpiarSeleccion);
	}
}
