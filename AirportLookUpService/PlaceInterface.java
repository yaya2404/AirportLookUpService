import java.rmi.Remote;
import java.rmi.RemoteException;
import PlaceData.PlaceDataProto.*;

public interface PlaceInterface extends Remote {
	public Place findPlace(String city, String state) throws RemoteException;
}
