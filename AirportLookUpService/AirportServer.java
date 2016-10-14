import java.rmi.Naming;
import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.io.FileInputStream;
import AirportData.AirportDataProto.*;

public class AirportServer {
    public static void main(String args[]) {
		int port = 1099;
        try {
            // first command-line argument is the port of the rmiregistry
            if(args.length == 1){
				port = Integer.parseInt(args[0]);
			}
            String url = "//localhost:" + port + "/Airports";
			AirportList list = AirportList.parseFrom(new FileInputStream("airports-proto.bin"));
			Airports a = new Airports(list);
            System.out.println("binding " + url);
            Naming.rebind(url, a);
            System.out.println("server " + url + " is running...");
			
        }catch (Exception e) {
            System.out.println("Airport server failed: " + e.getMessage());
			System.exit(1);
        }
    }
}
