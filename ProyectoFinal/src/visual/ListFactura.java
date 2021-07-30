package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Factura;
import logico.Tienda;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] rows;
	private Factura selected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListFactura dialog = new ListFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListFactura() {
		setTitle("Listar Facturas");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				String[] headers= {"Id","Cliente","Cant. Venta","Descuento","Fecha"};
				model=new DefaultTableModel();
				model.setColumnIdentifiers(headers);
				table= new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index=-1;
						index=table.getSelectedRow();
						if(index!=-1)
						{
							String id=(String)(model.getValueAt(index,0));
							selected=Tienda.getInstance().buscarFacturaById(id);
						}
					}
				});
				table.setModel(model);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
		loadTable();
	}

	public static void loadTable() {
		model.setRowCount(0);
		rows=new Object[model.getColumnCount()];
		for(Factura f:Tienda.getInstance().getFacturas()) {
			rows[0]=f.getId();
			rows[1]=f.getCliente().getNombre();
			rows[2]=f.getVenta().size();
			rows[3]=f.getDescuento();
			rows[4]=f.getFecha();
			model.addRow(rows);
		}
	}

}
