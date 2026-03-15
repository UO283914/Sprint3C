package app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;

public class DarAccesoEmpresaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabEventos;
	private JTable tabEmpresas;
	private JButton btnDarAcceso;
	private JButton btnQuitarAcceso;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JComboBox<String> cbFiltroAcceso;

	// El JLabel arriba para poder cambiarlo desde el Controlador
	private JLabel lblNombreAgencia;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DarAccesoEmpresaView frame = new DarAccesoEmpresaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DarAccesoEmpresaView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1120, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 500, 380);
		contentPane.add(scrollPane);

		tabEventos = new JTable();
		scrollPane.setViewportView(tabEventos);

		lblNombreAgencia = new JLabel("Agencia de Prensa : Mi Agencia");
		lblNombreAgencia.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNombreAgencia.setBounds(10, 9, 450, 22);
		contentPane.add(lblNombreAgencia);

		JLabel lblTituloEventos = new JLabel("Eventos con Reportaje entregado");
		lblTituloEventos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTituloEventos.setBounds(10, 64, 320, 14);
		contentPane.add(lblTituloEventos);

		JLabel lblFiltro = new JLabel("Filtro de acceso:");
		lblFiltro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFiltro.setBounds(540, 64, 140, 14);
		contentPane.add(lblFiltro);

		cbFiltroAcceso = new JComboBox<>(new String[] {
				"Empresas sin acceso concedido",
				"Empresas con acceso concedido"
		});
		cbFiltroAcceso.setBounds(680, 60, 250, 25);
		contentPane.add(cbFiltroAcceso);

		JLabel lblTituloEmpresas = new JLabel("Empresas");
		lblTituloEmpresas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTituloEmpresas.setBounds(540, 96, 208, 14);
		contentPane.add(lblTituloEmpresas);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(540, 121, 554, 348);
		contentPane.add(scrollPane_1);

		tabEmpresas = new JTable();
		scrollPane_1.setViewportView(tabEmpresas);

		btnDarAcceso = new JButton("Dar Acceso");
		btnDarAcceso.setBounds(540, 480, 180, 30);
		contentPane.add(btnDarAcceso);

		btnQuitarAcceso = new JButton("Quitar Acceso");
		btnQuitarAcceso.setBounds(730, 480, 180, 30);
		contentPane.add(btnQuitarAcceso);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(983, 567, 111, 23);
		contentPane.add(btnCancelar);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(856, 567, 117, 23);
		contentPane.add(btnAceptar);
	}

	public JTable getTabEventos() { return tabEventos; }
	public JTable getTabEmpresas() { return tabEmpresas; }
	public JButton getBtnDarAcceso() { return btnDarAcceso; }
	public JButton getBtnQuitarAcceso() { return btnQuitarAcceso; }
	public JButton getBtnCancelar() { return btnCancelar; }
	public JButton getBtnAceptar() { return btnAceptar; }
	public JComboBox<String> getCbFiltroAcceso() { return cbFiltroAcceso; }
	public JFrame getFrame() { return this; }
	public JLabel getLblTituloAgencia() { return this.lblNombreAgencia; }
}