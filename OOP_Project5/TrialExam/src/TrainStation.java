
public abstract class TrainStation {
	
	String stationName;
	
	public TrainStation(String stationName) {
		this.stationName = stationName;
	}
	
	public String getStationName() {
		return this.stationName;
	}
	
	public String getStationType() {
		if (this instanceof Station){
			return "Station";
		}
		else if (this instanceof ICStation){
			return "ICStation";
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}
