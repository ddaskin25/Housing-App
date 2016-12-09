
public class ICStation extends TrainStation {

	public ICStation(String stationName) {
		super(stationName);
	}
	
	public boolean equals(Object other) {
		if (other instanceof ICStation)
		{
			ICStation station = (ICStation) other;
			if (this.getStationName().equals(station.getStationName()))
				return true;
		}
		
		return false;
	}
}
