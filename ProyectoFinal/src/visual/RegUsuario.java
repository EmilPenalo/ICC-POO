package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Administrador;
import logico.Tienda;
import logico.Usuario;
import logico.Vendedor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class RegUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistar;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtUserName;
	private JPasswordField txtPassWord;
	private JRadioButton rdbtnVendedor;
	private JRadioButton rdbtnAdmin;
	private JSpinner spnComision;
	private JPanel panelVendedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegUsuario dialog = new RegUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegUsuario() {
		setTitle("Registrar Usuario");
		setBounds(100, 100, 386, 391);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Codigo:");
				lblNewLabel.setBounds(15, 16, 82, 20);
				panel.add(lblNewLabel);
			}
			{
				txtId = new JTextField();
				txtId.setBounds(80, 11, 146, 30);
				txtId.setText("U-" + Usuario.cod);
				txtId.setEditable(false);
				panel.add(txtId);
				txtId.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre:");
				lblNewLabel_1.setBounds(15, 57, 66, 20);
				panel.add(lblNewLabel_1);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setBounds(80, 52, 268, 30);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Username:");
				lblNewLabel_2.setBounds(15, 98, 82, 20);
				panel.add(lblNewLabel_2);
			}
			{
				txtUserName = new JTextField();
				txtUserName.setBounds(80, 93, 268, 30);
				panel.add(txtUserName);
				txtUserName.setColumns(10);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Password:");
				lblNewLabel_3.setBounds(15, 139, 104, 20);
				panel.add(lblNewLabel_3);
			}
			
			txtPassWord = new JPasswordField();
			txtPassWord.setBounds(80, 134, 268, 30);
			panel.add(txtPassWord);
			
			JPanel panelButtons = new JPanel();
			panelButtons.setBorder(new TitledBorder(null, "Tipo", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, null));
			panelButtons.setBounds(15, 175, 333, 57);
			panel.add(panelButtons);
			panelButtons.setLayout(null);
			
			rdbtnVendedor = new JRadioButton("Vendedor");
			rdbtnVendedor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					rdbtnVendedor.setSelected(true);
					rdbtnAdmin.setSelected(false);
					
					panelVendedor.setVisible(true);
				}
			});
			rdbtnVendedor.setSelected(true);
			rdbtnVendedor.setBounds(43, 23, 101, 29);
			panelButtons.add(rdbtnVendedor);
			
			rdbtnAdmin = new JRadioButton("Admin");
			rdbtnAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnVendedor.setSelected(false);
					rdbtnAdmin.setSelected(true);
					
					panelVendedor.setVisible(false);
				}
			});
			rdbtnAdmin.setBounds(187, 23, 101, 29);
			panelButtons.add(rdbtnAdmin);
			
			panelVendedor = new JPanel();
			panelVendedor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelVendedor.setBounds(15, 237, 333, 55);
			panel.add(panelVendedor);
			panelVendedor.setLayout(null);
			
			JLabel lblNewLabel_4 = new JLabel("Comision:");
			lblNewLabel_4.setBounds(15, 16, 90, 20);
			panelVendedor.add(lblNewLabel_4);
			
			spnComision = new JSpinner();
			spnComision.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnComision.setBounds(85, 11, 146, 30);
			panelVendedor.add(spnComision);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistar = new JButton("Registrar");
				btnRegistar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Usuario aux = null;
						String id = txtId.getText();
						String nombre = txtNombre.getText();
						String username = txtUserName.getText();
						String password = new String(txtPassWord.getPassword());
						
						if (rdbtnVendedor.isSelected()) {
							int comision = new Integer(spnComision.getValue().toString());
							aux = new Vendedor(id, nombre, username, password, comision);
						}
						
						if (rdbtnAdmin.isSelected()) {
							aux = new Administrador(id, nombre, username, password);
						}
						
						Tienda.getInstance().insertarUsuario(aux);
						JOptionPane.showMessageDialog(null, "Nuevo usuario registrado con exito","Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
						clean();
					}
				});
				buttonPane.add(btnRegistar);
				getRootPane().setDefaultButton(btnRegistar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void clean() {
		txtId.setText("U-" + Usuario.cod);
		txtNombre.setText("");
		txtUserName.setText("");
		txtPassWord.setText("");
	}
}
