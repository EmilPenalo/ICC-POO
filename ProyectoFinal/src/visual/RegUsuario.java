package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Tienda;
import logico.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistar;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtUserName;
	private JPasswordField txtPassWord;
	private JComboBox cbxTipo;

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
		setBounds(100, 100, 379, 326);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Id:");
				lblNewLabel.setBounds(10, 32, 46, 14);
				panel.add(lblNewLabel);
			}
			{
				txtId = new JTextField();
				txtId.setText(String.valueOf(Usuario.cod));
				txtId.setEditable(false);
				txtId.setBounds(31, 29, 203, 20);
				panel.add(txtId);
				txtId.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre:");
				lblNewLabel_1.setBounds(10, 85, 66, 14);
				panel.add(lblNewLabel_1);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setBounds(58, 82, 214, 20);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("UserName:");
				lblNewLabel_2.setBounds(10, 133, 66, 14);
				panel.add(lblNewLabel_2);
			}
			{
				txtUserName = new JTextField();
				txtUserName.setBounds(74, 130, 228, 20);
				panel.add(txtUserName);
				txtUserName.setColumns(10);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("PassWord:");
				lblNewLabel_3.setBounds(10, 173, 66, 14);
				panel.add(lblNewLabel_3);
			}
			
			txtPassWord = new JPasswordField();
			txtPassWord.setBounds(77, 170, 257, 20);
			panel.add(txtPassWord);
			
			JLabel lblNewLabel_4 = new JLabel("Tipo:");
			lblNewLabel_4.setBounds(10, 219, 46, 14);
			panel.add(lblNewLabel_4);
			
			cbxTipo = new JComboBox();
			cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Vendedor", "Administrador"}));
			cbxTipo.setBounds(42, 216, 204, 20);
			panel.add(cbxTipo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistar = new JButton("Registrar");
				btnRegistar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Usuario user=new Usuario(txtId.getText(),txtNombre.getText(),txtUserName.getText(),txtPassWord.getPassword().toString(),cbxTipo.getSelectedItem().toString());
						Tienda.getInstance().insertarUsuario(user);
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
		txtId.setText(String.valueOf(Usuario.cod));
		txtNombre.setText("");
		txtUserName.setText("");
		txtPassWord.setText("");
	}
}
