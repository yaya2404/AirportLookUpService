import java.util.Comparator;

public class airportData implements Comparator<airportData>{
	private String code = "";
	private String name = "";
	private String state = "";
	private int distance = 0;
	
	public airportData(){
		
	}
	
	public void setCode(String code){
		this.code = code;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setState(String state){
		this.state = state;
	}
	public void setDistance(int distance){
		this.distance = distance;
	}
	public String getCode(){
		return this.code;
	}
	public String getName(){
		return this.name;
	}
	public String getState(){
		return this.state;
	}
	public int getDistance(){
		return this.distance;
	}
	public int compare(airportData first, airportData second){
		return first.getDistance() - second.getDistance();
	}
	public String toString(){
		return "code=" + this.code + ", name=" + this.name + ", state=" + this.state + " distance: " + this.distance + "miles";
	}
}