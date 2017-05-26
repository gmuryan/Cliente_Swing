package businessDelegates;

import java.rmi.Naming;
import java.rmi.RemoteException;

import DTO.ProveedorDTO;
import RemoteObject.ClienteRemote;
import RemoteObject.ProveedorRemote;

public class ProveedorDelegate {

private static ProveedorDelegate instancia;
	private ProveedorRemote remoto;
	public static ProveedorDelegate GetInstancia(){
		if(instancia==null)
			instancia = new ProveedorDelegate();
		return instancia;
		
	}
	
	
	public ProveedorDelegate() {
		try{
			remoto =(ProveedorRemote) Naming.lookup("//localhost:1099/ProveedorController");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public boolean verificarProveedor(int parseInt) throws RemoteException {
		return remoto.verificarProveedor(parseInt);
	}

	public void altaProveedor(int parseInt, String text) throws RemoteException {
		remoto.altaProveedor(parseInt, text);
	}

	public void bajaProveedor(int parseInt) throws RemoteException {
		remoto.bajaProveedor(parseInt);
		
	}


	public ProveedorDTO solicitarProveedorView(int parseInt) throws RemoteException {
		return remoto.solicitarProveedorView(parseInt);
	}


	public void modificarProveedor(ProveedorDTO pv, int parseInt) throws RemoteException {
		remoto.modificarProveedor(pv, parseInt);
		
	}

}
