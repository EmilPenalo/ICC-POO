package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Factura implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Usuario vendedor;
	private Date fecha;
	private int descuento;
	private Cliente cliente;
	private ArrayList<Componente> venta;
	public static int cod = 1;
	
	public Factura(String id, Cliente cliente, int descuento, Usuario usuario) {
		super();
		this.id = id;
		this.vendedor = usuario;
		this.fecha = new Date();
		this.cliente = cliente;
		this.descuento = descuento;
		this.venta = new ArrayList<>();
		Factura.cod++;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public ArrayList<Componente> getVenta() {
		return venta;
	}

	public void setVenta(ArrayList<Componente> venta) {
		this.venta = venta;
	}
	
	public void agregarComponente(Componente c) {
		venta.add(c);
	}
	
	public float precioTotal() {
		float total = 0;
		for (Componente c : venta) {
			total += c.getPrecio();
		}
		return total - (total*(descuento/100));
	}
}
