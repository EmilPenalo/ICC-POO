package logico;

public class DiscoDuro extends Componente {
	private float capacidad;
	private String modelo;
	private String tipoConexion;
	
	public DiscoDuro(String id, String marca, String serial, float precio, int cantMin, int cantMax, int cantReal,
			float capacidad, String modelo, String tipoConexion) {
		super(id, marca, serial, precio, cantMin, cantMax, cantReal);
		this.capacidad = capacidad;
		this.modelo = modelo;
		this.tipoConexion = tipoConexion;
	}

	public float getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(float capacidad) {
		this.capacidad = capacidad;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipoConexion() {
		return tipoConexion;
	}

	public void setTipoConexion(String tipoConexion) {
		this.tipoConexion = tipoConexion;
	}
	
}
