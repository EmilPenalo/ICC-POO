package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cliente;
import logico.Tienda;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

public class OtorgarCredito extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JButton btnBuscar;
	private JTextField txtCreditoActual;
	private JSpinner spnMonto;
	private Cliente cliente;
	private JButton btnOtorgar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OtorgarCredito dialog = new OtorgarCredito();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OtorgarCredito() {
		setTitle("Otorgar Credito Cliente");
		setBounds(100, 100, 450, 218);
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
			
			JLabel lblNewLabel = new JLabel("C\u00E9dula Cliente:");
			lblNewLabel.setBounds(15, 16, 114, 14);
			panel.add(lblNewLabel);
			
			txtCedula = new JTextField();
			txtCedula.setBounds(110, 8, 197, 30);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cliente = Tienda.getInstance().buscarClienteByCedula(txtCedula.getText());
					if(cliente != null) {
						btnOtorgar.setEnabled(true);
						txtCreditoActual.setText(String.valueOf(cliente.getCredito()));
						JOptionPane.showMessageDialog(null,"Cliente encontrado","Otorgar Credito cliente", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,"El cliente no existe. Inténtelo nuevamente","Otorgar Credito cliente", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnBuscar.setBounds(325, 8, 89, 30);
			panel.add(btnBuscar);
			
			JLabel lblNewLabel_1 = new JLabel("Credito Actual:");
			lblNewLabel_1.setBounds(15, 59, 139, 14);
			panel.add(lblNewLabel_1);
			
			txtCreditoActual = new JTextField();
			txtCreditoActual.setEditable(false);
			txtCreditoActual.setBounds(110, 51, 130, 30);
			panel.add(txtCreditoActual);
			txtCreditoActual.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Monto:");
			lblNewLabel_2.setBounds(15, 97, 89, 14);
			panel.add(lblNewLabel_2);
			
			spnMonto = new JSpinner();
			spnMonto.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
			spnMonto.setBounds(110, 89, 130, 30);
			panel.add(spnMonto);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOtorgar = new JButton("Otorgar Credito");
				btnOtorgar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cliente.setCredito(cliente.getCredito() + Float.valueOf(spnMonto.getValue().toString()));
						JOptionPane.showMessageDialog(null, "Credito otorgado con exito", "Otogar credito Cliente", JOptionPane.INFORMATION_MESSAGE);
						clean();
					}
				});
				btnOtorgar.setEnabled(false);
				btnOtorgar.setActionCommand("OK");
				buttonPane.add(btnOtorgar);
				getRootPane().setDefaultButton(btnOtorgar);
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
		txtCedula.setText("");
		txtCreditoActual.setText("");
		spnMonto.setValue(new Float(0));
		btnOtorgar.setEnabled(false);
	}
}
