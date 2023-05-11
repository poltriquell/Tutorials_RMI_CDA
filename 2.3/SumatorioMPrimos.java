import java.rmi.*;
public interface SumatorioMPrimos extends Remote {
    long addSynchronized(long begin, long end) throws RemoteException;
    void addAsynchronized(long number) throws Exception;
    long getFinalAsynchronizedResult() throws RemoteException;
    boolean addAsynchronizedDone() throws RemoteException;

    // Metodos para el listener (añadir y quitar)
    void addListener(SumatorioMPrimosListener listener) throws RemoteException;

    void removeListener(SumatorioMPrimosListener listener) throws RemoteException;
}
