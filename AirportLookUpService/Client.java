import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import AirportData.AirportDataProto.*;
import PlaceData.PlaceDataProto.*;

public class Client  {
    public static void main(String args[]) {
		String host = "localhost";
		String city = "";
		String state = "";
		int port = 1099;
        try {
            if (args.length < 2 && args.length > 6) {
                System.err.println("usage: java Client -h rmiregistryserver -p port city state ... \n");
                System.exit(1);
            }
			
			String airporturl;
			String placeurl;
			if(args[0].equals("-h")){
				host = args[1];
				if(args[2].equals("-p")){
					port = Integer.parseInt(args[3]);
					city = args[4];
					state = args[5];
				}else{
					city = args[2];
					state = args[3];
				}

			}else if(args[0].equals("-p")){
				port = Integer.parseInt(args[1]);
				city = args[2];
				state = args[3];
			}else{
				city = args[0];
				state = args[1];
			}
			airporturl = "//" + host + ":" + port + "/Airports";
			placeurl = "//" + host + ":" + port + "/Places";
			AirportInterface airport = (AirportInterface)Naming.lookup(airporturl);
			PlaceInterface place = (PlaceInterface)Naming.lookup(placeurl);
			
			Place p = place.findPlace(city, state);
			System.out.println(p.getName() + ", " + p.getState() + ": " + p.getLat() + ", " + p.getLon());
			String[] a = airport.findAirport(p.getLat(), p.getLon());
			System.out.println(a[0]);
			System.out.println(a[1]);
			System.out.println(a[2]);
			System.out.println(a[3]);
			System.out.println(a[4]);
		}catch(NotBoundException a){
			System.out.println("NotBoundException: could not connect to host " + host + " on the port number " + port);
			System.exit(1);
		}catch(NullPointerException r){
			System.out.println("Could not find city/state or could not gather nearest airports");
			System.exit(1);
		}catch(Exception e) {
            System.out.println("Client exception: " + e);
			e.printStackTrace();
			System.exit(1);
        }

		
    }
}
