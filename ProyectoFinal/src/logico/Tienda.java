package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Tienda implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Usuario> usuarios;
	private ArrayList<Componente> inventario;
	private ArrayList<Factura> facturas;
	private ArrayList<Cliente> clientes;
	private ArrayList<Suministrador> suministradores;
	private ArrayList<OrdenCompra> ordenesCompra;
	private ArrayList<Combo> combos;
	
	private int codUsuario;
	private int codComponente;
	private int codCombo;
	private int codFactura;
	private int codOrden;
	private int codSumi;
	
	private static Usuario loginUser;
	private static Tienda shop = null;
		
	private Tienda() {
		this.usuarios = new ArrayList<Usuario>();
		this.inventario = new ArrayList<Componente>();
		this.facturas = new ArrayList<Factura>();
		this.clientes = new ArrayList<Cliente>();
		this.suministradores = new ArrayList<Suministrador>();
		this.ordenesCompra = new ArrayList<OrdenCompra>();
		this.combos=new ArrayList<Combo>();
	}
	
	public static Tienda getInstance() {
		if (shop == null) {
			shop = new Tienda();
		}
		return shop;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public ArrayList<Componente> getInventario() {
		return inventario;
	}

	public void setInventario(ArrayList<Componente> inventario) {
		this.inventario = inventario;
	}

	public ArrayList<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ArrayList<Suministrador> getSuministradores() {
		return suministradores;
	}

	public void setSuministradores(ArrayList<Suministrador> suministradores) {
		this.suministradores = suministradores;
	}

	public ArrayList<OrdenCompra> getOrdenesCompra() {
		return ordenesCompra;
	}

	public void setOrdenesCompra(ArrayList<OrdenCompra> ordenesCompra) {
		this.ordenesCompra = ordenesCompra;
	}
	
	public ArrayList<Combo> getCombos() {
		return combos;
	}

	public void setCombos(ArrayList<Combo> combos) {
		this.combos = combos;
	}

	public static Usuario getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(Usuario loginUser) {
		Tienda.loginUser = loginUser;
	}

	public static Tienda getTienda() {
		return shop;
	}

	public static void setTienda(Tienda tienda) {
		Tienda.shop = tienda;
	}

	public boolean chequearCompatibilidad(String serialBoard,String serialComp) {
		
		Componente board = buscarComponenteBySerial(serialBoard);
		Componente comp = buscarComponenteBySerial(serialComp);
		
		if (board != null && comp != null) {
			if(board instanceof MotherBoard && !(comp instanceof MotherBoard)) {
				return ((MotherBoard)board).compatibilidadConBoard(comp);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public float montoTotalFactura(String codFactura) {
		Factura factura = buscarFacturaById(codFactura);
		if (factura != null) {
			return factura.precioTotal();
		} else {
			return -1;
		}
	}
	
	public Factura buscarFacturaById(String codFactura) {
		
		for(Factura f:facturas) {
			if(f.getId().equalsIgnoreCase(codFactura)) {
				return f;
			}
		}
		return null;
	}
	
	public Componente buscarComponenteBySerial(String serial) {
		
		for(Componente c : inventario) {
			if(c.getSerial().equalsIgnoreCase(serial)) {
				return c;
			}
		}
		return null;
	}
	
	public Componente buscarComponenteById(String id) {
		
		for(Componente c : inventario) {
			if(c.getId().equalsIgnoreCase(id)) {
				return c;
			}
		}
		return null;
	}
	
	public Cliente buscarClienteByCedula(String cedula) {
		
		for(Cliente c : clientes) {
			if(c.getCedula().equalsIgnoreCase(cedula)) {
				return c;
			}
		}
		return null;
	}
	
	public Usuario buscarUsuarioById(String id) {
		
		for(Usuario u : usuarios) {
			if(u.getId().equalsIgnoreCase(id)) {
				return u;
			}
		}
		return null;
	}
	
	public Suministrador buscarSumiById(String id) {
		
		for(Suministrador sumi : suministradores) {
			if(sumi.getId().equalsIgnoreCase(id)) {
				return sumi;
			}
		}
		return null;
	}
	
	public OrdenCompra buscarOrdenById(String id) {
		
		for(OrdenCompra orden : ordenesCompra) {
			if(orden.getId().equalsIgnoreCase(id)) {
				return orden;
			}
		}
		return null;
	}

	public float obtenerPrecioComponente(String id) {
		
		Componente comp = buscarComponenteById(id);
		if (comp != null) {
			return comp.getPrecio();
		} else {
			return -1;
		}
	}
	
	public float calcularEquivalenciaMb(float valor) {
		return valor*1024;
	}
	
	public float calcularEquivalenciaMhz(float valor) {
		return valor*1000;
	}
	
	public float calcularEquivalenciaGhz(float valor) {	 
		return valor/1000;
	}
	
	public float calcularEquivalenciaTb(float valor) {
		return valor/1000;
	}
	
	public float calcularEquivalenciaTb_a_Gb(float valor) {
		return valor*1000;
	}
	
	public float calcularEquivalenciaMb_a_Gb(float valor) {
		return valor/1024;
	}
	
	public float obtenerPrecioOrdenCompra(String idOrden) {
		OrdenCompra orden = buscarOrdenCompraById(idOrden);
			
		if (orden != null) {
			return orden.precioTotal();
		}
		return -1;
	}
	
	private OrdenCompra buscarOrdenCompraById(String idOrden) {
		
		for(OrdenCompra ord : ordenesCompra) {
			if(ord.getId().equalsIgnoreCase(idOrden)) {
				return ord;
			}
		}
		return null;
	}

	public void insertarUsuario(Usuario nuevoUsuario) {
		usuarios.add(nuevoUsuario);
	}
	
	public void insertarCliente(Cliente nuevoCliente) {
		clientes.add(nuevoCliente);
	}
	
	public void insertarComponente(Componente nuevoComponente) {
		inventario.add(nuevoComponente);
	}
	
	public void insertarFactura(Factura nuevaFactura) {
		facturas.add(nuevaFactura);
	}
	
	public void insertarSuministrador(Suministrador nuevoSuministrador) {
		suministradores.add(nuevoSuministrador);
	}
	
	public void otorgarCredito(float monto,Cliente cliente) {
		
		cliente.setCredito(cliente.getCredito()+monto);
	}
	
	public float calcularComisionVendedor(String idVendedor, float comision) {
		
		float total = 0;
		Usuario user = buscarUsuarioById(idVendedor);
		
		if (user != null) {
			if (user instanceof Vendedor) {
				for  (Factura f : facturas) {
					if(f.getVendedor().getId().equalsIgnoreCase(idVendedor)) {
						total += f.precioTotal();
					}
				}
				return total*(((Vendedor) user).getComision()/100);
			} else {
				return -1;
			}

		} else {
			return -1;
		}
	}


	public void insertarCombo(Combo nuevoCombo) {
		combos.add(nuevoCombo);	
	}
	
	public boolean confirmLogin(String username, String password) {
		for (Usuario u : usuarios) {
			if (u.getUserName().equals(username) && u.getPassWord().equals(password)) {
				loginUser = u;
				return true;
			}
		}
		return false;
	}

	public void eliminarCombo(Combo selected) {
		combos.remove(selected);
	}

	private int buscarIndexOfComboById(String id) {
		int ind = 0;
		for (Combo com : combos) {
			if (com.getId().equalsIgnoreCase(id)) {
				return ind;
			}
			ind++;
		}
		return -1;
	}

	public Combo buscarComboById(String id) {
		for (Combo com : combos) {
			if (com.getId().equalsIgnoreCase(id)) {
				return com;
			}
		}
		return null;
	}
	
	public void eliminarCliente(Cliente selected) {
		clientes.remove(selected);
	}
	
	public void modificarCliente(Cliente selected) {
		int index = buscarIndexOfClienteByCedula(selected.getCedula());
		clientes.set(index,selected);
	}

	private int buscarIndexOfClienteByCedula(String cedula) {
		int ind = 0;
		for (Cliente c:clientes) {
			if (c.getCedula().equalsIgnoreCase(cedula)) {
				return ind;
			}
			ind++;
		}
		return -1;
	}

	private int buscarIndexOfSumiById(String id) {
		int ind=0;
		for (Suministrador sumi:suministradores) {
			if (sumi.getId().equalsIgnoreCase(id)) {
				return ind;
			}
			ind++;
		}
		return -1;
	}

	public void eliminarSuministrador(Suministrador selected) {
		suministradores.remove(selected);
		
	}

	public void eliminarComponente(Componente selected) {
		inventario.remove(selected);
	}

	public boolean eliminarUsuario(Usuario selected) {
		if (usuarios.size() - 1 > 0) {
			usuarios.remove(selected);
			return true;
		}
		return false;
	}

	public void insertarOrdenCompra(OrdenCompra nuevaorden) {
		ordenesCompra.add(nuevaorden);
	}

	public void modificarOrdenCompra(OrdenCompra selected) {
		int index=buscarIndexOfOrdenCompraById(selected.getId());
		ordenesCompra.set(index, selected);
	}

	private int buscarIndexOfOrdenCompraById(String id) {
		int ind = 0;
		for (OrdenCompra ord:ordenesCompra) {
			if (ord.getId().equalsIgnoreCase(id)) {
				return ind;
			}
			ind++;
		}
		return -1;
	}

	public void eliminarOrdenCompra(OrdenCompra selected) {
		ordenesCompra.remove(selected);
	}

	public boolean checkCedula(String cedula) {
		for (Cliente c : clientes) {
			if (c.getCedula().equalsIgnoreCase(cedula)) {
				return false;
			}
		}
		return true;
	}

	public void completarFactura(FacturaCredito f) {
		f.setPendiente(false);
		f.getCliente().setCreditoEnUso(f.getCliente().getCreditoEnUso() - f.precioTotal());
	}
	
	public boolean checkDisponibilidadComponente(Componente c) {
		if (c.getCantReal() < c.getCantMin()) {
			for (Suministrador sumi : suministradores) {
				if (sumi.getComponentes().contains(c)) {
					OrdenCompra orden = new OrdenCompra("OC-" + OrdenCompra.cod, sumi, c.getCantMax() - c.getCantReal());
					orden.addComponente(c);
					ordenesCompra.add(orden);
					return true;
				}
			}
			return false;
		} else {
			return true;
		}
	}

	public void finalizarOrden(OrdenCompra orden) {
		for (Componente c : orden.getComponentes()) {
			c.setCantReal(c.getCantReal() + orden.getCantUnidades());
		}
		orden.setPendiente(false);
	}

	public void updateCodigos() {
		codUsuario = Usuario.cod;
		codComponente = Componente.cod;
		codCombo = Combo.cod;
		codFactura = Factura.cod;
		codOrden = OrdenCompra.cod;
		codSumi = Suministrador.cod;
	}

	public void loadCodigos() {
		Usuario.cod = codUsuario;
		Componente.cod = codComponente;
		Combo.cod = codCombo;
		Factura.cod = codFactura;
		OrdenCompra.cod = codOrden;
		Suministrador.cod = codSumi;
	}

	public boolean checkCredito(Cliente c, float monto) {
		if (c.getCredito() - c.getCreditoEnUso() >= monto) {
			return true;
		}
		return false;
	}

	public boolean checkCombo(Combo c) {
		for (Componente comp : c.getComponentes()) {
			if (comp.getCantReal() == 0) {
				return false;
			}
		}
		return true;
	}
}
