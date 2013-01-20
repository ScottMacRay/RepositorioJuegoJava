package partidas;

public class LanzadorServidor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// RVA: tenemos que activar el registrador para que funcione con RMI
		Registrador registro= new Registrador();
		try {
			//registro.atenderLlamadas();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
