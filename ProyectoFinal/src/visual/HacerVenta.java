package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cliente;
import logico.Componente;
import logico.Factura;
import logico.Tienda;
import logico.Vendedor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class HacerVenta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private static JButton btnBuscar;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JButton btnRegistrar;
	private JList listComponente;
	private JList listVenta;
	private JButton btnDerecha;
	private JButton btnIzquierda;
	private DefaultListModel listModelComp;
	private DefaultListModel listModelVenta;
	private Cliente cliente;
	private JButton btnFinalizar;
	private JRadioButton btnSinCredito;
	private JSpinner spnDescuento;
	private JRadioButton btnCredito;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HacerVenta dialog = new HacerVenta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HacerVenta() {
		setTitle("Hacer venta");
		setBounds(100, 100, 450, 515);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Cedula Cliente:");
			lblNewLabel.setBounds(10, 11, 79, 14);
			panel.add(lblNewLabel);
			
			txtCedula = new JTextField();
			txtCedula.setBounds(93, 8, 211, 20);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				cliente=Tienda.getInstance().buscarClienteByCedula(txtCedula.getText());
				if(cliente!=null) {
					txtNombre.setText(cliente.getNombre());
					txtDireccion.setText(cliente.getDireccion());
					txtTelefono.setText(cliente.getTelefono());
					btnFinalizar.setEnabled(true);
				}else {
					txtNombre.setEditable(true);
					txtDireccion.setEditable(true);
					txtTelefono.setEditable(true);
					btnRegistrar.setEnabled(true);
				}
				}
			});
			btnBuscar.setBounds(314, 7, 89, 23);
			panel.add(btnBuscar);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(20, 46, 372, 129);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(10, 11, 46, 14);
			panel_1.add(lblNewLabel_1);
			
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setBounds(59, 8, 174, 20);
			panel_1.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel txtDirrecion = new JLabel("Direccion:");
			txtDirrecion.setBounds(10, 42, 52, 14);
			panel_1.add(txtDirrecion);
			
			txtDireccion = new JTextField();
			txtDireccion.setEditable(false);
			txtDireccion.setBounds(69, 39, 164, 20);
			panel_1.add(txtDireccion);
			txtDireccion.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Telefono:");
			lblNewLabel_2.setBounds(10, 71, 69, 14);
			panel_1.add(lblNewLabel_2);
			
			txtTelefono = new JTextField();
			txtTelefono.setEditable(false);
			txtTelefono.setBounds(67, 68, 195, 20);
			panel_1.add(txtTelefono);
			txtTelefono.setColumns(10);
			
			btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Cliente aux=new Cliente(txtCedula.getText(),txtNombre.getText(),txtDirrecion.getText(),txtTelefono.getText());
					Tienda.getInstance().insertarCliente(aux);
					cleanReg();
				}
			});
			btnRegistrar.setEnabled(false);
			btnRegistrar.setBounds(119, 99, 89, 23);
			panel_1.add(btnRegistrar);
			
			JLabel lblNewLabel_3 = new JLabel("Componentes disponibles:");
			lblNewLabel_3.setBounds(10, 196, 132, 14);
			panel.add(lblNewLabel_3);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(20, 216, 122, 135);
			panel.add(scrollPane);
			
			listModelComp=new DefaultListModel();
			listComponente = new JList();
			listComponente.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index =- 1;
					index = listComponente.getSelectedIndex();
					if (index != -1){
						btnDerecha.setEnabled(true);
					}
				}
			});
			listComponente.setModel(listModelComp);
			scrollPane.setViewportView(listComponente);
			
			btnDerecha = new JButton(">>");
			btnDerecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String comp=listComponente.getSelectedValue().toString();
					listModelVenta.addElement(comp);
				}
			});
			btnDerecha.setEnabled(false);
			btnDerecha.setBounds(160, 226, 89, 23);
			panel.add(btnDerecha);
			
			btnIzquierda = new JButton("<<");
			btnIzquierda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String comp=listComponente.getSelectedValue().toString();
					listModelVenta.removeElement(comp);
				}
			});
			btnIzquierda.setEnabled(false);
			btnIzquierda.setBounds(160, 295, 89, 23);
			panel.add(btnIzquierda);
			
			JLabel lblNewLabel_4 = new JLabel("Venta:");
			lblNewLabel_4.setBounds(306, 196, 46, 14);
			panel.add(lblNewLabel_4);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(282, 216, 110, 135);
			panel.add(scrollPane_1);
			
			listModelVenta=new DefaultListModel();
			listVenta = new JList();
			listVenta.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index =-1;
					index = listVenta.getSelectedIndex();
					if (index != -1){
						btnIzquierda.setEnabled(true);
					}
				}
			});
			listVenta.setModel(listModelVenta);
			scrollPane_1.setViewportView(listVenta);
			
			btnCredito = new JRadioButton("A credito");
			btnCredito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnCredito.setSelected(true);
					btnSinCredito.setSelected(false);
					
				}
			});
			btnCredito.setBounds(48, 368, 109, 23);
			panel.add(btnCredito);
			
			btnSinCredito = new JRadioButton("Sin Credito");
			btnSinCredito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnSinCredito.setSelected(true);
					btnCredito.setSelected(false);
				}
			});
			btnSinCredito.setSelected(true);
			btnSinCredito.setBounds(207, 368, 109, 23);
			panel.add(btnSinCredito);
			
			JLabel lblNewLabel_5 = new JLabel("Descuento:");
			lblNewLabel_5.setBounds(21, 408, 61, 14);
			panel.add(lblNewLabel_5);
			
			spnDescuento = new JSpinner();
			spnDescuento.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnDescuento.setBounds(86, 405, 103, 20);
			panel.add(spnDescuento);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnFinalizar = new JButton("Finalizar");
				btnFinalizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					if(listModelVenta.size()>0 && cliente!=null) {
					Factura f=new Factura("F-"+Factura.cod,cliente,Integer.valueOf(spnDescuento.getValue().toString()),Tienda.getLoginUser());
					for(int i=0;i<listModelVenta.size();i++) {
						Componente comp=Tienda.getInstance().buscarComponenteById(listModelVenta.get(i).toString());
						comp.setCantReal(comp.getCantReal()-1);
						f.getVenta().add(comp);
					}
					if(Tienda.getLoginUser() instanceof Vendedor) {
						((Vendedor)Tienda.getLoginUser()).setCantVentas(((Vendedor)Tienda.getLoginUser()).getCantVentas()+1);
						((Vendedor)Tienda.getLoginUser()).setVentas(((Vendedor)Tienda.getLoginUser()).getVentas()+f.precioTotal());
					}
					Tienda.getInstance().insertarFactura(f);
					JOptionPane.showMessageDialog(null,"Venta realizada con exito","Finalizar Venta",JOptionPane.INFORMATION_MESSAGE);
					clean();
					}else {
						JOptionPane.showMessageDialog(null,"Necesita al menos 1 componente para finalizar la venta","Aviso",JOptionPane.WARNING_MESSAGE);
					}
					}
				});
				btnFinalizar.setEnabled(false);
				btnFinalizar.setActionCommand("OK");
				buttonPane.add(btnFinalizar);
				getRootPane().setDefaultButton(btnFinalizar);
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
		loadComponentesDisponible();
	}

	private void loadComponentesDisponible() {
		for(Componente comp:Tienda.getInstance().getInventario()) {
			listModelComp.removeAllElements();
			if(comp.getCantReal()>0) {
				listModelComp.addElement(comp.getId());
			}
		}
	}
	
	private void clean() {
		txtCedula.setText("");
		spnDescuento.setValue(0);
		cleanReg();
		listModelVenta.removeAllElements();
		loadComponentesDisponible();
		btnFinalizar.setEnabled(false);
		btnSinCredito.setSelected(true);
		btnCredito.setEnabled(false);
	}
	private void cleanReg() {
		txtNombre.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		btnRegistrar.setEnabled(false);
	}
}
