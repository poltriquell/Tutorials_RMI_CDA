import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TemperatureMonitor implements TemperatureListener {
    private static BombillaRMI bombilla;

    public TemperatureMonitor() throws RemoteException {}

    public static void main(String[] args) throws RemoteException {

        System.out.println("Starting temperature monitor...");
        try {
            // Comprobar si se especificó un registro (registry) por parámetro
            String registry = "localhost";
            if (args.length >= 1) {
                registry = args[0];
            }

            // Formato de registro: //registry_hostname (opcional):port /service
            String registro = "rmi://" + registry + "/BombillaRMICallbacks";

            // Buscar el servicio en el registro y obtener un servicio remoto
            Remote servicioRemoto = Naming.lookup(registro);

            // Hacer casting a la interfaz TemperatureSensor
            bombilla = (BombillaRMI) servicioRemoto;

            // Obtener y mostrar la temperatura actual
            double temperaturaActual = bombilla.getTemperatureCallbacks();
            System.out.println("Temperatura Original: " + temperaturaActual);

            // Crear un nuevo monitor y registrarlo como listener con el sensor remoto
            TemperatureMonitor monitor = new TemperatureMonitor();

            // Exportar el objeto de la clase de la implementación al stub del interfase.
            TemperatureListener monitorExportado = (TemperatureListener) UnicastRemoteObject.exportObject(monitor, 0);
            bombilla.addTemperatureCallbacksListener(monitorExportado);

        } catch (NotBoundException nbe) {
            System.out.println ("No sensors available");
        } catch (RemoteException re) {
            System.out.println ("RMI Error - " + re);
        } catch (Exception e) {
            System.out.println ("Error - " + e);
        }
    }

    @Override
    public void temperatureChanged(double temperature) throws RemoteException {
        System.out.println("Temperature changed to " + temperature);
        System.out.println("Light bulb is on: " + bombilla.isOn());
    }
}
