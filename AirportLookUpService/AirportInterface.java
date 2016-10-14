import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AirportInterface extends Remote {
	public String[] findAirport(double lat, double lon) throws RemoteException;
}
