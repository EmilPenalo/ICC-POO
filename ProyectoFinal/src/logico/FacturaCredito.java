package logico;

import java.util.Date;

public class FacturaCredito extends Factura {
	private int diasCredito;
	private boolean pendiente;
	
	public FacturaCredito(String id, Cliente cliente, int descuento, Vendedor vendedor, int diasCredito, boolean pendiente) {
		super(id, cliente, descuento, vendedor);
		this.diasCredito = diasCredito;
		this.pendiente = pendiente;
	}

	public int getDiasCredito() {
		return diasCredito;
	}

	public void setDiasCredito(int diasCredito) {
		this.diasCredito = diasCredito;
	}

	public boolean isPendiente() {
		return pendiente;
	}

	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}
}
