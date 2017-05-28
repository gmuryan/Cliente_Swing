package main;

import java.rmi.Naming;

import RemoteObject.TDACliente;

public class Main {
	public static TDACliente remoto;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			if (getStub()) {
				System.out.println("Se conecto");
				//List<ClienteDTO> clientes = remoto.GetClientes();
				System.out.println("Levante los clientes");
			} else
				System.out.println("No se conecto");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static boolean getStub() {

		try {
			remoto = (TDACliente) Naming.lookup("//localhost/GestionClientes");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
