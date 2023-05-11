import java.rmi.*;

public interface BombillaRMI extends java.rmi.Remote
{
	public void on() throws RemoteException;
	public void off() throws RemoteException;
	public boolean isOn() throws RemoteException;

	// Métodos añadidos para consultar y modificar la temperatura y el consumo

	public int getPowerConsumption() throws RemoteException;

	public void setPowerConsumption(int powerConsumption) throws RemoteException;

	public int getTemperature() throws RemoteException;

	public void setTemperature(int temperature) throws RemoteException;

	// Callbacks de temperatura
	public double getTemperatureCallbacks() throws RemoteException;

	public void addTemperatureCallbacksListener ( TemperatureListener listener ) throws RemoteException;

	public void removeTemperatureCallbacksListener ( TemperatureListener listener ) throws RemoteException;
}

