package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Suministrador;
import logico.Tienda;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListSuministrador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JButton btnModificar;
	private static JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] rows;
	private static Suministrador selected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListSuministrador dialog = new ListSuministrador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListSuministrador() {
		setTitle("Listar Suministrador");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				String[] headers= {"Id","Nombre","País","Cant. Componentes","Entrega"};
				model=new DefaultTableModel();
				model.setColumnIdentifiers(headers);
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index=-1;
						index=table.getSelectedRow();
						if(index!=-1)
						{
							btnModificar.setEnabled(true);
							btnEliminar.setEnabled(true);
							String id=(String)(model.getValueAt(index,0));
							selected=Tienda.getInstance().buscarSumiById(id);
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
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Tienda.getInstance().eliminarSuministrador(selected);
						loadTable();
					}
				});
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegSuministrador modSumi=new RegSuministrador(selected);
						modSumi.setModal(true);
						modSumi.setVisible(true);
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadTable();
	}

	public static void loadTable() {
		model.setRowCount(0);
		rows=new Object[model.getColumnCount()];
		for(Suministrador sumi:Tienda.getInstance().getSuministradores()) {
			rows[0]=sumi.getId();
			rows[1]=sumi.getNombre();
			rows[2]=sumi.getPais();
			rows[3]=sumi.getComps().size();
			rows[4]=sumi.getEntrega();
			model.addRow(rows);
		}
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
	}

}
