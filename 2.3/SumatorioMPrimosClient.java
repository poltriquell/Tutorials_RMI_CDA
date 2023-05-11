import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SumatorioMPrimosClient implements SumatorioMPrimosListener {

    private static long upperLimit = 1000000;

    private static SumatorioMPrimos stub;

    public static void main(String[] args) {
        System.out.println("Searching for the primeAddition service...");

        try {
            String host = "localhost";
            if (args.length > 0) {
                upperLimit = Long.parseLong(args[0]);
                if (args.length > 1) {
                    host = args[1];
                }
            }

            // Obtener el registro de objetos remotos
            Registry registry = LocateRegistry.getRegistry(host);

            // Buscar el objeto remoto en el registro y obtener su stub
            SumatorioMPrimos stub = (SumatorioMPrimos) registry.lookup("SumatorioMPrimos");

            System.out.println("Service found.");

            // Crear listener y registrarlo en el objeto remoto (servidor)
            SumatorioMPrimosClient listener = new SumatorioMPrimosClient();
            SumatorioMPrimosListener stubListener = (SumatorioMPrimosListener) UnicastRemoteObject.exportObject(listener, 0);
            stub.addListener(stubListener);
            System.out.println("Listener created.");

            // Esperar a que el usuario pulse enter para comenzar la computación
            System.out.println("Please hit enter key to start the computation (only in master).");
            System.in.read();

            System.out.println("Adding primes up to " + upperLimit + " SYNC MODE ...");

            // Empieza la computación
            System.out.println("Starting the computation...");
            stub.addAsynchronized(upperLimit);
            System.out.println("Computation started.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public long addSynchronized(long begin, long end) throws RemoteException {
        System.out.println("addSync(" + begin + "," + end + ").");
        try {
            return stub.addSynchronized(begin, end);

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addAsynchronizedDone() throws RemoteException {
        System.out.println("Computation of a listener ended.");
    }
}
