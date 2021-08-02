package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logico.Componente;
import logico.DiscoDuro;
import logico.MicroProcesador;
import logico.MotherBoard;
import logico.Ram;
import logico.Tienda;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegComponente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtSerial;
	private Componente selected;
	private JTextField txtMarca;
	private JSpinner spnPrecio;
	private JSpinner spnMin;
	private JSpinner spnMax;
	private JSpinner spnReal;
	private JRadioButton rdbtnDiscoDuro;
	private JRadioButton rdbtnMicro;
	private JRadioButton rdbtnMotherBoard;
	private JRadioButton rdbtnRAM;
	private JTextField txtModeloDD;
	private JComboBox cbxTipoConexion;
	private JTextField txtModeloMicro;
	private JSpinner spnVelocidad;
	private JComboBox cbxTipoSocketMicro;
	private JTextField txtModeloMotherBoard;
	private JPanel panelMicro;
	private JPanel panelRAM;
	private JPanel panelMotherBoard;
	private JPanel panelDiscoDuro;
	private JRadioButton rdbtnGhz;
	private JRadioButton rdbtnMhz;
	private JSpinner spnCapacidad;
	private JComboBox cbxTipoSocketMotherBoard;
	private JSpinner spnMemoria;
	private JComboBox cbxTipoMemoria;
	private JComboBox cbxTipoRam;
	private JScrollPane scrollPane_1;
	private JList listSelect;
	
	private DefaultListModel<String> listModelDisp;
	private DefaultListModel<String> listModelSelect;
	private JButton btnDerecha;
	private JButton btnIzquierda;
	private JList listDisponible;
	private JRadioButton rdbtnTb;
	private JRadioButton rdbtnGbDD;
	private JRadioButton rdbtnGbRAM;
	private JRadioButton rdbtnMb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegComponente dialog = new RegComponente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegComponente(Componente comp) {
		selected = comp;
		
		if (selected == null) {
			setTitle("Registrar Componente");

		} else {
			setTitle("Modificar Componente");
		}
		
		setBounds(100, 100, 460, 744);
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
			panel.setLayout(null);
			
			panelMotherBoard = new JPanel();
			panelMotherBoard.setVisible(false);
			
			panelRAM = new JPanel();
			panelRAM.setVisible(false);
			panelRAM.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelRAM.setBounds(15, 389, 404, 132);
			panel.add(panelRAM);
			panelRAM.setLayout(null);
			
			JLabel lblNewLabel_3_2 = new JLabel("Cant. Memoria:");
			lblNewLabel_3_2.setBounds(15, 18, 134, 20);
			panelRAM.add(lblNewLabel_3_2);
			
			spnMemoria = new JSpinner();
			spnMemoria.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnMemoria.setBounds(110, 13, 146, 30);
			panelRAM.add(spnMemoria);
			
			JLabel lblNewLabel_4_2 = new JLabel("Tipo Memoria:");
			lblNewLabel_4_2.setBounds(15, 56, 109, 20);
			panelRAM.add(lblNewLabel_4_2);
			
			cbxTipoMemoria = new JComboBox();
			cbxTipoMemoria.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "SRAM", "DRAM", "SDRAM", "SDR SDRAM", "DDR SDRAM", "GDDR SDRAM", "HBM"}));
			cbxTipoMemoria.setBounds(110, 51, 146, 30);
			panelRAM.add(cbxTipoMemoria);
			
			rdbtnGbRAM = new JRadioButton("Gb");
			rdbtnGbRAM.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (rdbtnMb.isSelected()) {
						rdbtnMb.setSelected(false);
						spnMemoria.setValue(Tienda.getInstance().calcularEquivalenciaMb_a_Gb( new Float(spnMemoria.getValue().toString() )));
					}
					rdbtnGbRAM.setSelected(true);
				}
			});
			rdbtnGbRAM.setSelected(true);
			rdbtnGbRAM.setBounds(267, 14, 59, 29);
			panelRAM.add(rdbtnGbRAM);
			
			rdbtnMb = new JRadioButton("Mb");
			rdbtnMb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (rdbtnGbRAM.isSelected()) {
						rdbtnGbRAM.setSelected(false);
						spnMemoria.setValue(Tienda.getInstance().calcularEquivalenciaMb( new Float(spnMemoria.getValue().toString() )));
					}
					rdbtnMb.setSelected(true);
				}
			});
			rdbtnMb.setBounds(333, 14, 60, 29);
			panelRAM.add(rdbtnMb);
			
			panelDiscoDuro = new JPanel();
			panelDiscoDuro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDiscoDuro.setBounds(15, 389, 404, 132);
			panel.add(panelDiscoDuro);
			panelDiscoDuro.setLayout(null);
			
			JLabel lblNewLabel_2 = new JLabel("Modelo:");
			lblNewLabel_2.setBounds(15, 18, 69, 20);
			panelDiscoDuro.add(lblNewLabel_2);
			
			txtModeloDD = new JTextField();
			txtModeloDD.setBounds(110, 13, 146, 30);
			panelDiscoDuro.add(txtModeloDD);
			txtModeloDD.setColumns(10);
			
			JLabel lblNewLabel_3 = new JLabel("Capacidad:");
			lblNewLabel_3.setBounds(15, 56, 78, 20);
			panelDiscoDuro.add(lblNewLabel_3);
			
			spnCapacidad = new JSpinner();
			spnCapacidad.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
			spnCapacidad.setBounds(110, 51, 146, 30);
			panelDiscoDuro.add(spnCapacidad);
			
			JLabel lblNewLabel_4 = new JLabel("Tipo Conexion:");
			lblNewLabel_4.setBounds(15, 94, 109, 20);
			panelDiscoDuro.add(lblNewLabel_4);
			
			cbxTipoConexion = new JComboBox();
			cbxTipoConexion.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "IDE", "SCSI", "SATA", "SAS", "PCI-e"}));
			cbxTipoConexion.setBounds(110, 89, 146, 30);
			panelDiscoDuro.add(cbxTipoConexion);
			
			rdbtnGbDD = new JRadioButton("Gb");
			rdbtnGbDD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (rdbtnTb.isSelected()) {
						rdbtnTb.setSelected(false);
						spnCapacidad.setValue(Tienda.getInstance().calcularEquivalenciaTb_a_Gb( new Float(spnCapacidad.getValue().toString() )));
					}
					rdbtnGbDD.setSelected(true);
				}
			});
			rdbtnGbDD.setSelected(true);
			rdbtnGbDD.setBounds(267, 52, 59, 29);
			panelDiscoDuro.add(rdbtnGbDD);
			
			rdbtnTb = new JRadioButton("Tb");
			rdbtnTb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (rdbtnGbDD.isSelected()) {
						rdbtnGbDD.setSelected(false);
						spnCapacidad.setValue(Tienda.getInstance().calcularEquivalenciaTb( new Float(spnCapacidad.getValue().toString() )));
					}
					rdbtnTb.setSelected(true);
				}
			});
			rdbtnTb.setBounds(333, 52, 60, 29);
			panelDiscoDuro.add(rdbtnTb);
			panelMotherBoard.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelMotherBoard.setBounds(15, 389, 404, 252);
			panel.add(panelMotherBoard);
			panelMotherBoard.setLayout(null);
			
			JLabel lblNewLabel_2_2 = new JLabel("Modelo:");
			lblNewLabel_2_2.setBounds(15, 18, 69, 20);
			panelMotherBoard.add(lblNewLabel_2_2);
			
			txtModeloMotherBoard = new JTextField();
			txtModeloMotherBoard.setColumns(10);
			txtModeloMotherBoard.setBounds(110, 13, 146, 30);
			panelMotherBoard.add(txtModeloMotherBoard);
			
			JLabel lblNewLabel_4_1_1 = new JLabel("Tipo Socket:");
			lblNewLabel_4_1_1.setBounds(15, 56, 109, 20);
			panelMotherBoard.add(lblNewLabel_4_1_1);
			
			cbxTipoSocketMotherBoard = new JComboBox();
			cbxTipoSocketMotherBoard.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "PGA", "PPGA", "FC-PGA", "SPGA", "SECC", "VRM"}));
			cbxTipoSocketMotherBoard.setBounds(110, 51, 146, 30);
			panelMotherBoard.add(cbxTipoSocketMotherBoard);
			
			JLabel lblNewLabel_4_1_1_1 = new JLabel("Tipo RAM:");
			lblNewLabel_4_1_1_1.setBounds(15, 94, 109, 20);
			panelMotherBoard.add(lblNewLabel_4_1_1_1);
			
			cbxTipoRam = new JComboBox();
			cbxTipoRam.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "SRAM", "DRAM", "SDRAM", "SDR SDRAM", "DDR SDRAM", "GDDR SDRAM", "HBM"}));
			cbxTipoRam.setBounds(110, 89, 146, 30);
			panelMotherBoard.add(cbxTipoRam);
			
			JLabel lblNewLabel_5 = new JLabel("Conexiones Compatibles:");
			lblNewLabel_5.setBounds(15, 130, 179, 20);
			panelMotherBoard.add(lblNewLabel_5);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(15, 153, 146, 87);
			panelMotherBoard.add(scrollPane);
			
			listModelDisp = new DefaultListModel();
			listDisponible = new JList();
			listDisponible.setModel(listModelDisp);
			listDisponible.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = -1;
					index = listDisponible.getSelectedIndex();
					
					if (index != -1) {
						btnDerecha.setEnabled(true);
					}
				}
			});
			listDisponible.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(listDisponible);
			loadConexiones();
			
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane_1.setBounds(243, 153, 146, 87);
			panelMotherBoard.add(scrollPane_1);
			
			listModelSelect = new DefaultListModel<>();
			listSelect = new JList();
			listSelect.setModel(listModelSelect);
			listSelect.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index =- 1;
					index = listSelect.getSelectedIndex();
					if (index != -1){
						btnIzquierda.setEnabled(true);
					}
				}
			});
			listSelect.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane_1.setViewportView(listSelect);
			
			btnDerecha = new JButton(">>");
			btnDerecha.setEnabled(false);
			btnDerecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String aux = listDisponible.getSelectedValue().toString();
					listModelSelect.addElement(aux);
					listModelDisp.remove(listDisponible.getSelectedIndex());
					btnDerecha.setEnabled(false);
				}
			});
			btnDerecha.setBounds(173, 163, 57, 29);
			panelMotherBoard.add(btnDerecha);
			
			btnIzquierda = new JButton("<<");
			btnIzquierda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String aux = listSelect.getSelectedValue().toString();
					listModelDisp.addElement(aux);
					listModelSelect.remove(listSelect.getSelectedIndex());
					btnIzquierda.setEnabled(false);
				}
			});
			btnIzquierda.setEnabled(false);
			btnIzquierda.setBounds(173, 201, 57, 29);
			panelMotherBoard.add(btnIzquierda);
			
			JLabel lblNewLabel_6 = new JLabel("Seleccionadas:");
			lblNewLabel_6.setBounds(245, 130, 144, 20);
			panelMotherBoard.add(lblNewLabel_6);
			
			JLabel lblCodigo = new JLabel("Codigo:");
			lblCodigo.setBounds(15, 20, 69, 20);
			panel.add(lblCodigo);
			
			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			txtCodigo.setBounds(81, 15, 146, 30);
			if (selected == null) {
				txtCodigo.setText("COMP-" + Componente.cod);
			} else {
				txtCodigo.setText(selected.getId());
			}
			panel.add(txtCodigo);
			txtCodigo.setColumns(10);
			{
				JLabel lblSerial = new JLabel("Serial:");
				lblSerial.setBounds(15, 102, 69, 20);
				panel.add(lblSerial);
			}
			{
				txtSerial = new JTextField();
				txtSerial.setColumns(10);
				txtSerial.setBounds(81, 97, 330, 30);
				panel.add(txtSerial);
			}
			{
				JLabel lblPrecio = new JLabel("Precio:");
				lblPrecio.setBounds(15, 142, 112, 20);
				panel.add(lblPrecio);
			}
			
			JLabel lblMarca = new JLabel("Marca:");
			lblMarca.setBounds(15, 61, 69, 20);
			panel.add(lblMarca);
			
			txtMarca = new JTextField();
			txtMarca.setColumns(10);
			txtMarca.setBounds(81, 56, 146, 30);
			panel.add(txtMarca);
			{
				spnPrecio = new JSpinner();
				spnPrecio.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
				spnPrecio.setBounds(81, 137, 146, 30);
				panel.add(spnPrecio);
			}
			
			JPanel panelCantidades = new JPanel();
			panelCantidades.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cantidades", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
			panelCantidades.setBounds(15, 178, 404, 70);
			panel.add(panelCantidades);
			panelCantidades.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Min:");
			lblNewLabel.setBounds(12, 33, 36, 20);
			panelCantidades.add(lblNewLabel);
			
			spnMin = new JSpinner();
			spnMin.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnMin.setBounds(46, 28, 70, 30);
			panelCantidades.add(spnMin);
			{
				JLabel lblMax = new JLabel("Max:");
				lblMax.setBounds(142, 33, 36, 20);
				panelCantidades.add(lblMax);
			}
			{
				spnMax = new JSpinner();
				spnMax.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				spnMax.setBounds(178, 28, 70, 30);
				panelCantidades.add(spnMax);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Real:");
				lblNewLabel_1.setBounds(272, 33, 36, 20);
				panelCantidades.add(lblNewLabel_1);
			}
			{
				spnReal = new JSpinner();
				spnReal.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				spnReal.setBounds(308, 28, 70, 30);
				panelCantidades.add(spnReal);
			}
			{
				JPanel panelButtons = new JPanel();
				panelButtons.setBorder(new TitledBorder(null, "Tipo", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
				panelButtons.setBounds(15, 264, 404, 109);
				panel.add(panelButtons);
				panelButtons.setLayout(null);
				
				rdbtnDiscoDuro = new JRadioButton("Disco Duro");
				rdbtnDiscoDuro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						rdbtnDiscoDuro.setSelected(true);
						panelDiscoDuro.setVisible(true);
						
						rdbtnMicro.setSelected(false);
						rdbtnMotherBoard.setSelected(false);
						rdbtnRAM.setSelected(false);
						
						panelMicro.setVisible(false);
						panelMotherBoard.setVisible(false);
						panelRAM.setVisible(false);

					}
				});
				rdbtnDiscoDuro.setSelected(true);
				rdbtnDiscoDuro.setBounds(31, 29, 155, 29);
				panelButtons.add(rdbtnDiscoDuro);
				
				rdbtnMicro = new JRadioButton("Micro Procesador");
				rdbtnMicro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panelMicro.setVisible(true);
						rdbtnMicro.setSelected(true);
						
						
						rdbtnMotherBoard.setSelected(false);
						rdbtnRAM.setSelected(false);
						rdbtnDiscoDuro.setSelected(false);
						
						panelDiscoDuro.setVisible(false);
						panelMotherBoard.setVisible(false);
						panelRAM.setVisible(false);
					}
				});
				rdbtnMicro.setBounds(217, 29, 155, 29);
				panelButtons.add(rdbtnMicro);
				
				rdbtnMotherBoard = new JRadioButton("MotherBoard");
				rdbtnMotherBoard.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdbtnMotherBoard.setSelected(true);
						rdbtnRAM.setSelected(false);
						rdbtnDiscoDuro.setSelected(false);
						rdbtnMicro.setSelected(false);
						
						panelMicro.setVisible(false);					
						panelDiscoDuro.setVisible(false);
						panelMotherBoard.setVisible(true);
						panelRAM.setVisible(false);
					}
				});
				rdbtnMotherBoard.setBounds(31, 66, 155, 29);
				panelButtons.add(rdbtnMotherBoard);
				
				rdbtnRAM = new JRadioButton("RAM");
				rdbtnRAM.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdbtnMotherBoard.setSelected(false);
						rdbtnRAM.setSelected(true);
						rdbtnDiscoDuro.setSelected(false);
						rdbtnMicro.setSelected(false);
						
						panelMicro.setVisible(false);					
						panelDiscoDuro.setVisible(false);
						panelMotherBoard.setVisible(false);
						panelRAM.setVisible(true);
					}
				});
				rdbtnRAM.setBounds(217, 66, 155, 29);
				panelButtons.add(rdbtnRAM);
			}
			
			panelMicro = new JPanel();
			panelMicro.setVisible(false);
			panelMicro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelMicro.setBounds(15, 389, 404, 132);
			panel.add(panelMicro);
			panelMicro.setLayout(null);
			
			JLabel lblNewLabel_2_1 = new JLabel("Modelo:");
			lblNewLabel_2_1.setBounds(15, 18, 69, 20);
			panelMicro.add(lblNewLabel_2_1);
			
			txtModeloMicro = new JTextField();
			txtModeloMicro.setColumns(10);
			txtModeloMicro.setBounds(110, 13, 146, 30);
			panelMicro.add(txtModeloMicro);
			
			JLabel lblNewLabel_3_1 = new JLabel("Velocidad:");
			lblNewLabel_3_1.setBounds(15, 56, 129, 20);
			panelMicro.add(lblNewLabel_3_1);
			
			spnVelocidad = new JSpinner();
			spnVelocidad.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
			spnVelocidad.setBounds(110, 51, 146, 30);
			panelMicro.add(spnVelocidad);
			
			rdbtnGhz = new JRadioButton("Ghz");
			rdbtnGhz.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (rdbtnMhz.isSelected()) {
						rdbtnMhz.setSelected(false);
						spnVelocidad.setValue(Tienda.getInstance().calcularEquivalenciaGhz( new Float(spnVelocidad.getValue().toString() )));
					}
					rdbtnGhz.setSelected(true);
				}
			});
			rdbtnGhz.setSelected(true);
			rdbtnGhz.setBounds(267, 52, 59, 29);
			panelMicro.add(rdbtnGhz);
			
			rdbtnMhz = new JRadioButton("Mhz");
			rdbtnMhz.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (rdbtnGhz.isSelected()) {
						rdbtnGhz.setSelected(false);
						spnVelocidad.setValue(Tienda.getInstance().calcularEquivalenciaMhz( new Float(spnVelocidad.getValue().toString() )));
					}
					rdbtnMhz.setSelected(true);
				}
			});
			rdbtnMhz.setBounds(333, 52, 60, 29);
			panelMicro.add(rdbtnMhz);
			
			JLabel lblNewLabel_4_1 = new JLabel("Tipo Socket:");
			lblNewLabel_4_1.setBounds(15, 94, 109, 20);
			panelMicro.add(lblNewLabel_4_1);
			
			cbxTipoSocketMicro = new JComboBox();
			cbxTipoSocketMicro.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "PGA", "PPGA", "FC-PGA", "SPGA", "SECC", "VRM"}));
			cbxTipoSocketMicro.setBounds(110, 89, 146, 30);
			panelMicro.add(cbxTipoSocketMicro);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("");
				if (selected == null) {
					okButton.setText("Registrar");
				} else {
					okButton.setText("Modificar");
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected == null) {
							boolean valido = false;
							
							Componente aux = null;
							String id = txtCodigo.getText();
							String marca = txtMarca.getText();
							String serial = txtSerial.getText();
							float precio = new Float(spnPrecio.getValue().toString());
							int cantMin = new Integer(spnMin.getValue().toString());
							int cantMax = new Integer(spnMax.getValue().toString());
							int cantReal = new Integer(spnReal.getValue().toString());
							
							if (rdbtnDiscoDuro.isSelected()) {
								float capacidad = new Float(spnCapacidad.getValue().toString());
								String modelo = txtModeloDD.getText();
								
								if (rdbtnTb.isSelected()) {
									capacidad = Tienda.getInstance().calcularEquivalenciaTb_a_Gb(capacidad);
								}
								
								if (cbxTipoConexion.getSelectedIndex() != 0) {
									String tipoConexion = cbxTipoConexion.getSelectedItem().toString();
									aux = new DiscoDuro(id, marca, serial, precio, cantMin, cantMax, cantReal, capacidad, modelo, tipoConexion);
									valido = true;
								} else {
									JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de conexion", "Registro de Componentes", JOptionPane.WARNING_MESSAGE);
									valido = false;
								}
							}
							
							if (rdbtnRAM.isSelected()) {
								float cantMemoria = new Float(spnMemoria.getValue().toString());
								
								if (rdbtnMb.isSelected()) {
									cantMemoria = Tienda.getInstance().calcularEquivalenciaMb_a_Gb(cantMemoria);
								}
								
								if (cbxTipoMemoria.getSelectedIndex() != 0) {
									String tipoMemoria = cbxTipoMemoria.getSelectedItem().toString();
									aux = new Ram(id, marca, serial, precio, cantMin, cantMax, cantReal, cantMemoria, tipoMemoria);
									valido = true;
								} else {
									JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de memoria", "Registro de Componentes", JOptionPane.WARNING_MESSAGE);
									valido = false;
								}

							}
							
							if (rdbtnMicro.isSelected()) {
								String modelo = txtModeloMicro.getText();
								float velocidad = new Float(spnVelocidad.getValue().toString());
								
								if (rdbtnMhz.isSelected()) {
									velocidad = Tienda.getInstance().calcularEquivalenciaGhz(velocidad);
								}
								
								if (cbxTipoSocketMicro.getSelectedIndex() != 0) {
									String tipoSocket = cbxTipoSocketMicro.getSelectedItem().toString();
									aux = new MicroProcesador(id, marca, serial, precio, cantMin, cantMax, cantReal, modelo, tipoSocket, velocidad);
									valido = true;
								} else {
									JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de socket", "Registro de Componentes", JOptionPane.WARNING_MESSAGE);
									valido = false;
								}

							}
							
							if (rdbtnMotherBoard.isSelected()) {
								String modelo = txtModeloMotherBoard.getText();
								String tipoSocket = cbxTipoSocketMotherBoard.getSelectedItem().toString();
								String tipoRam = cbxTipoRam.getSelectedItem().toString();
								ArrayList<String> conexionesCompatibles = new ArrayList<>();
								
								if (listModelSelect.size() >= 1) {
									if (cbxTipoSocketMotherBoard.getSelectedIndex() != 0) {
										if (cbxTipoRam.getSelectedIndex() != 0) {
											
											for (int i = 0; i < listModelSelect.size(); i++) {
												conexionesCompatibles.add(listModelSelect.getElementAt(i));
											}
											aux = new MotherBoard(id, marca, serial, precio, cantMin, cantMax, cantReal, modelo, tipoSocket, tipoRam, conexionesCompatibles);
											valido = true;
											
										} else {
											JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de RAM", "Registro de Componentes", JOptionPane.WARNING_MESSAGE);
											valido = false;
										}
										
									} else {
										JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de socket", "Registro de Componentes", JOptionPane.WARNING_MESSAGE);
										valido = false;
									}
						
								} else {
									JOptionPane.showMessageDialog(null, "Debe seleccionar al menos una conexion", "Registro de Componentes", JOptionPane.WARNING_MESSAGE);
									valido = false;
								}
							}
							if (valido) {
								Tienda.getInstance().insertarComponente(aux);
								JOptionPane.showMessageDialog(null, "Registrado satisfactoriamente", "Registro de Componentes", JOptionPane.INFORMATION_MESSAGE);
								clean();
							}
						} else {
							selected.setId(txtCodigo.getText());
							selected.setMarca(txtMarca.getText());
							selected.setSerial(txtSerial.getText());
							selected.setPrecio(new Float(spnPrecio.getValue().toString()));
							selected.setCantMin(new Integer(spnMin.getValue().toString()));
							selected.setCantMax(new Integer(spnMax.getValue().toString()));
							selected.setCantReal(new Integer(spnReal.getValue().toString()));
							
							if (selected instanceof DiscoDuro) {
								if (cbxTipoConexion.getSelectedIndex() != 0) {
									((DiscoDuro) selected).setCapacidad(new Float(spnCapacidad.getValue().toString()));
									((DiscoDuro) selected).setModelo(txtModeloDD.getText());
									((DiscoDuro) selected).setTipoConexion(cbxTipoConexion.getSelectedItem().toString());
								} else {
									JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de conexion", "Registro de Componentes", JOptionPane.WARNING_MESSAGE);
								}
								
							}
							
							if (selected instanceof Ram) {
								if (cbxTipoMemoria.getSelectedIndex() != 0) {
									((Ram) selected).setCantMemoria(new Float(spnMemoria.getValue().toString()));
									((Ram) selected).setTipoMemoria(cbxTipoMemoria.getSelectedItem().toString());
								} else {
									JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de memoria", "Registro de Componentes", JOptionPane.WARNING_MESSAGE);
								}
							}
							
							if (selected instanceof MicroProcesador) {
								if (cbxTipoSocketMicro.getSelectedIndex() != 0) {
									((MicroProcesador) selected).setModelo(txtModeloMicro.getText());
									((MicroProcesador) selected).setTipoSocket(cbxTipoSocketMicro.getSelectedItem().toString());
									
									float velocidad = new Float(spnVelocidad.getValue().toString());
									
									if (rdbtnMhz.isSelected()) {
										velocidad = Tienda.getInstance().calcularEquivalenciaGhz(velocidad);
									}
									((MicroProcesador) selected).setVelocidad(velocidad);

								} else {
									JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de socket", "Registro de Componentes", JOptionPane.WARNING_MESSAGE);
								}
							}
							
							if (selected instanceof MotherBoard) {
								ArrayList<String> conexionesCompatibles = new ArrayList<>();
								
								if (listModelSelect.size() != 0) {
									if (cbxTipoSocketMotherBoard.getSelectedIndex() != 0) {
										if (cbxTipoRam.getSelectedIndex() != 0) {
											for (int i = 0; i < listModelSelect.size(); i++) {
												conexionesCompatibles.add(listModelSelect.getElementAt(i));
											}
											((MotherBoard) selected).setConexionesCompatibles(conexionesCompatibles);
											((MotherBoard) selected).setModelo(txtModeloMotherBoard.getText());
											((MotherBoard) selected).setTipoSocket(cbxTipoSocketMotherBoard.getSelectedItem().toString());
											((MotherBoard) selected).setTipoRam(cbxTipoRam.getSelectedItem().toString());
											
										} else {
											JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de RAM", "Registro de Componentes", JOptionPane.WARNING_MESSAGE);
										}
										
									} else {
										JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de Socket", "Registro de Componentes", JOptionPane.WARNING_MESSAGE);
									}
									
								} else {
									JOptionPane.showMessageDialog(null, "Debe seleccionar al menos una conexion", "Registro de Componentes", JOptionPane.WARNING_MESSAGE);
								}
								
							}

//							ListComponente.loadTable(0);
							dispose();
						}
						
					}
				});
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
			loadComponente();
		}
	}

	private void loadComponente() {
		if (selected != null) {
			txtCodigo.setText(selected.getId());
			txtMarca.setText(selected.getMarca());
			txtSerial.setText(selected.getSerial());
			spnPrecio.setValue(selected.getPrecio());
			spnMin.setValue(selected.getCantMin());
			spnMax.setValue(selected.getCantMax());
			spnReal.setValue(selected.getCantReal());
			
			if (selected instanceof Ram) {
				spnMemoria.setValue(((Ram) selected).getCantMemoria());
				cbxTipoMemoria.setSelectedItem(((Ram) selected).getTipoMemoria());
				
				rdbtnMotherBoard.setSelected(false);
				rdbtnRAM.setSelected(true);
				rdbtnDiscoDuro.setSelected(false);
				rdbtnMicro.setSelected(false);
				
				rdbtnDiscoDuro.setEnabled(false);
				rdbtnMicro.setEnabled(false);
				rdbtnMotherBoard.setEnabled(false);
				
				panelMicro.setVisible(false);					
				panelDiscoDuro.setVisible(false);
				panelMotherBoard.setVisible(false);
				panelRAM.setVisible(true);
			}
			
			if (selected instanceof DiscoDuro) {
				txtModeloDD.setText(((DiscoDuro) selected).getModelo());
				spnCapacidad.setValue(((DiscoDuro) selected).getCapacidad());
				cbxTipoConexion.setSelectedItem(((DiscoDuro) selected).getTipoConexion());
				
				rdbtnMotherBoard.setSelected(false);
				rdbtnRAM.setSelected(false);
				rdbtnDiscoDuro.setSelected(true);
				rdbtnMicro.setSelected(false);
				
				rdbtnRAM.setEnabled(false);
				rdbtnMicro.setEnabled(false);
				rdbtnMotherBoard.setEnabled(false);
				
				panelMicro.setVisible(false);					
				panelDiscoDuro.setVisible(true);
				panelMotherBoard.setVisible(false);
				panelRAM.setVisible(false);
			}
			
			if (selected instanceof MicroProcesador) {
				txtModeloMicro.setText(((MicroProcesador) selected).getModelo());
				cbxTipoSocketMicro.setSelectedItem(((MicroProcesador) selected).getTipoSocket());
				spnVelocidad.setValue(((MicroProcesador) selected).getVelocidad());
				
				rdbtnMotherBoard.setSelected(false);
				rdbtnRAM.setSelected(false);
				rdbtnDiscoDuro.setSelected(false);
				rdbtnMicro.setSelected(true);
				
				rdbtnRAM.setEnabled(false);
				rdbtnDiscoDuro.setEnabled(false);
				rdbtnMotherBoard.setEnabled(false);
				
				panelMicro.setVisible(true);					
				panelDiscoDuro.setVisible(false);
				panelMotherBoard.setVisible(false);
				panelRAM.setVisible(false);
			}
			
			if (selected instanceof MotherBoard) {
				txtModeloMotherBoard.setText(((MotherBoard) selected).getModelo());
				cbxTipoSocketMotherBoard.setSelectedItem(((MotherBoard) selected).getTipoSocket());
				cbxTipoRam.setSelectedItem(((MotherBoard) selected).getTipoRam());
			}
		}
	}

	protected void clean() {
		txtCodigo.setText("COMP-"+Componente.cod);
		txtMarca.setText("");
		txtSerial.setText("");
		spnPrecio.setValue(0);
		spnMin.setValue(0);
		spnMax.setValue(0);
		spnReal.setValue(0);
		spnMemoria.setValue(0);
		cbxTipoMemoria.setSelectedIndex(0);
		txtModeloDD.setText("");
		spnCapacidad.setValue(0);
		cbxTipoConexion.setSelectedIndex(0);
		txtModeloMicro.setText("");
		cbxTipoSocketMicro.setSelectedIndex(0);
		spnVelocidad.setValue(1);
		txtModeloMotherBoard.setText("");
		cbxTipoSocketMotherBoard.setSelectedIndex(0);
		cbxTipoRam.setSelectedIndex(0);
		
		listModelDisp.removeAllElements();
		listModelSelect.removeAllElements();
		loadConexiones();
	}
	
	private void loadConexiones() {
		listModelDisp.addElement("SATA");
		listModelDisp.addElement("SATA 2");
		listModelDisp.addElement("DDR");
		listModelDisp.addElement("DDR 2");
		listModelDisp.addElement("DDR 3");
		listModelDisp.addElement("LGA");
		listModelDisp.addElement("TR4");
	}
}
