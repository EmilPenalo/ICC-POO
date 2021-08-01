package logico;

import java.io.Serializable;

public abstract class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String id;
	protected String nombre;
	protected String userName;
	protected String passWord;
	public static int cod = 1;
	
	public Usuario(String id, String nombre, String userName, String passWord) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.userName = userName;
		this.passWord = passWord;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
