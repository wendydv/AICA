package aica.model;


public class Control {
	private static Usuario sessionUser = null;

	public Control() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static Usuario getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(Usuario sessionUser) {
		Control.sessionUser = sessionUser;
	} 


}
