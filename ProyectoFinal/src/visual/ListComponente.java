package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Componente;
import logico.DiscoDuro;
import logico.MicroProcesador;
import logico.MotherBoard;
import logico.Ram;
import logico.Tienda;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ListComponente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object rows[];
	private Componente selected = null;
	private JButton btnEliminar;
	private JButton btnModificar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListComponente dialog = new ListComponente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListComponente() {
		setTitle("Inventario de Componentes");
		setBounds(100, 100, 525, 325);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 52, 499, 190);
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					String headers[] = {"Codigo", "Marca", "Serial", "En inventario", "Precio", "Tipo"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(headers);
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = -1;
							index = table.getSelectedRow();
							
							if (index != -1) {
								btnEliminar.setEnabled(true);
								btnModificar.setEnabled(true);
								
								String id = (String)(model.getValueAt(index, 0));
								selected = Tienda.getInstance().buscarComponenteById(id);
							}
						}
					});
					table.setModel(model);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(table);
				}
			}
		}
		
		JPanel panelTipo = new JPanel();
		panelTipo.setBounds(5, 0, 499, 51);
		contentPanel.add(panelTipo);
		panelTipo.setLayout(null);
		{
			JLabel lblTipoDeComponente = new JLabel("Tipo de Componente:");
			lblTipoDeComponente.setBounds(15, 21, 161, 20);
			panelTipo.add(lblTipoDeComponente);
		}
		{
			JComboBox cbxTipo = new JComboBox();
			cbxTipo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selection = cbxTipo.getSelectedIndex();
					loadTable(selection);
				}
			});
			cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Disco Duro", "Micro Procesador", "RAM", "MotherBoard"}));
			cbxTipo.setBounds(170, 16, 146, 30);
			panelTipo.add(cbxTipo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnModificar = new JButton("Modificar");
					btnModificar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							RegComponente aux = new RegComponente(selected);
							aux.setModal(true);
							aux.setVisible(true);
							
							btnEliminar.setEnabled(false);
							btnModificar.setEnabled(false);
						}
					});
					btnModificar.setEnabled(false);
					buttonPane.add(btnModificar);
				}
				{
					btnEliminar = new JButton("Eliminar");
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int option = JOptionPane.showConfirmDialog(null, "Desea eliminar el componente seleccionado: " + selected.getId() + "?", "Eliminar componente", JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
								Tienda.getInstance().eliminarComponente(selected);
								loadTable(0);
							}
							
							btnEliminar.setEnabled(false);
							btnModificar.setEnabled(false);
						}
					});
					btnEliminar.setEnabled(false);
					buttonPane.add(btnEliminar);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadTable(0);
	}

	public static void loadTable(int sel) {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		
		switch (sel) {
		case 0:
			for (Componente c : Tienda.getInstance().getInventario()) {
				
				rows[0] = c.getId();
				rows[1] = c.getMarca();
				rows[2] = c.getSerial();
				rows[3] = c.getCantReal();
				rows[4] = c.getPrecio();
				
				if (c instanceof DiscoDuro) {
					rows[5] = "Disco Duro";
				}
				
				if (c instanceof MotherBoard) {
					rows[5] = "MotherBoard";
				}
				
				if (c instanceof MicroProcesador) {
					rows[5] = "Micro";
				}
				
				if (c instanceof Ram) {
					rows[5] = "RAM";
				}
						
				model.addRow(rows);
			}
			break;
			
		case 1:
			for (Componente c : Tienda.getInstance().getInventario()) {
				if (c instanceof DiscoDuro) {
					rows[0] = c.getId();
					rows[1] = c.getMarca();
					rows[2] = c.getSerial();
					rows[3] = c.getCantReal();
					rows[4] = c.getPrecio();
					rows[5] = "Disco Duro";
							
					model.addRow(rows);
				}
			}
			break;
			
		case 2:
			for (Componente c : Tienda.getInstance().getInventario()) {
				if (c instanceof MicroProcesador) {
					rows[0] = c.getId();
					rows[1] = c.getMarca();
					rows[2] = c.getSerial();
					rows[3] = c.getCantReal();
					rows[4] = c.getPrecio();
					rows[5] = "Micro";
							
					model.addRow(rows);
				}
			}
			break;
			
		case 3:
			for (Componente c : Tienda.getInstance().getInventario()) {
				if (c instanceof Ram) {
					rows[0] = c.getId();
					rows[1] = c.getMarca();
					rows[2] = c.getSerial();
					rows[3] = c.getCantReal();
					rows[4] = c.getPrecio();
					rows[5] = "RAM";
							
					model.addRow(rows);
				}
			}
			break;
			
		case 4:
			for (Componente c : Tienda.getInstance().getInventario()) {
				if (c instanceof MotherBoard) {
					rows[0] = c.getId();
					rows[1] = c.getMarca();
					rows[2] = c.getSerial();
					rows[3] = c.getCantReal();
					rows[4] = c.getPrecio();
					rows[5] = "MotherBoard";
							
					model.addRow(rows);
				}
			}
			break;
		}
	}
}
