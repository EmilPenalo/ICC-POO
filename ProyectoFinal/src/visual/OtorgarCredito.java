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
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("C\u00E9dula Cliente:");
			lblNewLabel.setBounds(10, 24, 85, 14);
			panel.add(lblNewLabel);
			
			txtCedula = new JTextField();
			txtCedula.setBounds(90, 21, 197, 20);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cliente=Tienda.getInstance().buscarClienteByCedula(txtCedula.getText());
					if(cliente!=null) {
						btnOtorgar.setEnabled(true);
						txtCreditoActual.setText(String.valueOf(cliente.getCredito()));
						JOptionPane.showMessageDialog(null,"Cliente encontrado","Otorgar Credito cliente", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null,"El cliente no existe.Inténtelo nuevamente","Otorgar Credito cliente", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnBuscar.setBounds(300, 20, 89, 23);
			panel.add(btnBuscar);
			
			JLabel lblNewLabel_1 = new JLabel("Credito Actual:");
			lblNewLabel_1.setBounds(10, 59, 85, 14);
			panel.add(lblNewLabel_1);
			
			txtCreditoActual = new JTextField();
			txtCreditoActual.setEditable(false);
			txtCreditoActual.setBounds(87, 56, 103, 20);
			panel.add(txtCreditoActual);
			txtCreditoActual.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Monto a otorgar:");
			lblNewLabel_2.setBounds(10, 103, 85, 14);
			panel.add(lblNewLabel_2);
			
			spnMonto = new JSpinner();
			spnMonto.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
			spnMonto.setBounds(100, 100, 187, 20);
			panel.add(spnMonto);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOtorgar = new JButton("Otorgar Credito");
				btnOtorgar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cliente.setCredito(cliente.getCredito()+Float.valueOf(spnMonto.getValue().toString()));
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
