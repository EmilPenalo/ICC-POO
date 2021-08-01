package logico;

import java.util.Date;

import visual.ListFactura;

public class Main {
	
	public static void main(String[] args) {
		Cliente c = new Cliente("001", "Nombre", " ", " ");
		Factura temp = new Factura("01", new Date(), c, 2, new Vendedor("0001", "nose", "username", "password", 3));
		Factura temp2 = new FacturaCredito("02", new Date(), c, 2, new Vendedor("0002", "nose", "username", "password", 3), 0, true);
		Factura temp3 = new FacturaCredito("03", new Date(), c, 2, new Vendedor("0003", "nose", "username", "password", 3), 0, false);

		Tienda.getInstance().getFacturas().add(temp);
		Tienda.getInstance().getFacturas().add(temp2);
		Tienda.getInstance().getFacturas().add(temp3);
		
		ListFactura window = new ListFactura(c);
		window.setVisible(true);
	}

}
