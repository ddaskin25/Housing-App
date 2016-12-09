import java.util.ArrayList;
import java.util.Scanner;

public abstract class Train {
	
	String timeOfDeparture;
	String timeOfArrival;
	ArrayList<TrainStation> listOfStations;
	
	public Train() {
		listOfStations = new ArrayList<TrainStation>();
	}
	
	public String getTimeOfDeparture() {
		return this.timeOfDeparture;
	}
	
	public String getTimeOfArrival() {
		return this.timeOfArrival;
	}
	
	public void setTimeOfDeparture(String timeOfDeparture) {
		if (this.timeOfDeparture != null)
			throw new IllegalAccessError();
		
		this.timeOfDeparture = timeOfDeparture;
	}
	
	public void setTimeOfArrival(String timeOfArrival) {
		if (this.timeOfArrival != null)
			throw new IllegalAccessError();
		
		this.timeOfArrival = timeOfArrival;
	}
	
	public void addStation(TrainStation trainStation) {
		this.listOfStations.add(trainStation);
	}
	
	public ArrayList<TrainStation> getTrainStations() {
		return this.listOfStations;
	}
	
	public static Train readTrain(Scanner scanner, String next) {
		String nextElement = next;
		
		while (nextElement.equals(""))
			nextElement = scanner.nextLine();
		
		Train currentTrain = null;

		if (nextElement.contains("<SPRINTER>"))
			currentTrain = new Sprinter();
		else if (nextElement.contains("<INTERCITY>"))
			currentTrain = new Intercity();
		else
			throw new IllegalArgumentException();
		
		nextElement = scanner.nextLine();
		while (!nextElement.contains("</INTERCITY>") && !nextElement.contains("</SPRINTER>")) 
		{
			
			if (nextElement.contains("<DEPARTS>")) 
			{
				String departureTime = nextElement.replace("<DEPARTS>", "").replace("</DEPARTS>", "");

				currentTrain.setTimeOfDeparture(departureTime);
			}
			else if (nextElement.contains("<ARRIVES>")) 
			{
				String arrivalTime = nextElement.replace("<ARRIVES>", "").replace("</ARRIVES>", "");
				
				currentTrain.setTimeOfArrival(arrivalTime);
			}
			else if (nextElement.contains("<STATION>"))
			{
				String stationName = nextElement.replace("<STATION>", "").replace("</STATION>", "");

				TrainStation trainStation = new Station(stationName);
				currentTrain.addStation(trainStation);
			}
			else if (nextElement.contains("<ICSTATION>")) 
			{
				String stationName = nextElement.replace("<ICSTATION>", "").replace("</ICSTATION>", "");

				TrainStation trainStation = new ICStation(stationName);
				currentTrain.addStation(trainStation);
			}
			nextElement = scanner.nextLine();
		}
		
		return currentTrain;
	}
	
	@Override
	public String toString() {
		String finalString = "";
		
		if (this instanceof Sprinter)
			finalString += "Sprinter from ";
		else if (this instanceof Intercity)
			finalString += "Intercity from ";
		else
			throw new IllegalArgumentException();
		
		finalString += listOfStations.get(0).getStationName();
		finalString += " to " + listOfStations.get(listOfStations.size() - 1).getStationName() + System.lineSeparator();
		
		finalString += "   Departure: " + this.getTimeOfDeparture() + System.lineSeparator();
		finalString += "   Arrival: " + this.getTimeOfArrival() + System.lineSeparator();
		
		int index = 0;
		for (TrainStation trainStation : listOfStations) {
			String trainStationString = "   ---";
			
			if (trainStation instanceof ICStation || index == 0 || index == listOfStations.size() - 1)
			{
				trainStationString += trainStation.getStationName() + System.lineSeparator();
			}
			else if (trainStation instanceof Station)
			{
				trainStationString += "-(" + trainStation.getStationName() + ")" + System.lineSeparator();
			}
			
			finalString += trainStationString;
			index++;
		}
		
		return finalString;
	}
}
