package app.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Color;

public class OfrecerReportajesView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// Componentes para el controlador
	public JTable tableEventos;
	public JTable tableEmpresas;
	public JTable tableOfertasEnCurso;
	
	// Filtro de la HU #33531
	public JComboBox<String> comboFiltroEmpresas;
	
	// Botones
	public JButton btnOfertar;
	public JButton btnQuitarOfrecimiento; // Nuevo botón HU #33531
	public JButton btnCancelar;
	public JButton btnAceptarTodo;
	public JButton btnLimpiarSeleccion;

	public OfrecerReportajesView() {
		setTitle("Gestión de Ofrecimientos de Reportajes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1050, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// --- COLUMNA 1: EVENTOS (Igual al prototipo original) ---
		JLabel lblEventos = new JLabel("EVENTOS CON REPORTEROS ASIGNADOS:");
		lblEventos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEventos.setBounds(20, 20, 300, 14);
		contentPane.add(lblEventos);

		JScrollPane scrollEventos = new JScrollPane();
		scrollEventos.setBounds(20, 45, 300, 450);
		contentPane.add(scrollEventos);
		
		tableEventos = new JTable();
		scrollEventos.setViewportView(tableEventos);

		// --- COLUMNA 2: GESTIÓN DE EMPRESAS (Modificada HU #33531) ---
		JLabel lblEmpresas = new JLabel("GESTIÓN DE EMPRESAS COMUNICACIÓN:");
		lblEmpresas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmpresas.setBounds(340, 20, 300, 14);
		contentPane.add(lblEmpresas);

		// Selector de Filtro
		comboFiltroEmpresas = new JComboBox<String>();
		comboFiltroEmpresas.setModel(new DefaultComboBoxModel<String>(new String[] {
			"Ver empresas SIN OFERTA", 
			"Ver empresas YA OFERTADAS"
		}));
		comboFiltroEmpresas.setBounds(340, 45, 330, 25);
		contentPane.add(comboFiltroEmpresas);

		JScrollPane scrollEmpresas = new JScrollPane();
		scrollEmpresas.setBounds(340, 80, 330, 350);
		contentPane.add(scrollEmpresas);
		
		tableEmpresas = new JTable();
		scrollEmpresas.setViewportView(tableEmpresas);

		// Botonera Central
		btnOfertar = new JButton("OFERTAR");
		btnOfertar.setBounds(340, 440, 155, 30);
		contentPane.add(btnOfertar);

		btnQuitarOfrecimiento = new JButton("QUITAR OFRECIMIENTO");
		btnQuitarOfrecimiento.setForeground(Color.RED);
		btnQuitarOfrecimiento.setBounds(515, 440, 155, 30);
		btnQuitarOfrecimiento.setEnabled(false); // Se habilita según el filtro
		contentPane.add(btnQuitarOfrecimiento);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(340, 480, 330, 30);
		contentPane.add(btnCancelar);

		// --- COLUMNA 3: OFERTAS EN CURSO ---
		JLabel lblOfertas = new JLabel("OFERTAS EN CURSO (Estado):");
		lblOfertas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOfertas.setBounds(690, 20, 300, 14);
		contentPane.add(lblOfertas);

		JScrollPane scrollOfertas = new JScrollPane();
		scrollOfertas.setBounds(690, 45, 320, 385);
		contentPane.add(scrollOfertas);
		
		tableOfertasEnCurso = new JTable();
		scrollOfertas.setViewportView(tableOfertasEnCurso);

		btnAceptarTodo = new JButton("ACEPTAR TODO");
		btnAceptarTodo.setBounds(690, 440, 320, 30);
		contentPane.add(btnAceptarTodo);

		btnLimpiarSeleccion = new JButton("LIMPIAR TABLAS");
		btnLimpiarSeleccion.setBounds(690, 480, 320, 30);
		contentPane.add(btnLimpiarSeleccion);
	}
}