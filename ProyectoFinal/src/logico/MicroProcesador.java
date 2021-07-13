package logico;

public class MicroProcesador extends Componente {
	private String modelo;
	private String tipoSocket;
	private float velocidad;
	
	public MicroProcesador(String id, String marca, String serial, float precio, int cantMin, int cantMax, int cantReal,
			String modelo, String tipoSocket, float velocidad) {
		super(id, marca, serial, precio, cantMin, cantMax, cantReal);
		this.modelo = modelo;
		this.tipoSocket = tipoSocket;
		this.velocidad = velocidad;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipoSocket() {
		return tipoSocket;
	}

	public void setTipoSocket(String tipoSocket) {
		this.tipoSocket = tipoSocket;
	}

	public float getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(float velocidad) {
		this.velocidad = velocidad;
	}
}
