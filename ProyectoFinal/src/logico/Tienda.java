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
	
	private static Usuario loginUser;
	private static Tienda shop = null;
		
	private Tienda() {
		this.usuarios = new ArrayList<Usuario>();
		this.inventario = new ArrayList<Componente>();
		this.facturas = new ArrayList<Factura>();
		this.clientes = new ArrayList<Cliente>();
		this.suministradores = new ArrayList<Suministrador>();
		this.ordenesCompra = new ArrayList<OrdenCompra>();
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
	
	public float calcularEquivalenciaTb(float valor) {
		
		return valor/1000;
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
		Usuario vendedor=buscarUsuarioById(idVendedor);
		
		if (vendedor != null) {
			if(vendedor.getTipo() == 'V') {
				for(Factura f : facturas) {
					if(f.getId().equalsIgnoreCase(idVendedor)) {
						total += f.precioTotal();
					}
				}
			} else {
				return -1;
			}
			return total*(comision/100);
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
	
	public void modificarCombo(Combo selected) {
		int index=buscarIndexOfComboById(selected.getId());
		combos.set(index,selected);
	}

	private int buscarIndexOfComboById(String id) {
		int ind=0;
		for(Combo com:combos) {
			if(com.getId().equalsIgnoreCase(id)) {
				return ind;
			}
			ind++;
		}
		return -1;
	}

	public Combo buscarComboById(String id) {
		for(Combo com:combos) {
			if(com.getId().equalsIgnoreCase(id)) {
				return com;
			}
		}
		return null;
	}
	
	public void eliminarCliente(Cliente selected) {
		clientes.remove(selected);
	}
	
	public void modificarCliente(Cliente selected) {
		int index=buscarIndexOfClienteByCedula(selected.getCedula());
		clientes.set(index,selected);
	}

	private int buscarIndexOfClienteByCedula(String cedula) {
		int ind=0;
		for(Cliente c:clientes) {
			if(c.getCedula().equalsIgnoreCase(cedula)) {
				return ind;
			}
			ind++;
		}
		return -1;
	}
}
