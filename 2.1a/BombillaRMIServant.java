import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;


// Implementacion Servidor
public class BombillaRMIServant extends UnicastRemoteObject 
										  implements BombillaRMI
{
	private static final long serialVersionUID = 1;
	
	private boolean luzOn;

	private int powerConsumption;

	private int temperature;
	
	// Constructor.
	public BombillaRMIServant() throws RemoteException
	{
		// Asignar valor por defecto = off
		setBombilla(false);
	}

	// Metodo remoto -> Enciende la Bombilla.
	public void on() throws RemoteException
	{
		// Encender Bombilla.
		setBombilla(true);
	}

	// Metodo remoto -> Apagar la Bombilla.	
	public void off() throws RemoteException
	{
		// Apagar Bombilla.
		setBombilla(false);
	}

	// Metodo remoto -> Devuelve el estado de la Bombilla.	
	public boolean isOn() throws RemoteException
	{
		return getBombilla();
	}

	// Implementacion de los métodos añadidos para consultar y modificar la temperatura y el consumo

	@Override
	public int getPowerConsumption() throws RemoteException {
		return powerConsumption;
	}

	@Override
	public void setPowerConsumption(int powerConsumption) throws RemoteException {
		this.powerConsumption = powerConsumption;
	}

	@Override
	public int getTemperature() throws RemoteException {
		return temperature;
	}

	@Override
	public void setTemperature(int temperature) throws RemoteException {
		this.temperature = temperature;
	}

	// Metodo local -> Modificar el estado de la bombilla.
	public void setBombilla(boolean valor)
	{
		luzOn = valor;
	}
	
	// Metodo local -> Devolver el estado de la bombilla.
	public boolean getBombilla()
	{
		return(luzOn);
	}		
}
