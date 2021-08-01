package visual;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Componente;
import logico.OrdenCompra;
import logico.Suministrador;
import logico.Tienda;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.JSeparator;

public class RegOrdenCompra extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistrar;
	private JTextField txtId;
	private JButton btnDerecha;
	private JButton btnIzquierda;
	private JList<String> listCompra;
	private JComboBox<String> cbxSumi;
	private DefaultListModel<String> listModelComp;
	private DefaultListModel<String> listModelCompra;
	private JSpinner spnCantUnidad;
	private OrdenCompra selected;
	private JList<String> listComponente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegOrdenCompra dialog = new RegOrdenCompra(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegOrdenCompra(OrdenCompra orden) {
		selected = orden;
		if (selected == null) {
			setTitle("Registrar Orden de Compra");
		} else {
			setTitle("Modificar Orden de Compra");
		}
		setBounds(100, 100, 406, 389);
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
			
			JLabel lblNewLabel = new JLabel("Codigo:");
			lblNewLabel.setBounds(10, 16, 79, 14);
			panel.add(lblNewLabel);
			
			txtId = new JTextField();
			txtId.setText("OC-"+OrdenCompra.cod);
			txtId.setEnabled(false);
			txtId.setBounds(110, 8, 136, 30);
			panel.add(txtId);
			txtId.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Suministrador:");
			lblNewLabel_1.setBounds(10, 54, 128, 14);
			panel.add(lblNewLabel_1);
			
			cbxSumi = new JComboBox<String>();
			cbxSumi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadComponentes();
				}
			});
			cbxSumi.setBounds(110, 46, 258, 30);
			panel.add(cbxSumi);
			
			JLabel lblNewLabel_2 = new JLabel("Unidades:");
			lblNewLabel_2.setBounds(10, 94, 90, 14);
			panel.add(lblNewLabel_2);
			
			spnCantUnidad = new JSpinner();
			spnCantUnidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnCantUnidad.setBounds(110, 91, 136, 30);
			panel.add(spnCantUnidad);
			
			JLabel lblNewLabel_3 = new JLabel("Componentes:");
			lblNewLabel_3.setBounds(10, 149, 106, 14);
			panel.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Compra:");
			lblNewLabel_4.setBounds(256, 149, 112, 14);
			panel.add(lblNewLabel_4);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 172, 106, 124);
			panel.add(scrollPane);
			
			listModelComp = new DefaultListModel<String>();
			listComponente = new JList<String>();
			listComponente.setModel(listModelComp);
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
					String aux = listComponente.getSelectedValue().toString();
					listModelCompra.addElement(aux);
					listModelComp.remove(listComponente.getSelectedIndex());
					btnDerecha.setEnabled(false);		
				}
			});
			btnDerecha.setEnabled(false);
			btnDerecha.setBounds(142, 190, 89, 30);
			panel.add(btnDerecha);
			
			btnIzquierda = new JButton("<<");
			btnIzquierda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String aux = listCompra.getSelectedValue().toString();
					listModelComp.addElement(aux);
					listModelCompra.remove(listCompra.getSelectedIndex());
					btnIzquierda.setEnabled(false);	
				}
			});
			btnIzquierda.setEnabled(false);
			btnIzquierda.setBounds(142, 253, 89, 30);
			panel.add(btnIzquierda);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(256, 172, 112, 124);
			panel.add(scrollPane_1);
			
			listModelCompra = new DefaultListModel<String>();
			listCompra = new JList<String>();
			listCompra.setModel(listModelCompra);
			listCompra.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index =- 1;
					index = listCompra.getSelectedIndex();
					if (index != -1){
						btnIzquierda.setEnabled(true);
					}
				}
			});
			listCompra.setModel(listModelCompra);
			scrollPane_1.setViewportView(listCompra);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(10, 134, 360, 2);
			panel.add(separator);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton();
				if (selected == null) {
					btnRegistrar.setText("Registrar");
				} else {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					 if (listModelCompra.size() > 0) {
						 if (cbxSumi.getSelectedIndex() != 0) {
							 if (selected == null) {
									Suministrador sumi = Tienda.getInstance().buscarSumiById(cbxSumi.getSelectedItem().toString());
									OrdenCompra nuevaorden = new OrdenCompra(txtId.getText(),sumi,Integer.valueOf(spnCantUnidad.getValue().toString()));
									
									for(int i = 0; i < listModelCompra.size(); i++) {
										Componente comp = Tienda.getInstance().buscarComponenteById(listModelCompra.get(i).toString());
										nuevaorden.getComponentes().add(comp);
									}
									Tienda.getInstance().insertarOrdenCompra(nuevaorden);
									JOptionPane.showMessageDialog(null,"Se ha registrado la orden con exito","Registro de Orden de Compra",JOptionPane.INFORMATION_MESSAGE);
									clean();
								 } else {
									 Suministrador s = Tienda.getInstance().buscarSumiById(cbxSumi.getSelectedItem().toString());
									 selected.setSuministrador(s);
									 selected.setId(txtId.getText());
									 selected.setCantUnidades(Integer.valueOf(spnCantUnidad.getValue().toString()));
									 
									 ArrayList<Componente> componentes = new ArrayList<>();
									 for (int i = 0; i < listModelCompra.size(); i++) {
										 Componente c = Tienda.getInstance().buscarComponenteById(listModelCompra.get(i).toString());
										 if (c != null) {
											 componentes.add(c);
										 }
									 }
									 selected.setComponentes(componentes);
									 
									 JOptionPane.showMessageDialog(null,"Se ha modificado la orden","Modificacion de Orden",JOptionPane.INFORMATION_MESSAGE);
									 ListOrdenCompra.loadTable();
									 dispose();
								 } 
							 } else {
								 JOptionPane.showMessageDialog(null,"Necesita seleccionar un Suministrador","Aviso",JOptionPane.WARNING_MESSAGE);
							 }
						 
						 } else {
							 JOptionPane.showMessageDialog(null,"Necesita al menos 1 componente para la orden","Aviso",JOptionPane.WARNING_MESSAGE);
						 }
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
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
		loadcbxSumi();
		loadOrdenCompra();
		loadComponentes();
	}

	private void loadOrdenCompra() {
		if (selected != null) {
			txtId.setText(selected.getId());
			cbxSumi.setSelectedItem(selected.getSuministrador().getId());
			System.out.println(selected.getSuministrador().getId());
			spnCantUnidad.setValue(selected.getCantUnidades());
			
			if (selected != null) {
				for (Componente c : selected.getComponentes()) {
					listModelCompra.addElement(c.getId());
				}
			}
		}
		
	}
	
	private void checkComponentes() {
		Suministrador sumi = Tienda.getInstance().buscarSumiById(cbxSumi.getSelectedItem().toString()); 
		if (sumi != null) {
			for (int i = 0; i < listModelCompra.size(); i++) {
				Componente c = Tienda.getInstance().buscarComponenteById(listModelCompra.get(i).toString());
				if (!sumi.getComponentes().contains(c)) {
					listModelCompra.removeElementAt(i);
					i--;
				}
			}
		}
	}

	private void loadcbxSumi() {
		cbxSumi.removeAll();
		
		cbxSumi.addItem("<Seleccione>"); 
		for (Suministrador sumi : Tienda.getInstance().getSuministradores()) {
			String aux = new String(sumi.getId());
			cbxSumi.addItem(aux);
		}
		
	}

	private void loadComponentes() {
		listModelComp.removeAllElements();
		if (cbxSumi.getSelectedIndex() != 0 && cbxSumi.getSelectedIndex() != -1) {			
			for (Componente com : Tienda.getInstance().getInventario()) {
				Suministrador sumi = Tienda.getInstance().buscarSumiById(cbxSumi.getSelectedItem().toString()); 
				if (sumi.getComponentes().contains(com)) {
					if (!listModelCompra.contains(new String(com.getId()))) {
						if (selected != null) {
							String aux = new String(com.getId());
							listModelComp.addElement(aux);
						} else {
							String aux = new String(com.getId());
							listModelComp.addElement(aux);
						}
					}
				}
			}
			checkComponentes();
			
		} else {
			for (Componente com : Tienda.getInstance().getInventario()) {
				if (!listModelCompra.contains(new String(com.getId()))) {
					if (selected != null) {
						String aux = new String(com.getId());
						listModelComp.addElement(aux);
					} else {
						String aux = new String(com.getId());
						listModelComp.addElement(aux);
					}
				}
			}
		}
		
	}
	
	private void clean() {
		txtId.setText("OC-" + OrdenCompra.cod);
		cbxSumi.setSelectedIndex(0);
		spnCantUnidad.setValue(0);
		listModelCompra.removeAllElements();
		loadComponentes();
	}
}
