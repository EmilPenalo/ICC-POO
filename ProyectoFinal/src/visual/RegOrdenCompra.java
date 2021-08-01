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

public class RegOrdenCompra extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistrar;
	private JTextField txtId;
	private JButton btnDerecha;
	private JButton btnIzquierda;
	private JList listCompra;
	private JComboBox cbxSumi;
	private DefaultListModel listModelComp;
	private DefaultListModel listModelCompra;
	private JSpinner spnCantUnidad;
	private OrdenCompra selected;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			RegOrdenCompra dialog = new RegOrdenCompra();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public RegOrdenCompra(OrdenCompra orden) {
		selected=orden;
		if(selected==null) {
		setTitle("Registrar Orden de Compra");
		}else {
		setTitle("Modificar Orden de Compra");
		}
		setBounds(100, 100, 417, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Id:");
			lblNewLabel.setBounds(10, 23, 46, 14);
			panel.add(lblNewLabel);
			
			txtId = new JTextField();
			txtId.setText("OC-"+OrdenCompra.cod);
			txtId.setEnabled(false);
			txtId.setBounds(30, 20, 136, 20);
			panel.add(txtId);
			txtId.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Suministrador:");
			lblNewLabel_1.setBounds(10, 72, 79, 14);
			panel.add(lblNewLabel_1);
			
			cbxSumi = new JComboBox();
			cbxSumi.setBounds(83, 69, 232, 20);
			panel.add(cbxSumi);
			
			JLabel lblNewLabel_2 = new JLabel("Cant. Unidades:");
			lblNewLabel_2.setBounds(10, 114, 90, 14);
			panel.add(lblNewLabel_2);
			
			spnCantUnidad = new JSpinner();
			spnCantUnidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnCantUnidad.setBounds(93, 111, 128, 20);
			panel.add(spnCantUnidad);
			
			JLabel lblNewLabel_3 = new JLabel("Componentes:");
			lblNewLabel_3.setBounds(10, 149, 79, 14);
			panel.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Compra:");
			lblNewLabel_4.setBounds(298, 149, 46, 14);
			panel.add(lblNewLabel_4);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 172, 106, 124);
			panel.add(scrollPane);
			
			listModelComp=new DefaultListModel();
			JList listComponente = new JList();
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
					String aux=listComponente.getSelectedValue().toString();
					listModelCompra.addElement(aux);		
				}
			});
			btnDerecha.setEnabled(false);
			btnDerecha.setBounds(144, 190, 89, 23);
			panel.add(btnDerecha);
			
			btnIzquierda = new JButton("<<");
			btnIzquierda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String aux=listCompra.getSelectedValue().toString();
					listModelCompra.removeElement(aux);
				}
			});
			btnIzquierda.setEnabled(false);
			btnIzquierda.setBounds(144, 253, 89, 23);
			panel.add(btnIzquierda);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(256, 172, 112, 124);
			panel.add(scrollPane_1);
			
			listModelCompra=new DefaultListModel();
			listCompra = new JList();
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
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton();
				if(selected==null) {
					btnRegistrar.setText("Registrar");
				}else {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					 if(listModelCompra.size()>0) {
						 if(selected==null) {
						Suministrador sumi=Tienda.getInstance().buscarSumiById(cbxSumi.getSelectedItem().toString());
						OrdenCompra nuevaorden=new OrdenCompra(txtId.getText(),sumi,Integer.valueOf(spnCantUnidad.getValue().toString()));
						
						for(int i=0;i<listModelCompra.size();i++) {
							Componente comp=Tienda.getInstance().buscarComponenteById(listModelCompra.get(i).toString());
							nuevaorden.getComponentes().add(comp);
						}
						Tienda.getInstance().insertarOrdenCompra(nuevaorden);
						clean();
						 }else {
							 Suministrador s=Tienda.getInstance().buscarSumiById(cbxSumi.getSelectedItem().toString());
							 selected.setSuministrador(s);
							 selected.setId(txtId.getText());
							 selected.setCantUnidades(Integer.valueOf(spnCantUnidad.getValue().toString()));
							 for(int i=0;i<listModelCompra.size();i++) {
								 Componente c=Tienda.getInstance().buscarComponenteById(listModelCompra.get(i).toString());
								 selected.getComponentes().set(i, c);
							 }
							 dispose();
							 Tienda.getInstance().modificarOrdenCompra(selected);
							 ListOrdenCompra.loadTable();
						 }
					 }else {
						 JOptionPane.showMessageDialog(null,"necesita al menos 1 componente para la orden","Aviso",JOptionPane.WARNING_MESSAGE);
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
		loadComponentes();
		loadcbxSumi();
		loadOrdenCompra();
	}

	private void loadOrdenCompra() {
		if(selected!=null) {
			txtId.setText(selected.getId());
			cbxSumi.setSelectedItem(selected.getSuministrador().getId());
			spnCantUnidad.setValue(selected.getCantUnidades());
			for(Componente c:selected.getComponentes()) {
				listModelCompra.addElement(c.getId());
		}
		}
		
	}

	private void loadcbxSumi() {
		cbxSumi.removeAll();
		for(Suministrador sumi:Tienda.getInstance().getSuministradores()) {
			String aux=new String(sumi.getId());
			cbxSumi.addItem(aux);
		}
		
	}

	private void loadComponentes() {
		listModelComp.removeAllElements();
		for(Componente com:Tienda.getInstance().getInventario()) {
			String aux=new String(com.getId());
			listModelComp.addElement(aux);
		}
		
	}
	
	private void clean() {
		txtId.setText("OC-"+OrdenCompra.cod);
		cbxSumi.setSelectedIndex(0);
		spnCantUnidad.setValue(1);
		listModelCompra.removeAllElements();
	}
}
