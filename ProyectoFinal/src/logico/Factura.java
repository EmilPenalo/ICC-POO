package logico;

import java.util.ArrayList;
import java.util.Date;

public class Factura {
	private String id;
	private Date fecha;
	private int descuento;
	private Cliente cliente;
	private ArrayList<Componente> venta;
	public static int cod = 1;
	
	public Factura(String id, Date fecha, Cliente cliente, int descuento) {
		super();
		this.id = id;
		this.fecha = fecha;
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
		return total;
	}
}
