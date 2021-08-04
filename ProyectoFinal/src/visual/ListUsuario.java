package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Administrador;
import logico.Tienda;
import logico.Usuario;
import logico.Vendedor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListUsuario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object rows[];
	private Usuario selected = null;
	private JButton btnEliminar;
	private JButton btnInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListUsuario dialog = new ListUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListUsuario() {
		setTitle("Listado de Usuarios");
		setBounds(100, 100, 450, 300);
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
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					String headers[] = {"Codigo", "Nombre", "Username", "Tipo"};
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

								String id = (String)(model.getValueAt(index, 0));
								selected = Tienda.getInstance().buscarUsuarioById(id);
								
								if (selected instanceof Vendedor) {
									btnInfo.setEnabled(true);
								} else {
									btnInfo.setEnabled(false);
								}
							}
						}
					});
					table.setModel(model);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(table);
				}
			}
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
					btnEliminar = new JButton("Eliminar");
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int option = JOptionPane.showConfirmDialog(null, "Desea eliminar el usuario seleccionado: " + selected.getId() + "|" + selected.getNombre() + "?", "Eliminar usuario", JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
								if (!Tienda.getInstance().eliminarUsuario(selected)) {
									JOptionPane.showMessageDialog(null, "Debe haber por lo menos un Usuario registrado", "Eliminar Usuario", JOptionPane.WARNING_MESSAGE);
								}
								loadTable();
							}
							
							btnEliminar.setEnabled(false);
							btnInfo.setEnabled(false);
						}
					});
					{
						btnInfo = new JButton("Info");
						btnInfo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								JOptionPane.showMessageDialog(null, "% Comision: " + ((Vendedor) selected).getComision() + "\nCant. Ventas: " +
										((Vendedor) selected).getCantVentas() + "\nTotal Vendido: $" + ((Vendedor) selected).getVentas() + "\nComision: $" +
										((Vendedor) selected).calculoComision(),"Informacion de Vendedor", JOptionPane.INFORMATION_MESSAGE);
								
								btnInfo.setEnabled(false);
								btnEliminar.setEnabled(false);
							}
						});
						btnInfo.setEnabled(false);
						buttonPane.add(btnInfo);
					}
					btnEliminar.setEnabled(false);
					buttonPane.add(btnEliminar);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadTable();
	}

	public static void loadTable() {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		for (Usuario c : Tienda.getInstance().getUsuarios()) {
			
			rows[0] = c.getId();
			rows[1] = c.getNombre();
			rows[2] = c.getUserName();
			
			if (c instanceof Vendedor) {
				rows[3] = "Vendedor";
			}
			
			if (c instanceof Administrador) {
				rows[3] = "Admin";
			}
					
			model.addRow(rows);
			
		}
	}

}
