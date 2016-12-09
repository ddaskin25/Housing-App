
public class Intercity extends Train {

	public Intercity() {
		
	}

	public boolean equals(Object other) {
		if (other instanceof Intercity)
		{
			Intercity intercity = (Intercity) other;
			if (intercity.getTimeOfArrival().equals(this.getTimeOfArrival())
					&& intercity.getTimeOfDeparture().equals(this.getTimeOfDeparture())
					&& intercity.getTrainStations().equals(this.getTrainStations()))
				return true;
		}
		
		return false;
	}
	
}
