package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.Factura;
import logico.FacturaCredito;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ListFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] rows;
	private Factura selected;
	private static Cliente cliente = null;
	private JComboBox<String> cbxFiltro;
	private JButton btnCompletar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListFactura dialog = new ListFactura(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListFactura(Cliente clienteDado) {
		cliente = clienteDado;
		
		setTitle("Listar Facturas");
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelTable = new JPanel();
			panelTable.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelTable.setBounds(5, 55, 574, 261);
			contentPanel.add(panelTable);
			panelTable.setLayout(null);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(0, 0, 574, 261);
			panelTable.add(scrollPane);
			
			table = new JTable();
			String[] headers= {"Id","Cliente","Total","Descuento","Fecha"};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(headers);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = -1;
					index = table.getSelectedRow();
					if(index != -1) {
 							String id = (String)(model.getValueAt(index,0));
						selected = Tienda.getInstance().buscarFacturaById(id);
						
						if (selected instanceof FacturaCredito) {
							if (((FacturaCredito) selected).isPendiente()) {
								btnCompletar.setEnabled(true);
							} else {
								btnCompletar.setEnabled(false);
							}
						} else {
							btnCompletar.setEnabled(false);
						}
					}
				}
			});
			table.setModel(model);
			scrollPane.setViewportView(table);

			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(5, 5, 574, 44);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Filtro:");
			lblNewLabel.setBounds(15, 12, 69, 20);
			panel.add(lblNewLabel);
			
			cbxFiltro = new JComboBox<String>();
			cbxFiltro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnCompletar.setEnabled(false);
					loadTable(cbxFiltro.getSelectedIndex());
				}
			});
			cbxFiltro.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Pendientes", "Completas"}));
			cbxFiltro.setBounds(70, 7, 182, 30);
			panel.add(cbxFiltro);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				btnCompletar = new JButton("Completar Pago");
				btnCompletar.setEnabled(false);
				btnCompletar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int option = JOptionPane.showConfirmDialog(null, "Desea confirmar el pago de esta factura: " + selected.getId() + "?", "Pago de Factura", JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.YES_OPTION) {
							Tienda.getInstance().completarFactura((FacturaCredito) selected);
							loadTable(cbxFiltro.getSelectedIndex());
						}
						
						btnCompletar.setEnabled(false);
					}
				});
				buttonPane.add(btnCompletar);
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
			if (cliente == null) {
				for (Factura f : Tienda.getInstance().getFacturas()) {
					
					rows[0] = f.getId();
					rows[1] = f.getCliente().getNombre();
					rows[2] = f.precioTotal();
					rows[3] = f.getDescuento();
					
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
					String strFecha = dateFormat.format(f.getFecha()); 
					rows[4] = strFecha;
					
					model.addRow(rows);
				}
			} else {
				for (Factura f : Tienda.getInstance().getFacturas()) {
					if (f.getCliente().getCedula().equalsIgnoreCase(cliente.getCedula())) {
						rows[0] = f.getId();
						rows[1] = f.getCliente().getNombre();
						rows[2] = f.precioTotal();
						rows[3] = f.getDescuento();
						
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
						String strFecha = dateFormat.format(f.getFecha()); 
						rows[4] = strFecha;
						
						model.addRow(rows);
					}
				}
			}
			break;

		case 1:
			if (cliente == null) {
				for (Factura f : Tienda.getInstance().getFacturas()) {
					
					if (f instanceof FacturaCredito) {
						if (((FacturaCredito) f).isPendiente()) {
							
							rows[0] = f.getId();
							rows[1] = f.getCliente().getNombre();
							rows[2] = f.precioTotal();
							rows[3] = f.getDescuento();

							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
							String strFecha = dateFormat.format(f.getFecha()); 
							rows[4] = strFecha;
							
							model.addRow(rows);
						}
					}
				}
			} else {
				for (Factura f : Tienda.getInstance().getFacturas()) {
					if (f.getCliente().getCedula().equalsIgnoreCase(cliente.getCedula())) {
						
						if (f instanceof FacturaCredito) {
							if (((FacturaCredito) f).isPendiente()) {
								
								rows[0] = f.getId();
								rows[1] = f.getCliente().getNombre();
								rows[2] = f.precioTotal();
								rows[3] = f.getDescuento();

								DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
								String strFecha = dateFormat.format(f.getFecha()); 
								rows[4] = strFecha;
								
								model.addRow(rows);
							}
						}
					}
				}
			}
			break;
			
		case 2:
			if (cliente == null) {
				for (Factura f : Tienda.getInstance().getFacturas()) {
					
					if (f instanceof FacturaCredito) {
						if (!((FacturaCredito) f).isPendiente()) {
							
							rows[0] = f.getId();
							rows[1] = f.getCliente().getNombre();
							rows[2] = f.precioTotal();
							rows[3] = f.getDescuento();

							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
							String strFecha = dateFormat.format(f.getFecha()); 
							rows[4] = strFecha;
							
							model.addRow(rows);
						}
					} else {
						
						rows[0] = f.getId();
						rows[1] = f.getCliente().getNombre();
						rows[2] = f.precioTotal();
						rows[3] = f.getDescuento();

						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
						String strFecha = dateFormat.format(f.getFecha()); 
						rows[4] = strFecha;
						
						model.addRow(rows);
					}
				}
			} else {
				for (Factura f : Tienda.getInstance().getFacturas()) {
					if (f.getCliente().getCedula().equalsIgnoreCase(cliente.getCedula())) {
						
						if (f instanceof FacturaCredito) {
							if (!((FacturaCredito) f).isPendiente()) {
								
								rows[0] = f.getId();
								rows[1] = f.getCliente().getNombre();
								rows[2] = f.precioTotal();
								rows[3] = f.getDescuento();

								DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
								String strFecha = dateFormat.format(f.getFecha()); 
								rows[4] = strFecha;
								
								model.addRow(rows);
							}
						} else {
							
							rows[0] = f.getId();
							rows[1] = f.getCliente().getNombre();
							rows[2] = f.precioTotal();
							rows[3] = f.getDescuento();
							
							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
							String strFecha = dateFormat.format(f.getFecha()); 
							rows[4] = strFecha;
							
							model.addRow(rows);
						}
					}
				}
			}
			break;
			
		}
	}
}
