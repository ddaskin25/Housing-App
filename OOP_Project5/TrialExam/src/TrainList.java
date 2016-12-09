import java.util.ArrayList;
import java.util.Scanner;

public class TrainList {
	
	ArrayList<Train> listOfTrains;
	
	public TrainList() {
		listOfTrains = new ArrayList<Train>();
	}
	
	public ArrayList<Train> getTrains() {
		return this.listOfTrains;
	}
	
	public void addTrain(Train train) {
		this.listOfTrains.add(train);
	}
	
	public static TrainList readTrains(Scanner scanner) {
		TrainList trainList = new TrainList();
		
		while (!scanner.nextLine().equals("<TRAINS>")) {
			continue;
		}

		String nextElement = "";
		while (!nextElement.equals("</TRAINS>")) {
			Train newTrain = Train.readTrain(scanner, nextElement);
			trainList.addTrain(newTrain);
			nextElement = scanner.nextLine();
		}
		
		return trainList;
	}
	
	public String toString() {
		String finalString = "";
		
		for (Train train : listOfTrains) {
			finalString += train.toString() + System.lineSeparator();
		}
		
		return finalString;
	}
	
	public boolean equals(Object other) {
		if (other instanceof TrainList)
		{
			TrainList trainList = (TrainList) other;
			if (trainList.getTrains().equals(this.getTrains()))
				return true;
		}
		
		return false;
	}
}
