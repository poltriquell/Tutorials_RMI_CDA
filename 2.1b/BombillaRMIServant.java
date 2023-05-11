import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.*;


// Implementacion Servidor
public class BombillaRMIServant extends UnicastRemoteObject 
										  implements BombillaRMI, Runnable
{
	private static final long serialVersionUID = 1;
	
	private boolean luzOn;

	private int powerConsumption;

	private int temperature;

	private HashSet<TemperatureListener> listenersList = new HashSet<TemperatureListener>();
	
	// Constructor.
	public BombillaRMIServant() throws RemoteException
	{
		// Asignar valor por defecto = off
		setBombilla(false);

		// Asignar valor a la temperatura por defecto
		setTemperature(3000);
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

	// Metodos para los callbacks de temperatura


	public void setTemperatureCallbacks(double temp) throws RemoteException { temperature = (int) temp; }

	@Override
	public double getTemperatureCallbacks() throws RemoteException { return temperature; }

	@Override
	public void addTemperatureCallbacksListener(TemperatureListener listener) throws RemoteException {
		System.out.println("Adding listener" + listener);
		listenersList.add(listener);
	}

	@Override
	public void removeTemperatureCallbacksListener(TemperatureListener listener) throws RemoteException {
		System.out.println("Removing listener" + listener);
		listenersList.remove(listener);
	}

	// Utilizar el metodo run para generar los callbacks del cambio de temperatura
	public void run() {
		SecureRandom rand = new SecureRandom();
		while (true) {
			try {
				Thread.sleep(3000);

				// Generar un número aleatorio para simular el cambio de temperatura
				int randomNumber = rand.nextInt();

				if (randomNumber < 0) {
					randomNumber = randomNumber * 2;
					System.out.println("Increasing temperature by: " + randomNumber + " degrees");
				} else {
					randomNumber = randomNumber - 200;
					System.out.println("Decreasing temperature by: " + randomNumber + " degrees");
				}

				// Notificar a los listeners del cambio de temperatura
				for (Iterator<TemperatureListener> iterator = listenersList.iterator(); iterator.hasNext();) {
					TemperatureListener listener = iterator.next();
					try {
						listener.temperatureChanged(temperature);
					} catch (RemoteException re) {
						System.out.println("Listener not accessible, removing listener: " + listener);
						iterator.remove();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
