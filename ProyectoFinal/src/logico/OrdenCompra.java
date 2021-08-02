package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class OrdenCompra implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Suministrador suministrador;
	private ArrayList<Componente> componentes;
	private int cantUnidades;
	private Date fecha;
	private boolean pendiente;
	public static int cod=1; 
	
	public OrdenCompra(String id, Suministrador suministrador, int cantUnidades) {
		super();
		this.id = id;
		this.suministrador = suministrador;
		this.componentes = new ArrayList<Componente>();
		this.cantUnidades = cantUnidades;
		this.fecha = new Date();
		this.setPendiente(true);
		cod++;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Suministrador getSuministrador() {
		return suministrador;
	}

	public void setSuministrador(Suministrador suministrador) {
		this.suministrador = suministrador;
	}

	public ArrayList<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(ArrayList<Componente> componentes) {
		this.componentes = componentes;
	}

	public int getCantUnidades() {
		return cantUnidades;
	}

	public void setCantUnidades(int cantUnidades) {
		this.cantUnidades = cantUnidades;
	}

	public boolean isPendiente() {
		return pendiente;
	}

	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public static int getCod() {
		return cod;
	}

	public static void setCod(int cod) {
		OrdenCompra.cod = cod;
	}
	
	public void addComponente(Componente c) {
		componentes.add(c);
	}
	
	public float precioTotal() {
		float total=0;
		
		for (int i=0;i<componentes.size();i++) {
			total+=componentes.get(i).getPrecio()*cantUnidades;
		}
		
		return total;
	}
}
