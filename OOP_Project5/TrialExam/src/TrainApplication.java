import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TrainApplication {

	public static void main(String[] args) throws Exception {
		Scanner inputScanner = new Scanner(System.in);
		
		// Get the filename from input and use it to read the file
		String fileName = promptString("Enter the filename for the application", inputScanner);
		Path filePath = Paths.get(fileName);
		Scanner fileScanner = new Scanner(filePath);
		
		TrainList trainList = TrainList.readTrains(fileScanner);
		System.out.println("File found, continuing program...");
		System.out.println();

		showOptions();
		int nextInputChoice = promptInt(inputScanner);
		
		while (nextInputChoice != 4) {
			
			if (nextInputChoice == 1)
			{
				System.out.println();
				System.out.println(trainList);
				System.out.println("Done.");
			}
			else if (nextInputChoice == 2)
			{
				System.out.println();
				inputScanner.nextLine();
				String firstStation = promptString("Enter the first station for the train schedule", inputScanner);
				String lastStation = promptString("Enter the last station for the train schedule", inputScanner);
				
				for (Train train : trainList.getTrains()) {
					String trainLast = train.getTrainStations().get(train.getTrainStations().size() - 1).getStationName();
					if (train.getTrainStations().get(0).getStationName().equals(firstStation)
							&& trainLast.equals(lastStation))
						System.out.println(train);
				}
				
				System.out.println("Done.");
			}
			else if (nextInputChoice == 3)
			{
				System.out.println("Writing to file...");
				System.out.println("Done.");
			}
			
			showOptions();
			nextInputChoice = promptInt(inputScanner);
		}

		System.out.println("Terminated.");
		fileScanner.close();
	}

	private static int promptInt(Scanner inputScanner) {
		int nextInputChoice;
		
		System.out.print(" >> ");
		nextInputChoice = inputScanner.nextInt();
		
		return nextInputChoice;
	}

	private static void showOptions() {
		System.out.println("Please make your choice:");
		System.out.println("   1 - Please show all trains in the in-memory database on screen");
		System.out.println("   2 - Please show all trains from station A to station B");
		System.out.println("   3 - Write to file");
		System.out.println("   4 - Stop the program");
	}

	private static String promptString(String promptMessage, Scanner scanner) {
		System.out.println(promptMessage);
		System.out.print(" >> ");
		
		try {
			return scanner.nextLine();
		} catch (Exception exception) {
			throw exception;
		}
		
	}
}
