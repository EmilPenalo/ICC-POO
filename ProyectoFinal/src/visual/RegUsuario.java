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
import javax.swing.border.TitledBorder;

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
		setBounds(100, 100, 379, 302);
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
			
			JLabel lblNewLabel_4 = new JLabel("Tipo:");
			lblNewLabel_4.setBounds(15, 180, 46, 20);
			panel.add(lblNewLabel_4);
			
			cbxTipo = new JComboBox();
			cbxTipo.setBounds(80, 175, 146, 30);
			cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Vendedor", "Administrador"}));
			panel.add(cbxTipo);
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
						if (cbxTipo.getSelectedIndex() != 0) {
							Usuario user = new Usuario(txtId.getText(),txtNombre.getText(),txtUserName.getText(), new String(txtPassWord.getPassword()) ,cbxTipo.getSelectedItem().toString().charAt(0));
							Tienda.getInstance().insertarUsuario(user);
							JOptionPane.showMessageDialog(null, "Nuevo usuario registrado con exito","Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
							clean();
						} else {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo usuario","Informacion", JOptionPane.INFORMATION_MESSAGE);
						}
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
