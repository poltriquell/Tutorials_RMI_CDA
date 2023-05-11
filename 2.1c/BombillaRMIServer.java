import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;


// Servidor
public class BombillaRMIServer extends BombillaRMIServant // Añadir extends BombillaRMIServant
{
	public BombillaRMIServer() throws RemoteException {};

	public static void main(String args[]) {

		System.out.println("Cargando Servicio RMI");

		// Instanciar el Security Manager
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
				// Cargar el servicio.
				BombillaRMIServant servicioBombilla = new BombillaRMIServant();

				// Exportar el objeto
				BombillaRMI bombilla = (BombillaRMI) UnicastRemoteObject.exportObject(servicioBombilla, 0);

				// Establecer la conexión entre el stub (objeto remoto) y el registro de RMI.
				Registry registry = LocateRegistry.getRegistry();

				// Registrar el servicio en el registro de RMI.
				registry.rebind("BombillaRMICallbacks", bombilla);

				System.err.println("Server ready");

				// Crear un hilo, y pasarle el servidor de sensores.
				// Esto activará el método run(), y provocará cambios regulares de temperatura.
				Thread thread = new Thread (servicioBombilla);
				thread.start();

		}
		catch (RemoteException re)
		{
			System.err.println("Remote Error - " + re);
		}
		catch (Exception e)
		{
			System.err.println("Error - " + e);
		}
	}
}