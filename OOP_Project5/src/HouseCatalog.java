import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Class that represents a HouseCatalog that contains Houses
 * 
 * @author Davud
 * @version 10 october
 */
public class HouseCatalog {
	private Set<House> houses;

	/**
	 * Getter for houses
	 * 
	 * @return houses
	 */
	public Set<House> getHouses() {
		return houses;
	}

	/**
	 * Constructor for HouseCatalog Initializes new HashSet
	 */
	public HouseCatalog() {
		houses = new HashSet<House>();
	}

	/**
	 * Adds house to this houses
	 * 
	 * @param house
	 *            - the house you want to add
	 */
	public void addHouse(House house) {
		this.houses.add(house);
	}

	/**
	 * Checks which houses in this list are under or equal to price and returns
	 * a new list with those houses in it
	 * 
	 * @param price
	 *            - houses use this price in the CostAtMost method
	 * @return List with houses that are under or equal to budget
	 */
	public List<House> housesCostAtMost(int price) {

		// new list to store result in
		List<House> s = new ArrayList<House>();

		// Generates a serie of houses
		Iterator<House> temp = this.houses.iterator();

		// if there is next input
		while (temp.hasNext()) {
			House house = (House) temp.next();
			if (house.costsAtMost(price)) {
				s.add(house);
			}
		}
		return s;

	}

	/**
	 * Reads file, constructs houses and adds those houses to a new HouseCatalog
	 * 
	 * @param fileName
	 *            - file you want read
	 * @return HouseCatalog with houses read from file
	 * @throws Exception
	 *             if input is incorrect
	 */
	public static HouseCatalog read(String fileName) throws Exception {
		try {

			// Sets path for file you want read
			Path filePath = Paths.get(fileName);

			// scans file
			Scanner sc = new Scanner(filePath);

			// first input is number of houses
			int AmountOfHouses = sc.nextInt();

			// temp hc to store result in
			HouseCatalog houseCatalog = new HouseCatalog();

			// loops trough all houses
			for (int index = 0; index < AmountOfHouses; index++) {

				// constucts x amount of houses from read file and puts them in
				// temp
				House house = (House) House.read(sc);
				houseCatalog.addHouse(house);
			}
			return houseCatalog;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * String representation of HouseCatalog
	 */
	@Override
	public String toString() {
		String res = "HouseCatalog<";
		Iterator<House> iterator = this.houses.iterator();

		while (iterator.hasNext()) {
			House house = iterator.next();
			res += house.toString();

			if (iterator.hasNext()) {
				res += ", ";
			}
		}
		res = res + ">";
		return res;
	}

}
