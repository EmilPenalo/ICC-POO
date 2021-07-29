package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Componente;
import logico.Suministrador;
import logico.Tienda;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegSuministrador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JComboBox cbxPais;
	private JButton btnDerecha;
	private JButton btnIzquierda;
	private JButton btnRegistrar;
	private JList listComponente;
	private JList listSuministro;
	private DefaultListModel listModelComp;
	private DefaultListModel listModelSumi;
	private Suministrador selected;
	private JSpinner spnEntrega;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			RegSuministrador dialog = new RegSuministrador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public RegSuministrador(Suministrador suministrador) {
		selected=suministrador;
		if(selected==null) {
		setTitle("Registrar Suministrador");
		}else {
			setTitle("Modificar Suministrador");
		}
		setBounds(100, 100, 471, 402);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Id:");
			lblNewLabel.setBounds(10, 22, 46, 14);
			panel.add(lblNewLabel);
			
			txtId = new JTextField();
			txtId.setText("S-"+Suministrador.cod);
			txtId.setEditable(false);
			txtId.setBounds(33, 19, 156, 20);
			panel.add(txtId);
			txtId.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(10, 73, 46, 14);
			panel.add(lblNewLabel_1);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(52, 70, 239, 20);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Pa\u00EDs:");
			lblNewLabel_2.setBounds(10, 117, 35, 14);
			panel.add(lblNewLabel_2);
			
			cbxPais = new JComboBox();
			cbxPais.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Estados Unidos", "Alemania", "China", "Francia", "Espa\u00F1a", "India", "C\u00E1nada"}));
			cbxPais.setBounds(43, 114, 210, 20);
			panel.add(cbxPais);
			
			JLabel lblNewLabel_3 = new JLabel("Componentes:");
			lblNewLabel_3.setBounds(10, 157, 77, 14);
			panel.add(lblNewLabel_3);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 170, 110, 139);
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
			listComponente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(listComponente);
			
			JLabel lblNewLabel_4 = new JLabel("Suministro:");
			lblNewLabel_4.setBounds(265, 157, 61, 14);
			panel.add(lblNewLabel_4);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(244, 170, 117, 139);
			panel.add(scrollPane_1);
			
			listModelSumi=new DefaultListModel();
			listSuministro = new JList();
			listSuministro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listSuministro.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index =- 1;
					index = listSuministro.getSelectedIndex();
					if (index != -1){
						btnIzquierda.setEnabled(true);
					}
				}
			});
			listSuministro.setModel(listModelSumi);
			scrollPane_1.setViewportView(listSuministro);
			
			btnDerecha = new JButton(">>");
			btnDerecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String aux=listComponente.getSelectedValue().toString();
					listModelSumi.addElement(aux);
					btnDerecha.setEnabled(false);
				}
			});
			btnDerecha.setEnabled(false);
			btnDerecha.setBounds(134, 207, 89, 23);
			panel.add(btnDerecha);
			
			btnIzquierda = new JButton("<<");
			btnIzquierda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String aux=listSuministro.getSelectedValue().toString();
					listModelSumi.removeElement(aux);
					btnIzquierda.setEnabled(false);
				}
			});
			btnIzquierda.setEnabled(false);
			btnIzquierda.setBounds(134, 262, 89, 23);
			panel.add(btnIzquierda);
			
			JLabel lblNewLabel_5 = new JLabel("Entrega:");
			lblNewLabel_5.setBounds(263, 117, 46, 14);
			panel.add(lblNewLabel_5);
			
			spnEntrega = new JSpinner();
			spnEntrega.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnEntrega.setBounds(308, 114, 127, 20);
			panel.add(spnEntrega);
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
						if(listModelSumi.size()>0) {
							if(selected==null) {
						Suministrador sumi=new Suministrador(txtId.getText(),txtNombre.getText(),cbxPais.getSelectedItem().toString(),Integer.valueOf(spnEntrega.getValue().toString()));
						for(int i=0;i<listModelSumi.size();i++) {
							String id=listModelSumi.get(i).toString();
							Componente comp=Tienda.getInstance().buscarComponenteById(id);
							sumi.getComps().add(comp);
						}
						Tienda.getInstance().insertarSuministrador(sumi);
						JOptionPane.showMessageDialog(null,"Nuevo suministrador registrado con exito","Registrar suministrador",JOptionPane.INFORMATION_MESSAGE);
						clean();
							}else {
								selected.setId(txtId.getText());
								selected.setNombre(txtNombre.getText());
								selected.setPais(cbxPais.getSelectedItem().toString());
								
								for(int i=0;i<listModelSumi.size();i++) {
									String id=listModelSumi.get(i).toString();
									Componente comp=Tienda.getInstance().buscarComponenteById(id);
									selected.getComps().set(i,comp);
							}
								Tienda.getInstance().modificarSuministrador(selected);
								dispose();
								ListSuministrador.loadTable();
							}
						}else {
							JOptionPane.showMessageDialog(null,"necesita al menos 1 componente para Para continuar","Aviso", JOptionPane.WARNING_MESSAGE);
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
	}
	private void loadComponentes() {
		listModelComp.removeAllElements();
		for(Componente c:Tienda.getInstance().getInventario()) {
			String aux=new String(c.getId());
			listModelComp.addElement(aux);
		}
		
	}
	private void clean() {
		txtId.setText("S-"+Suministrador.cod);
		txtNombre.setText("");
		spnEntrega.setValue(1);
		listModelSumi.removeAllElements();
	}
	
	private void loadSumi() {
		if(selected!=null) {
		txtId.setText(selected.getId());
		txtNombre.setText(selected.getNombre());
		cbxPais.setSelectedItem(selected.getPais());
		spnEntrega.setValue(selected.getEntrega());
		
		for(int i=0;i<selected.getComps().size();i++) {
			String aux=new String(selected.getComps().get(i).toString());
			listModelSumi.setElementAt(aux, i);
		}
		}
	}
}
