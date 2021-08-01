package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.Tienda;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class ListCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JButton btnEliminar;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] rows;
	private Cliente selected;
	private static JButton btnModificar;
	private static JButton btnHistorial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListCliente dialog = new ListCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListCliente() {
		setTitle("Listar Cliente");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				String[] headers= {"Cédula","Nombre","Dirección","Teléfono","Crédito Total"};
				model=new DefaultTableModel();
				model.setColumnIdentifiers(headers);
				table = new JTable();
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = -1;
						index = table.getSelectedRow();
						if(index != -1) {
							btnModificar.setEnabled(true);
							btnEliminar.setEnabled(true);
							btnHistorial.setEnabled(true);
							String cedula = (String)(model.getValueAt(index,0));
							selected = Tienda.getInstance().buscarClienteByCedula(cedula);
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
				btnHistorial = new JButton("Historial");
				btnHistorial.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ListFactura historial = new ListFactura(selected);
						historial.setModal(true);
						historial.setVisible(true);
						
						btnEliminar.setEnabled(false);
						btnModificar.setEnabled(false);
						btnHistorial.setEnabled(false);
					}
				});
				btnHistorial.setEnabled(false);
				buttonPane.add(btnHistorial);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegCliente aux = new RegCliente(selected);
						aux.setModal(true);
						aux.setVisible(true);
						
						btnEliminar.setEnabled(false);
						btnModificar.setEnabled(false);
						btnHistorial.setEnabled(false);
					}
				});
				btnModificar.setEnabled(false);
				buttonPane.add(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int option = JOptionPane.showConfirmDialog(null, "Desea eliminar el cliente seleccionado: " + selected.getCedula() + "?", "Eliminar Cliente", JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.YES_OPTION) {
							Tienda.getInstance().eliminarCliente(selected);
							loadTable();
						}
						
						btnEliminar.setEnabled(false);
						btnModificar.setEnabled(false);
						btnHistorial.setEnabled(false);
					}
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
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
		for(Cliente c:Tienda.getInstance().getClientes()) {
			rows[0]=c.getCedula();
			rows[1]=c.getNombre();
			rows[2]=c.getDireccion();
			rows[3]=c.getTelefono();
			rows[4]=c.getCredito();
			model.addRow(rows);
		}
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnHistorial.setEnabled(false);
	}

}
