import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Davud
 * @version 18 october
 */
public class HousingApplication {

	/**
	 * Application for Housing
	 * 
	 * @param Uses
	 *            user inpit
	 * @throws Exception
	 *             - error if inputted wrong option
	 */
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		String FILE_PATH = "houses.txt";

		// creates house catalog from houses read from file path(could be any
		// file)
		HouseCatalog hc = HouseCatalog.read(FILE_PATH);

		try {
			while (true) {

				// prints options
				printOptions();
				int option = sc.nextInt();

				// Constructs house from user input, owneroccupied, and adds to
				// the catalog
				if (option == 1) {
					House currentHouse = getHouseFromInput(sc, OwnerOccupiedHouse.class);
					hc.addHouse(currentHouse);
					System.out.println("Added new house!\r\n");

				}

				// Constructs house from user input, rental, and adds to catalog
				else if (option == 2) {
					House currentHouse = getHouseFromInput(sc, RentalHouse.class);
					hc.addHouse(currentHouse);
					System.out.println("Added new house!\r\n");

				}

				// Output houses after being filtered according to inputted
				// values of user
				else if (option == 3) {
					outputIncludedHouses(sc, hc);
					System.out.println();

					// Writes houses to file and closes programm
				} else if (option == 4) {

					// writes to file path
					PrintWriter fileWriter = new PrintWriter(FILE_PATH, "UTF-8");

					// gets number of houses
					int numberOfHouses = hc.getHouses().size();

					// first writes size of hc and newline
					fileWriter.write(numberOfHouses + "\r\n");

					// for every house in hc converts this house to string and
					// writes that string to file path
					for (House house : hc.getHouses()) {
						fileWriter.write(convertHouseToFileText(house));
					}
					// exit
					fileWriter.close();
					sc.close();
					break;

				}
			}
			// after exit
			System.out.println("Application closed");

			// if inputted invalid option saves the file by writing already
			// excisting houses back
		} catch (Exception exception) {
			System.err.println("An error has occured");
			System.err.println("Writing houses to file...");
			PrintWriter fileWriter = new PrintWriter(FILE_PATH, "UTF-8");
			int numberOfHouses = hc.getHouses().size();
			fileWriter.write(numberOfHouses + "\r\n");

			for (House house : hc.getHouses()) {
				fileWriter.write(convertHouseToFileText(house));
			}
			fileWriter.close();
			throw exception;
		}

	}

	/**
	 * Shows houses filtered by user inputted max price, min efficiency and
	 * availability
	 * 
	 * @param sc
	 *            - scanner used
	 * @param hc
	 *            - Catalog in wich filtered houses are stored
	 */
	private static void outputIncludedHouses(Scanner sc, HouseCatalog hc) {

		// asks for max price and min efficiency
		int maxPrice = promptInt("Enter the maximum price: ", sc);
		String minEfficiency = promptString("Enter the minimum energy efficiency (A-E): ", sc).toUpperCase();

		if (!minEfficiency.equals("A") && !minEfficiency.equals("B") && !minEfficiency.equals("C")
				&& !minEfficiency.equals("D") && !minEfficiency.equals("E")) {
			throw new IllegalArgumentException();
		}

		// Asks for house state to be used in filter
		String houseState = promptString("Is the house for sale or for rent (S/R)", sc).toUpperCase();
		boolean forRent;
		if (houseState.equals("S") || houseState.equals("R")) {
			forRent = houseState.equals("R");
		} else {
			throw new InputMismatchException();
		}

		// asks if still available
		boolean availabilityOfHouse = promptBoolean("Should the house be available? (Y/N)", sc);

		// loops through every house in hc
		for (House house : hc.getHouses()) {
			char efficiency = House.getEfficiency(house.getnRooms(), house.getHeatingSystem());
			char minimumEfficiency = minEfficiency.toCharArray()[0];

			// if all filters match returns houses with sufficient values
			if (house.costsAtMost(maxPrice) && efficiency <= minimumEfficiency
					&& house.isAvailable() == availabilityOfHouse) {
				if (forRent && house instanceof RentalHouse) {
					System.out.println(house);
				} else if (!forRent && house instanceof OwnerOccupiedHouse) {
					System.out.println(house);
				}
			}
		}

	}

	/**
	 * To start the program prints the options
	 */
	private static void printOptions() {
		System.out.println("      Choose an option: ");
		System.out.println("[1] - Add a new OccupiedHouse");
		System.out.println("[2] - Add a new RentalHouse");
		System.out.println("[3] - Show houses");
		System.out.println("[4] - Close the application\r\n");
		System.out.println("Your option > ");

	}

	/**
	 * Asks for string
	 * 
	 * @param promptMessage
	 *            - the question
	 * @param sc
	 *            - scanner used
	 * @return - string
	 */
	private static String promptString(String promptMessage, Scanner sc) {
		System.out.println(promptMessage);
		System.out.println(">");

		try {
			return sc.next();
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Asks for Int
	 * 
	 * @param promptMessage
	 *            - The relevant question
	 * @param sc
	 *            - scanner used
	 * @return - integer
	 */
	private static int promptInt(String promptMessage, Scanner sc) {
		System.out.println(promptMessage);
		System.out.println(">");

		try {
			return sc.nextInt();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Asks if house has centralheating or boiler, used in constructor
	 * 
	 * @param promptMessage
	 *            - the question
	 * @param sc
	 *            - scanner used
	 * @return - B/C
	 */
	private static HeatingSystem promptHeatingSystem(String promptMessage, Scanner sc) {
		System.out.println(promptMessage);
		System.out.println(">");

		try {
			String inputString = sc.next();
			if (inputString.toLowerCase().equals("boiler")) {
				return new Boiler();
			} else if (inputString.toLowerCase().equals("centralheating")) {
				return new CentralHeating();
			} else {
				throw new InputMismatchException();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Asks for question then returns a boolean according to respectively
	 * inputted Y/N
	 * 
	 * @param promptMessage
	 *            - the question
	 * @param sc
	 *            - scanner used
	 * @return - true/false
	 */
	private static boolean promptBoolean(String promptMessage, Scanner sc) {
		try {
			System.out.println(promptMessage);
			String inputString = sc.next();
			if (inputString.toUpperCase().equals("Y") || inputString.toUpperCase().equals("YES")) {
				return true;
			} else if (inputString.toUpperCase().equals("N") || inputString.toUpperCase().equals("NO")) {
				return false;
			} else {
				throw new InputMismatchException();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Constructs house from user input
	 * 
	 * @param sc
	 *            - scanner used for input
	 * @param houseClass
	 *            - Class if house (R/O)
	 * @return - Returns Constructed RentalHouse or OwnerOccupiedHouse
	 */
	private static House getHouseFromInput(Scanner sc, @SuppressWarnings("rawtypes") Class houseClass) {
		String streetName = promptString("Enter street", sc);
		int houseNumber = promptInt("Enter house number", sc);
		String zipCode = promptString("Enter postal code", sc);
		String houseCity = promptString("Enter city", sc);
		int numberOfRooms = promptInt("Enter number of rooms", sc);
		int housePrice = promptInt("Enter price", sc);
		boolean isAvailable = promptBoolean("Is the house available (Y/N)", sc);
		HeatingSystem heatingSystem = promptHeatingSystem("Enter the heating system for the house", sc);

		Address address = new Address(streetName, houseNumber, zipCode, houseCity);

		if (houseClass.equals(OwnerOccupiedHouse.class)) {
			return new OwnerOccupiedHouse(address, numberOfRooms, housePrice, isAvailable, heatingSystem);
		} else if (houseClass.equals(RentalHouse.class)) {
			return new RentalHouse(address, numberOfRooms, housePrice, isAvailable, heatingSystem);
		} else
			throw new InputMismatchException();
	}

	/**
	 * Converts house to string
	 * 
	 * @param currentHouse
	 *            - house to be converted
	 * @return - String representation of house
	 */
	private static String convertHouseToFileText(House currentHouse) {

		// Constructs string same as in example by using new lines
		String houseData = "";
		houseData += currentHouse.getAvailibilityText() + ":\r\n";
		houseData += currentHouse.getAddress().getStreet() + " " + currentHouse.getAddress().getNumber() + "\r\n";
		houseData += currentHouse.getAddress().getZipCode() + " " + currentHouse.getAddress().getCity() + "\r\n";
		houseData += currentHouse.getnRooms() + " rooms\r\n";
		houseData += "saleprice " + currentHouse.getSalePrice() + "\r\n";

		if (currentHouse.getHeatingSystem() instanceof Boiler) {
			houseData += "boieler\r\n";
		} else if (currentHouse.getHeatingSystem() instanceof CentralHeating) {
			houseData += "centralheating\r\n";
		} else {
			throw new IllegalArgumentException();
		}
		return houseData;
	}

}
