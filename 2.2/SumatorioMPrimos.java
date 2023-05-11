import java.rmi.*;
public interface SumatorioMPrimos extends Remote {
    long addSynchronized(long number) throws RemoteException;
    void addAsynchronized(long number) throws Exception;
    long getFinalAsynchronizedResult() throws RemoteException;
    boolean addAsynchronizedDone() throws RemoteException;
}
