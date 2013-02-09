package partidas;

import intercambio.InterfazClienteServidor;

import java.rmi.*;

public class Registrador {
	
	
	public Registrador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void atenderLlamadas(InterfazClienteServidor objetoRemoto) throws Exception{
		try{
			//RVA: antes se creaba aqui el objetoRemoto
			Naming.rebind("MajesticThunderbolt", objetoRemoto); //registra un objeto con ese nombre.
			System.out.println("He arrancado el registrador");
		} catch (Exception e){
			e.printStackTrace();
			throw(e);
		}
	}

}
