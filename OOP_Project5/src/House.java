import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class that respresents a House
 * 
 * @author Davud
 * @version 10 october
 */
public abstract class House {
	private Address address;
	private int nRooms;
	private int salePrice;
	private boolean available;
	private HeatingSystem heatingSystem;

	public abstract String getAvailibilityText();

	/**
	 * Getter for address
	 * 
	 * @return address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Getter for amount of rooms
	 * 
	 * @return amount of rooms
	 */
	public int getnRooms() {
		return nRooms;
	}

	/**
	 * Getter for SalePrice
	 * 
	 * @return SalePrice
	 */
	public int getSalePrice() {
		return salePrice;
	}

	/**
	 * Constructor for House
	 * 
	 * @param address
	 * @param nRooms
	 * @param salePrice
	 */
	public House(Address address, int nRooms, int salePrice, boolean available, HeatingSystem heatingSystem) {
		this.address = address;
		this.nRooms = nRooms;
		this.salePrice = salePrice;
		this.available = available;
		this.heatingSystem = heatingSystem;
	}

	/**
	 * Checks if salePrice of a house is lesser than/equal to int price
	 * 
	 * @param price
	 *            - budget
	 * @return true or false
	 */
	public boolean costsAtMost(int price) {
		if (this.getSalePrice() <= price) {
			return true;
		}
		return false;
	}

	/**
	 * Read from scanner to form House
	 * 
	 * @param sc
	 *            - the scanner read from
	 * @return House constructed from read values for the parameters
	 */
	public static House read(Scanner sc) {

		if (!sc.hasNextLine()) {
			throw new InputMismatchException();
		}

		// skips amount of houses
		sc.nextLine();

		String houseState = sc.nextLine();
		Address address = Address.read(sc);

		if (!sc.hasNextInt()) {
			throw new NoSuchElementException();
		}

		int numberOfRooms = sc.nextInt();

		if (!sc.hasNext()) {
			throw new InputMismatchException();
		}
		sc.next();

		if (!sc.hasNext()) {
			throw new InputMismatchException();
		}
		sc.next();

		if (!sc.hasNextInt())
			throw new InputMismatchException();

		int price = sc.nextInt();

		if (!sc.hasNextLine())
			throw new InputMismatchException();

		String heatingSystemString = sc.next();

		// if for sale or sold constructs a ooh
		House house = null;
		if (houseState.equals("FOR SALE:") || houseState.equals("SOLD:")) {
			boolean availability = (houseState == "FOR SALE:");
			HeatingSystem heatingSystem;

			if (heatingSystemString == "boiler")
				heatingSystem = new Boiler();
			else
				heatingSystem = new CentralHeating();

			house = new OwnerOccupiedHouse(address, numberOfRooms, price, availability, heatingSystem);
		}

		// if for rent or rented constructs a rh
		if (houseState.equals("FOR RENT:") || houseState.equals("RENTED:")) {
			boolean availability = (houseState == "FOR RENTL:");
			HeatingSystem heatingSystem;

			if (heatingSystemString == "boiler")
				heatingSystem = new Boiler();
			else
				heatingSystem = new CentralHeating();

			house = new RentalHouse(address, numberOfRooms, price, availability, heatingSystem);
		}
		// return result
		return house;

	}

	/**
	 * String representation of "House"
	 */
	public String toString() {
		return "<House(" + address.toString() + nRooms + " " + salePrice + ")>";
	}

	/**
	 * Checks if this House is equal to other; value and class
	 * 
	 * @param other
	 *            - the object you compare "this" with
	 */
	public boolean equals(Object other) {
		if (other instanceof House) {
			House hs = (House) other;
			if (this.getAddress().equals(hs.getAddress())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Getter for availability
	 * 
	 * @return - availability
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Getter for hs
	 * 
	 * @return - heatngsystem
	 */
	public HeatingSystem getHeatingSystem() {
		return heatingSystem;
	}

	/**
	 * Gives a house an energy label according to the amount of rooms and type
	 * of heatingsystem
	 * 
	 * @param rooms
	 *            - amount of rooms
	 * @param heatingSystem
	 *            - type of heatingsystem (b/c)
	 * @return - Char for energy label
	 */
	public static char getEfficiency(int rooms, HeatingSystem heatingSystem) {
		int multiplier = 0;
		if (heatingSystem.getClass() == CentralHeating.class)
			multiplier = 2;

		if (heatingSystem.getClass() == Boiler.class)
			multiplier = 1;

		if (rooms <= 1 * multiplier)
			return 'A';
		if (rooms <= 2 * multiplier)
			return 'B';
		if (rooms <= 3 * multiplier)
			return 'C';
		if (rooms <= 4 * multiplier)
			return 'D';
		if (rooms <= 5 * multiplier)
			return 'E';

		throw new IllegalArgumentException();

	}

}
