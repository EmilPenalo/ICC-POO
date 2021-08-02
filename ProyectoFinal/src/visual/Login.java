package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Administrador;
import logico.Tienda;
import logico.Usuario;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				FileInputStream tiendaInput;
				FileOutputStream tiendaOutput;
				ObjectInputStream reader;
				ObjectOutputStream writer;
					
				// Tratando de leer tienda.dat
				try {
					
					tiendaInput = new FileInputStream ("tienda.dat");
					reader = new ObjectInputStream(tiendaInput);
					Tienda temp = (Tienda) reader.readObject();
					Tienda.setTienda(temp);
					
					tiendaInput.close();
					reader.close();
					
					Tienda.getInstance().loadCodigos();
					
				} catch (FileNotFoundException e) {
					
					// Creando tienda.dat si no existe con un usuario default
					try {
						
						tiendaOutput = new  FileOutputStream("tienda.dat");
						writer = new ObjectOutputStream(tiendaOutput);
						Usuario adminUser = new Administrador("U-" + Usuario.cod, "Administrador", "Admin", "Admin");
						Tienda.getInstance().insertarUsuario(adminUser);
						writer.writeObject(Tienda.getInstance());
						
						tiendaOutput.close();
						writer.close();
						
					} catch (FileNotFoundException e1) {
						e.printStackTrace();
					} catch (IOException e1) {
						e.printStackTrace();
					}
					
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					Login dialog = new Login();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setTitle("User Login");
		setBounds(100, 100, 450, 255);
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
			
			JLabel lblUsuario = new JLabel("Usuario:");
			lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblUsuario.setBounds(15, 40, 101, 20);
			panel.add(lblUsuario);
			
			txtUser = new JTextField();
			txtUser.setBounds(100, 35, 300, 30);
			panel.add(txtUser);
			txtUser.setColumns(10);
			
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPassword.setBounds(15, 100, 101, 20);
			panel.add(lblPassword);
			
			txtPassword = new JPasswordField();
			txtPassword.setBounds(100, 95, 300, 30);
			panel.add(txtPassword);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Login");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (Tienda.getInstance().confirmLogin(txtUser.getText(), new String(txtPassword.getPassword()))) {
							Principal principal = new Principal();
							dispose();
							principal.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "El nombre de usuario o su clava es incorrecta", "Error de Login", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
}
