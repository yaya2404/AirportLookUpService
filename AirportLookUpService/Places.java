import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.*;
import PlaceData.PlaceDataProto.*;

// this is the class with remote methods

public class Places
  extends UnicastRemoteObject
  implements PlaceInterface {
	PlaceList list;
    public Places(PlaceList list) throws RemoteException {
		this.list = list;
    }
	
	public Place findPlace(String city, String state) throws RemoteException{
			for(Place place: this.list.getPlaceList()){
				if(city.regionMatches(false,0,place.getName(),0,city.length()) && state.equals(place.getState())){
					return place;
				}
			}
			return null;
	}
}