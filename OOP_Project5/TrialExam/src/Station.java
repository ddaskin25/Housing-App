
public class Station extends TrainStation {

	public Station(String stationName) {
		super(stationName);
	}
	
	public boolean equals(Object other) {
		if (other instanceof Station)
		{
			Station station = (Station) other;
			if (station.getStationName().equals(this.getStationName()))
				return true;
		}
		
		return false;
	}
}
