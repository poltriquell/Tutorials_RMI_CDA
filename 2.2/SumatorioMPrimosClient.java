import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SumatorioMPrimosClient {

    private static long upperLimit = 1000000;

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

            System.out.println("Adding primes up to " + upperLimit + " SYNC MODE ...");

            // Llamar al método remoto que devuelve el resultado de la suma sincrona
            long resultSynchronized = stub.addSynchronized(upperLimit);
            System.out.println("addSynchronized(" + upperLimit + ") = " + resultSynchronized);

            // Llamada al método remoto que devuelve el resultado de la suma asincrona
            System.out.println("Adding primes up to " + upperLimit + " ASYNC MODE ...");

            try {
                stub.addAsynchronized(upperLimit);
            } catch (Exception e) {
                System.out.println("Successfully caught exception: " + e);
            }

            while (!stub.addAsynchronizedDone()) {
                System.out.println("Waiting for addAsynchronized(" + upperLimit + ") to finish...");

                Thread.sleep(1000);
            }

            // Llamada al método remoto que devuelve el resultado de la suma asincrona
            long resultAsync = stub.getFinalAsynchronizedResult();
            System.out.println("addAsynchronized(" + upperLimit + ") = " + resultAsync);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
