package logico;

import java.io.Serializable;

public abstract class Componente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String marca;
	private String serial;
	private float precio;
	private int cantMin;
	private int cantMax;
	private int cantReal;
	public static int cod = 1;
	
	public Componente(String id, String marca, String serial, float precio, int cantMin, int cantMax, int cantReal) {
		super();
		this.id = id;
		this.marca = marca;
		this.serial = serial;
		this.precio = precio;
		this.cantMin = cantMin;
		this.cantMax = cantMax;
		this.cantReal = cantReal;
		Componente.cod++;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCantMin() {
		return cantMin;
	}

	public void setCantMin(int cantMin) {
		this.cantMin = cantMin;
	}

	public int getCantMax() {
		return cantMax;
	}

	public void setCantMax(int cantMax) {
		this.cantMax = cantMax;
	}

	public int getCantReal() {
		return cantReal;
	}

	public void setCantReal(int cantReal) {
		this.cantReal = cantReal;
	}
}
