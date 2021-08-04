package logico;

public class Vendedor extends Usuario {
	
	private static final long serialVersionUID = 1L;
	private int comision;
	private int cantVentas;
	private float ventas;

	public Vendedor(String id, String nombre, String userName, String passWord, int comision) {
		super(id, nombre, userName, passWord);
		this.comision = comision;
		this.cantVentas = 0;
		this.ventas = 0;
	}

	public int getComision() {
		return comision;
	}

	public void setComision(int comision) {
		this.comision = comision;
	}

	public int getCantVentas() {
		return cantVentas;
	}

	public void setCantVentas(int cantVentas) {
		this.cantVentas = cantVentas;
	}

	public float getVentas() {
		return ventas;
	}

	public void setVentas(float ventas) {
		this.ventas = ventas;
	}
	
	public float calculoComision() {
		return ventas * ((float) comision / 100);
	}
}
