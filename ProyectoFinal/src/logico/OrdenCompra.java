package logico;

import java.util.ArrayList;
import java.util.Date;

public class OrdenCompra {
	private String id;
	private Suministrador suministrador;
	private ArrayList<Componente> componentes;
	private int cantUnidades;
	private Date fecha;
	public static int cod=1; 
	
	public OrdenCompra(String id, Suministrador suministrador, int cantUnidades) {
		super();
		this.id = id;
		this.suministrador = suministrador;
		this.componentes=new ArrayList<Componente>();
		this.cantUnidades = cantUnidades;
		this.fecha = new Date();
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
	
	public float precioTotal() {
		float total=0;
		
		for(int i=0;i<componentes.size();i++) {
		total+=componentes.get(i).getPrecio()*cantUnidades;
		}
		
		return total;
	}
}
