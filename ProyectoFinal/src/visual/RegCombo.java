package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Combo;
import logico.Componente;
import logico.Tienda;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegCombo extends JDialog {
	private JButton btnRegistrar;
	private JTextField txtId;
	private JTextField txtNombre;
	private JButton btnDerecha;
	private JButton btnIzquierda;
	private JList listCombo;
	private JList listDisponible;
	private DefaultListModel listModelDisp;
	private DefaultListModel listModelCombo;
	private JSpinner spnDescuento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegCombo dialog = new RegCombo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegCombo() {
		setTitle("Registrar Combo");
		setBounds(100, 100, 447, 368);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(listModelCombo.size()>=2) {
						Combo combo=new Combo(txtId.getText(), txtNombre.getText(),new Integer(spnDescuento.getValue().toString()));
						for(int i=0;i<listModelCombo.size();i++) {
							String id=new String(listModelCombo.get(i).toString());
							Componente comp=Tienda.getInstance().buscarComponenteById(id);
							combo.agregarComponente(comp);
						}
						Tienda.getInstance().insertarCombo(combo);
						JOptionPane.showMessageDialog(null,"Nuvo combo registrado con exito","Registrar Combo",JOptionPane.INFORMATION_MESSAGE);
						clean();
						}else {
							JOptionPane.showMessageDialog(null,"Necesita al menos 2 componentes para registrar un combo","Registrar Combo",JOptionPane.ERROR_MESSAGE);
						}
						}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Id:");
				lblNewLabel.setBounds(10, 23, 46, 14);
				panel.add(lblNewLabel);
			}
			{
				txtId = new JTextField();
				txtId.setText(String.valueOf(Combo.cod));
				txtId.setEditable(false);
				txtId.setBounds(35, 20, 202, 20);
				panel.add(txtId);
				txtId.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre:");
				lblNewLabel_1.setBounds(10, 59, 61, 14);
				panel.add(lblNewLabel_1);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setBounds(59, 56, 211, 20);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Descuento:");
				lblNewLabel_2.setBounds(10, 101, 74, 14);
				panel.add(lblNewLabel_2);
			}
			{
				spnDescuento = new JSpinner();
				spnDescuento.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				spnDescuento.setBounds(78, 98, 147, 20);
				panel.add(spnDescuento);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Componente Disponibles:");
				lblNewLabel_3.setBounds(10, 136, 127, 14);
				panel.add(lblNewLabel_3);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 149, 127, 136);
				panel.add(scrollPane);
				{
					listModelDisp=new DefaultListModel();
					listDisponible = new JList();
					listDisponible.setModel(listModelDisp);
					listDisponible.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index=-1;
							index=listDisponible.getSelectedIndex();
							if(index!=-1){
								btnDerecha.setEnabled(true);
							}
						}
					});
					listDisponible.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(listDisponible);
				}
			}
			{
				btnDerecha = new JButton(">>");
				btnDerecha.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						listModelCombo.addElement(listDisponible.getSelectedValue().toString());
						btnDerecha.setEnabled(false);
					}
				});
				btnDerecha.addMouseListener(new MouseAdapter() {
				});
				btnDerecha.setEnabled(false);
				btnDerecha.setBounds(147, 176, 89, 23);
				panel.add(btnDerecha);
			}
			{
				btnIzquierda = new JButton("<<");
				btnIzquierda.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						listModelCombo.removeElement(listCombo.getSelectedValue());
						btnIzquierda.setEnabled(false);
					}
				});
				btnIzquierda.addMouseListener(new MouseAdapter() {
				});
				btnIzquierda.setEnabled(false);
				btnIzquierda.setBounds(147, 245, 89, 23);
				panel.add(btnIzquierda);
			}
			{
				JLabel lblNewLabel_4 = new JLabel(" Componente Combo:");
				lblNewLabel_4.setBounds(246, 136, 121, 14);
				panel.add(lblNewLabel_4);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(246, 149, 147, 136);
				panel.add(scrollPane);
				{
					listModelCombo=new DefaultListModel();
					listCombo = new JList();
					listCombo.setModel(listModelCombo);
					listCombo.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index=-1;
							index=listCombo.getSelectedIndex();
							if(index!=-1){
								btnIzquierda.setEnabled(true);
							}
						}
					});
					listCombo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(listCombo);
				}
			}
		}
		loadComponenteDisponibles();
	}

	private void loadComponenteDisponibles() {
		String aux;
		listModelDisp.removeAllElements();
		for(Componente c:Tienda.getInstance().getInventario()) {
			aux=new String(c.getId());
			listModelDisp.addElement(aux);
		}
		
	}
	
	private void clean() {
		txtId.setText(String.valueOf(Combo.cod));
		txtNombre.setText("");
		spnDescuento.setValue(new Integer(0));
		listModelCombo.removeAllElements();
	}

}
