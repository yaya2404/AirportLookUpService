import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.FileInputStream;
import PlaceData.PlaceDataProto.*;


public class PlaceServer {
    public static void main(String args[]) {
	
		int port = 1099;
        try {
            // first command-line argument is the port of the rmiregistry
			if(args.length == 1){
				port = Integer.parseInt(args[0]);
			}
            String url = "//localhost:" + port + "/Places";
            System.out.println("binding " + url);
			PlaceList list = PlaceList.parseFrom(new FileInputStream("places-proto.bin"));
			Places a = new Places(list);
            Naming.rebind(url, a);
            System.out.println("server " + url + " is running...");
        }
        catch (Exception e) {
            System.out.println("Place server failed: " + e.getMessage());
			System.exit(1);
        }
    }
}
