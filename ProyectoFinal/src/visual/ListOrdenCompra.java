package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.OrdenCompra;
import logico.Tienda;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ListOrdenCompra extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] rows;
	private OrdenCompra selected;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnPedido;
	private JPanel panel;
	private JComboBox<String> cbxFiltro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListOrdenCompra dialog = new ListOrdenCompra();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListOrdenCompra() {
		setTitle("Listar Orden de Compra");
		setBounds(100, 100, 443, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(5, 50, 418, 267);
			contentPanel.add(scrollPane);
			{
				String[] headers = {"Id","Suministrador","Cant. Componentes","Cant. Unidades","Fecha"};
				model=new DefaultTableModel();
				model.setColumnIdentifiers(headers);
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index =- 1;
						index = table.getSelectedRow();
						if(index != -1)
						{
							btnEliminar.setEnabled(true);
							
							String id = model.getValueAt(index,0).toString();
							selected = Tienda.getInstance().buscarOrdenById(id);
							
							if (selected.isPendiente()) {
								btnPedido.setEnabled(true);
								btnModificar.setEnabled(true);
							} else {
								btnPedido.setEnabled(false);
								btnModificar.setEnabled(false);
							}
						}
					}
				});
				table.setModel(model);
				scrollPane.setViewportView(table);
			}
		}
		{
			panel = new JPanel();
			panel.setBounds(5, 5, 418, 42);
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Filtro:");
			lblNewLabel.setBounds(15, 11, 69, 20);
			panel.add(lblNewLabel);
			
			cbxFiltro = new JComboBox<String>();
			cbxFiltro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					loadTable(cbxFiltro.getSelectedIndex());
					
					btnEliminar.setEnabled(false);
					btnModificar.setEnabled(false);
					btnPedido.setEnabled(false);
				}
			});
			cbxFiltro.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Pendientes", "Completas"}));
			cbxFiltro.setBounds(68, 6, 150, 30);
			panel.add(cbxFiltro);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				{
					btnPedido = new JButton("Hacer Pedido");
					btnPedido.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							int option = JOptionPane.showConfirmDialog(null, "Desea finalizar el pedido de la orden seleccionada: " + selected.getId() + "?", "Hacer Pedido", JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
								Tienda.getInstance().finalizarOrden(selected);
								loadTable(cbxFiltro.getSelectedIndex());
							}
							
							btnEliminar.setEnabled(false);
							btnModificar.setEnabled(false);
							btnPedido.setEnabled(false);
						}
					});
					btnPedido.setEnabled(false);
					buttonPane.add(btnPedido);
				}
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegOrdenCompra modOrden = new RegOrdenCompra(selected);
						modOrden.setModal(true);
						modOrden.setVisible(true);
						
						btnPedido.setEnabled(false);
						btnEliminar.setEnabled(false);
						btnModificar.setEnabled(false);
					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int option = JOptionPane.showConfirmDialog(null, "Desea eliminar la orden seleccionada: " + selected.getId() + "?", "Eliminar Orden de Compra", JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.YES_OPTION) {
							Tienda.getInstance().eliminarOrdenCompra(selected);
							loadTable(cbxFiltro.getSelectedIndex());
						}
						
						btnEliminar.setEnabled(false);
						btnModificar.setEnabled(false);
						btnPedido.setEnabled(false);

					}
				});
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);
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
			for (OrdenCompra ord : Tienda.getInstance().getOrdenesCompra()) {
				rows[0] = ord.getId();
				rows[1] = ord.getSuministrador().getNombre();
				rows[2] = ord.getComponentes().size();
				rows[3] = ord.getCantUnidades();
				
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
				String strFecha = dateFormat.format(ord.getFecha()); 
				rows[4] = strFecha;

				model.addRow(rows);
			}
			break;

		case 1:
			for (OrdenCompra ord : Tienda.getInstance().getOrdenesCompra()) {
				if (ord.isPendiente()) {
					rows[0] = ord.getId();
					rows[1] = ord.getSuministrador().getNombre();
					rows[2] = ord.getComponentes().size();
					rows[3] = ord.getCantUnidades();
					
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
					String strFecha = dateFormat.format(ord.getFecha()); 
					rows[4] = strFecha;

					model.addRow(rows);
				}
			}
			break;
			
		case 2:
			for (OrdenCompra ord : Tienda.getInstance().getOrdenesCompra()) {
				if (!ord.isPendiente()) {
					rows[0] = ord.getId();
					rows[1] = ord.getSuministrador().getNombre();
					rows[2] = ord.getComponentes().size();
					rows[3] = ord.getCantUnidades();
					
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
					String strFecha = dateFormat.format(ord.getFecha()); 
					rows[4] = strFecha;

					model.addRow(rows);
				}
			}
			break;
		}
	}
}
