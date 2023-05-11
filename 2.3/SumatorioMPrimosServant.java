import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Vector;

public class SumatorioMPrimosServant implements SumatorioMPrimos {

    private long finalResult = 0;
    private final Object asynchronizedLock = new Object();
    private boolean asynchronizedDone = true;

    private final HashSet<SumatorioMPrimosListener> listenersList = new HashSet<>();

    public long addSynchronized(long number, long end) throws RemoteException {
        synchronized (asynchronizedLock) {
            return calcularSumaPrimos(number);
        }
    }

    public void addAsynchronized(long number) throws Exception {
        synchronized (asynchronizedLock) {
            if (!asynchronizedDone) {
                throw new Exception("Async addition already in progress.");
            }
            asynchronizedDone = false;
            Thread t = new Thread(() -> {
                long result = calcularSumaPrimos(number);
                synchronized (asynchronizedLock) {
                    finalResult = result;
                    asynchronizedDone = true;
                }
            });
            t.start();
        }
    }

    public long getFinalAsynchronizedResult() throws RemoteException {
        synchronized (asynchronizedLock) {
            return asynchronizedDone ? finalResult : 0;
        }
    }

    public boolean addAsynchronizedDone() throws RemoteException {
        synchronized (asynchronizedLock) {
            return asynchronizedDone;
        }
    }

    @Override
    public void addListener(SumatorioMPrimosListener listener) throws RemoteException {
        System.out.println("Adding listener " + listener);
        listenersList.add(listener);
        System.out.println("Total listeners: " + listenersList.size());
    }

    @Override
    public void removeListener(SumatorioMPrimosListener listener) throws RemoteException {
        System.out.println("Removing listener " + listener);
        listenersList.remove(listener);
    }

    // Metodos auxiliares para calcular la suma de los numeros primos de la practica 1
    public static long calcularSumaPrimos(long number) {
        long sumaPrimos = 0;

        for (int i = 2;i < number; i++) {
            if (i % 2 != 0) {
                if (esPrimo(i)) { sumaPrimos += i; }
            }
        }
        return sumaPrimos;
    }

    public static boolean esPrimo(long numero) {
        for (int i = 3; i * i <= numero; i += 2) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

}
