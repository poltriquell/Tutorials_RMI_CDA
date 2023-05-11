import java.rmi.*;

public interface SumatorioMPrimosListener extends Remote {
    long addSynchronized(long begin, long end) throws RemoteException;

    void addAsynchronizedDone() throws RemoteException;
}
