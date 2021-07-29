package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import logico.Combo;
import logico.Componente;
import logico.Tienda;

import javax.swing.BorderFactory;
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
import javax.swing.border.TitledBorder;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import java.awt.Color;

public class RegCombo extends JDialog {
	private JButton btnRegistrar;
	private JTextField txtId;
	private JTextField txtNombre;
	private JButton btnDerecha;
	private JButton btnIzquierda;
	private JList<String> listCombo;
	private JList<String> listDisponible;
	private DefaultListModel<String> listModelDisp;
	private DefaultListModel<String> listModelCombo;
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
		setBounds(100, 100, 447, 470);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Codigo:");
				lblNewLabel.setBounds(15, 16, 56, 20);
				panel.add(lblNewLabel);
			}
			{
				txtId = new JTextField();
				txtId.setText("CC-" + Combo.cod);
				txtId.setEditable(false);
				txtId.setBounds(84, 11, 146, 30);
				panel.add(txtId);
				txtId.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre:");
				lblNewLabel_1.setBounds(15, 59, 71, 20);
				panel.add(lblNewLabel_1);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setBounds(84, 54, 326, 30);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Descuento:");
				lblNewLabel_2.setBounds(15, 101, 89, 20);
				panel.add(lblNewLabel_2);
			}
			{
				spnDescuento = new JSpinner();
				spnDescuento.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				spnDescuento.setBounds(84, 96, 146, 30);
				panel.add(spnDescuento);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Disponibles:");
				lblNewLabel_3.setBounds(15, 158, 163, 20);
				panel.add(lblNewLabel_3);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(15, 194, 147, 184);
				panel.add(scrollPane);
				{
					listModelDisp = new DefaultListModel<String>();
					listDisponible = new JList<String>();
					listDisponible.setModel(listModelDisp);
					listDisponible.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index =- 1;
							index = listDisponible.getSelectedIndex();
							if (index != -1){
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
				btnDerecha.setBounds(168, 239, 89, 30);
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
				btnIzquierda.setBounds(168, 308, 89, 30);
				panel.add(btnIzquierda);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Seleccionados:");
				lblNewLabel_4.setBounds(263, 158, 121, 20);
				panel.add(lblNewLabel_4);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(263, 194, 147, 184);
				panel.add(scrollPane);
				{
					listModelCombo = new DefaultListModel<String>();
					listCombo = new JList<String>();
					listCombo.setModel(listModelCombo);
					listCombo.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index =- 1;
							index = listCombo.getSelectedIndex();
							if (index != -1){
								btnIzquierda.setEnabled(true);
							}
						}
					});
					listCombo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(listCombo);
				}
			}
			
			JSeparator separator = new JSeparator();
			separator.setBounds(15, 140, 395, 2);
			panel.add(separator);
		}
		loadComponenteDisponibles();
	}

	private void loadComponenteDisponibles() {
		String aux;
		listModelDisp.removeAllElements();
		for (Componente c:Tienda.getInstance().getInventario()) {
			aux = new String(c.getId());
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
