import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SumatorioMPrimosServer extends SumatorioMPrimosServant {
    public SumatorioMPrimosServer() throws RemoteException {}

    public static void main(String[] args) {
        System.out.println("Starting SumatorioMPrimosServer...");

        try {
            SumatorioMPrimosServant servant = new SumatorioMPrimosServant();
            SumatorioMPrimos stub = (SumatorioMPrimos) UnicastRemoteObject.exportObject( servant, 0);

            // Crear el registro JNDI
            Registry registry = LocateRegistry.createRegistry( 1099);

            // Registrar el objeto remoto en el registro JNDI con el nombre "SumatorioMPrimos"
            registry.rebind("SumatorioMPrimos", stub);

            System.out.println("SumatorioMPrimosServer ready.");

        } catch (Exception e) {
            System.out.println("SumatorioMPrimosServer failed: " + e);
        }
    }
}
