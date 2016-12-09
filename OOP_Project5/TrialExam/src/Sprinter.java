
public class Sprinter extends Train {

	public Sprinter() {
		
	}

	public boolean equals(Object other) {
		if (other instanceof Sprinter)
		{
			Sprinter sprinter = (Sprinter) other;
			if (sprinter.getTimeOfArrival().equals(this.getTimeOfArrival())
					&& sprinter.getTimeOfDeparture().equals(this.getTimeOfDeparture())
					&& sprinter.getTrainStations().equals(this.getTrainStations()))
				return true;
		}
		
		return false;
	}
}
