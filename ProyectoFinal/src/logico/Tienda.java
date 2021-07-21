package logico;

import java.util.ArrayList;

public class Tienda {
		private ArrayList<Usuario> usuarios;
		private ArrayList<Componente> inventario;
		private ArrayList<Factura> facturas;
		private ArrayList<Cliente> clientes;
		private ArrayList<Suministrador> suministradores;
		private ArrayList<OrdenCompra> ordenesCompra;
		private static Tienda shop = null;
		
	public Tienda() {
		this.usuarios = new ArrayList<Usuario>();
		this.inventario = new ArrayList<Componente>();
		this.facturas = new ArrayList<Factura>();
		this.clientes = new ArrayList<Cliente>();
		this.suministradores = new ArrayList<Suministrador>();
		this.ordenesCompra = new ArrayList<OrdenCompra>();
	}
	
	public Tienda getInstance() {
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
	
	public boolean chequearCompatibilidad(String modeloBoard,String serialComp) {
		//TODO
		return false;
	}
	
	public float montoTotalFactura(String codFactura) {
		Factura factura = buscarFacturaById(codFactura);
		
		return factura.precioTotal();
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

	public float obtenerPrecioComponente(String serial) {
		//TODO
		return 0;
	}
	
	public float calcularEquivalenciaMb(float valor) {
		//TODO
		return 0;
	}
	
	public float calcularEquivalenciaMhz(float valor) {
		//TODO 
		return 0;
	}
	
	public float calcularEquivalenciaTb(float valor) {
		//TODO
		return 0;
	}
	
	public float obtenerPrecioOrdenCompra(String idOrden) {
		OrdenCompra orden = buscarOrdenCompraById(idOrden);
			
		if (orden != null) {
			return orden.precioTotal();
		}
		return -1;
	}
	
	private OrdenCompra buscarOrdenCompraById(String idOrden) {
		
		for(OrdenCompra ord:ordenesCompra) {
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
		//TODO
	}
}