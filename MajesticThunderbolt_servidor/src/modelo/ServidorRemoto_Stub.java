// Stub class generated by rmic, do not edit.
// Contents subject to change without notice.

package modelo;

public final class ServidorRemoto_Stub
    extends java.rmi.server.RemoteStub
    implements sincronizacionRMI.InterfazClienteServidor, java.rmi.Remote
{
    private static final long serialVersionUID = 2;
    
    private static java.lang.reflect.Method $method_obtenerIdentificadorCliente_0;
    private static java.lang.reflect.Method $method_publicarColision_1;
    private static java.lang.reflect.Method $method_publicarDestruccionAlien_2;
    private static java.lang.reflect.Method $method_publicarDestruccionNave_3;
    private static java.lang.reflect.Method $method_publicarDispararNave_4;
    private static java.lang.reflect.Method $method_publicarDisparoAlien_5;
    private static java.lang.reflect.Method $method_publicarDisparoDirigido_6;
    private static java.lang.reflect.Method $method_publicarEquilibrarNave_7;
    private static java.lang.reflect.Method $method_publicarMoverNaveAbajo_8;
    private static java.lang.reflect.Method $method_publicarMoverNaveArriba_9;
    private static java.lang.reflect.Method $method_publicarMoverNaveDerecha_10;
    private static java.lang.reflect.Method $method_publicarMoverNaveIzquierda_11;
    
    static {
	try {
	    $method_obtenerIdentificadorCliente_0 = sincronizacionRMI.InterfazClienteServidor.class.getMethod("obtenerIdentificadorCliente", new java.lang.Class[] {sincronizacionRMI.InterfazServidorCliente.class});
	    $method_publicarColision_1 = sincronizacionRMI.InterfazClienteServidor.class.getMethod("publicarColision", new java.lang.Class[] {int.class, int.class, int.class});
	    $method_publicarDestruccionAlien_2 = sincronizacionRMI.InterfazClienteServidor.class.getMethod("publicarDestruccionAlien", new java.lang.Class[] {int.class, int.class, int.class});
	    $method_publicarDestruccionNave_3 = sincronizacionRMI.InterfazClienteServidor.class.getMethod("publicarDestruccionNave", new java.lang.Class[] {int.class, int.class, int.class});
	    $method_publicarDispararNave_4 = sincronizacionRMI.InterfazClienteServidor.class.getMethod("publicarDispararNave", new java.lang.Class[] {int.class, int.class});
	    $method_publicarDisparoAlien_5 = sincronizacionRMI.InterfazClienteServidor.class.getMethod("publicarDisparoAlien", new java.lang.Class[] {int.class, int.class});
	    $method_publicarDisparoDirigido_6 = sincronizacionRMI.InterfazClienteServidor.class.getMethod("publicarDisparoDirigido", new java.lang.Class[] {int.class, int.class});
	    $method_publicarEquilibrarNave_7 = sincronizacionRMI.InterfazClienteServidor.class.getMethod("publicarEquilibrarNave", new java.lang.Class[] {int.class, int.class});
	    $method_publicarMoverNaveAbajo_8 = sincronizacionRMI.InterfazClienteServidor.class.getMethod("publicarMoverNaveAbajo", new java.lang.Class[] {int.class, int.class});
	    $method_publicarMoverNaveArriba_9 = sincronizacionRMI.InterfazClienteServidor.class.getMethod("publicarMoverNaveArriba", new java.lang.Class[] {int.class, int.class});
	    $method_publicarMoverNaveDerecha_10 = sincronizacionRMI.InterfazClienteServidor.class.getMethod("publicarMoverNaveDerecha", new java.lang.Class[] {int.class, int.class});
	    $method_publicarMoverNaveIzquierda_11 = sincronizacionRMI.InterfazClienteServidor.class.getMethod("publicarMoverNaveIzquierda", new java.lang.Class[] {int.class, int.class});
	} catch (java.lang.NoSuchMethodException e) {
	    throw new java.lang.NoSuchMethodError(
		"stub class initialization failed");
	}
    }
    
    // constructors
    public ServidorRemoto_Stub(java.rmi.server.RemoteRef ref) {
	super(ref);
    }
    
    // methods from remote interfaces
    
    // implementation of obtenerIdentificadorCliente(InterfazServidorCliente)
    public int obtenerIdentificadorCliente(sincronizacionRMI.InterfazServidorCliente $param_InterfazServidorCliente_1)
	throws java.rmi.RemoteException
    {
	try {
	    Object $result = ref.invoke(this, $method_obtenerIdentificadorCliente_0, new java.lang.Object[] {$param_InterfazServidorCliente_1}, -8093047971255649133L);
	    return ((java.lang.Integer) $result).intValue();
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of publicarColision(int, int, int)
    public void publicarColision(int $param_int_1, int $param_int_2, int $param_int_3)
	throws java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_publicarColision_1, new java.lang.Object[] {new java.lang.Integer($param_int_1), new java.lang.Integer($param_int_2), new java.lang.Integer($param_int_3)}, -876303157235838609L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of publicarDestruccionAlien(int, int, int)
    public void publicarDestruccionAlien(int $param_int_1, int $param_int_2, int $param_int_3)
	throws java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_publicarDestruccionAlien_2, new java.lang.Object[] {new java.lang.Integer($param_int_1), new java.lang.Integer($param_int_2), new java.lang.Integer($param_int_3)}, -7505701887119665154L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of publicarDestruccionNave(int, int, int)
    public void publicarDestruccionNave(int $param_int_1, int $param_int_2, int $param_int_3)
	throws java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_publicarDestruccionNave_3, new java.lang.Object[] {new java.lang.Integer($param_int_1), new java.lang.Integer($param_int_2), new java.lang.Integer($param_int_3)}, -2758245874241649595L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of publicarDispararNave(int, int)
    public void publicarDispararNave(int $param_int_1, int $param_int_2)
	throws java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_publicarDispararNave_4, new java.lang.Object[] {new java.lang.Integer($param_int_1), new java.lang.Integer($param_int_2)}, 442953511778417133L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of publicarDisparoAlien(int, int)
    public void publicarDisparoAlien(int $param_int_1, int $param_int_2)
	throws java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_publicarDisparoAlien_5, new java.lang.Object[] {new java.lang.Integer($param_int_1), new java.lang.Integer($param_int_2)}, 2704472293364428968L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of publicarDisparoDirigido(int, int)
    public void publicarDisparoDirigido(int $param_int_1, int $param_int_2)
	throws java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_publicarDisparoDirigido_6, new java.lang.Object[] {new java.lang.Integer($param_int_1), new java.lang.Integer($param_int_2)}, 4455812948254247741L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of publicarEquilibrarNave(int, int)
    public void publicarEquilibrarNave(int $param_int_1, int $param_int_2)
	throws java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_publicarEquilibrarNave_7, new java.lang.Object[] {new java.lang.Integer($param_int_1), new java.lang.Integer($param_int_2)}, -1189897568791634126L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of publicarMoverNaveAbajo(int, int)
    public void publicarMoverNaveAbajo(int $param_int_1, int $param_int_2)
	throws java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_publicarMoverNaveAbajo_8, new java.lang.Object[] {new java.lang.Integer($param_int_1), new java.lang.Integer($param_int_2)}, -7022913389459046311L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of publicarMoverNaveArriba(int, int)
    public void publicarMoverNaveArriba(int $param_int_1, int $param_int_2)
	throws java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_publicarMoverNaveArriba_9, new java.lang.Object[] {new java.lang.Integer($param_int_1), new java.lang.Integer($param_int_2)}, 5291851763182377887L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of publicarMoverNaveDerecha(int, int)
    public void publicarMoverNaveDerecha(int $param_int_1, int $param_int_2)
	throws java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_publicarMoverNaveDerecha_10, new java.lang.Object[] {new java.lang.Integer($param_int_1), new java.lang.Integer($param_int_2)}, 4471870773443002271L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of publicarMoverNaveIzquierda(int, int)
    public void publicarMoverNaveIzquierda(int $param_int_1, int $param_int_2)
	throws java.rmi.RemoteException
    {
	try {
	    ref.invoke(this, $method_publicarMoverNaveIzquierda_11, new java.lang.Object[] {new java.lang.Integer($param_int_1), new java.lang.Integer($param_int_2)}, 8055665926188262778L);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
}
