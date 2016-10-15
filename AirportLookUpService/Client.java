import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import AirportData.AirportDataProto.*;
import PlaceData.PlaceDataProto.*;

public class Client  {
    public static void main(String args[]) {
		String ahost = "localhost";
		String phost = "localhost";
		String city = "";
		String state = "";
		int aport = 1099, pport = 1099;
		
        try {
            if (args.length < 2) {
                System.err.println("usage: java Client -ah airportserver -ap airportserverport -ph placeserverhost -pp placeserverport city state ... \n");
                System.exit(1);
            }
			
			String airporturl;
			String placeurl;
			int i = 0;
			if(args.length > 2){
				for(i = 0; i < args.length - 2; i+=2){
					if(args[i].equals("-ah")){
						ahost = args[i+1];
					}else if(args[i].equals("-ap")){
						aport = Integer.parseInt(args[i+1]);
					}else if(args[i].equals("-ph")){
						phost = args[i+1];
					}else if(args[i].equals("-pp")){
						pport = Integer.parseInt(args[i+1]);
					}
				}
			}
			city = args[i];
			state = args[i+1];
			airporturl = "//" + ahost + ":" + aport + "/Airports";
			placeurl = "//" + phost + ":" + pport + "/Places";
			
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
		}catch(ArrayIndexOutOfBoundsException c){
			System.out.println("ArrayIndexOutOfBoundsException: you must have forgotten the city or state.");
		}catch(RemoteException b){
			System.out.println("RemoteException: remote method findPlace() or findAirport() failed.");
		}catch(NotBoundException a){
			System.out.println("NotBoundException: could not connect to host on given port number");
			System.exit(1);
		}catch(NullPointerException r){
			System.out.println("Could not find city/state or could not gather nearest airports");
			System.exit(1);
		}catch(Exception e){
            System.out.println("Client exception: " + e);
			System.exit(1);
        }
    }
}
