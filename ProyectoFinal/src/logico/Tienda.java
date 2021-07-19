package logico;

import java.util.ArrayList;

public class Tienda {
		private ArrayList<Usuario> usuarios;
		private ArrayList<Componente> inventario;
		private ArrayList<Factura> facturas;
		private ArrayList<Cliente> clientes;
		private ArrayList<Suministrador> suministradores;
		private ArrayList<OrdenCompra> ordenesCompra;
		
	public Tienda() {
		this.usuarios=new ArrayList<Usuario>();
		this.inventario=new ArrayList<Componente>();
		this.facturas=new ArrayList<Factura>();
		this.clientes=new ArrayList<Cliente>();
		this.suministradores=new ArrayList<Suministrador>();
		this.ordenesCompra=new ArrayList<OrdenCompra>();
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
		Factura factura=buscarFacturaById(codFactura);
		
		return factura.precioTotal();
	}
	
	private Factura buscarFacturaById(String codFactura) {
		
		for(Factura f:facturas) {
			if(f.getId().equalsIgnoreCase(codFactura)) {
				return f;
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
		
		OrdenCompra orden=buscarOrdenCompraById(idOrden);
		
		return orden.precioTotal();
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
		//TODO
	}
	
	public void insertarCliente(Cliente nuevoCliente) {
		//TODO
	}
	
	public void insertarComponente(Componente nuevoComponente) {
		//TODO
	}
	
	public void insertarFactura(Factura nuevaFactura) {
		//TODO
	}
	
	public void insertarSuministrador(Suministrador nuevoSuministrador) {
		//TODO
	}
	
	public void otorgarCredito(float monto,Cliente cliente) {
		//TODO
	}
}
