import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.*;
import AirportData.AirportDataProto.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
// this is the class with remote methods

public class Airports
  extends UnicastRemoteObject
  implements AirportInterface {

	AirportList list;
	
    public Airports(AirportList list) throws RemoteException {
		this.list = list;
    }
	
	public String[] findAirport(double lat, double lon) throws RemoteException{
			List<airportData> dlist = new ArrayList<airportData>();
			double alat, alon;
			String[] finalList = new String[5];
			
			for(Airport airport: this.list.getAirportList()){
				alat = airport.getLat();
				alon = airport.getLon();
				airportData temp = new airportData();
				temp.setCode(airport.getCode());
				temp.setName(airport.getName());
				temp.setState(airport.getState());
				temp.setDistance(
					(int)
					(
						Math.ceil(
							60
							*
							Math.toDegrees(
								Math.acos(
									Math.sin(Math.toRadians(lat))*Math.sin(Math.toRadians(alat)) 
									+ 
									Math.cos(Math.toRadians(lat))* Math.cos(Math.toRadians(alat))*Math.cos(Math.toRadians(alon) - Math.toRadians(lon))
								)
							)
							*
							1.11507794
						)
					)
				);
				dlist.add(temp);
			}
			dlist.sort(new airportData());
			finalList[0] = dlist.get(0).toString();
			finalList[1] = dlist.get(1).toString();
			finalList[2] = dlist.get(2).toString();
			finalList[3] = dlist.get(3).toString();
			finalList[4] = dlist.get(4).toString();
			return finalList;
	}
}