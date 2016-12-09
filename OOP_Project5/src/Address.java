
import java.util.Scanner;;

/**
 * Class that represents address
 * 
 * @author Davud Daskin
 * @version 10 october
 */
public class Address {
	private String street;
	private int number;
	private String zipCode;
	private String city;

	/**
	 * Getter for street
	 * 
	 * @return street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Getter for number
	 * 
	 * @return number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Getter for ZipCode
	 * 
	 * @return ZipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Getter for city
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Constructor of address
	 * 
	 * @param street
	 * @param number
	 * @param zipCode
	 * @param city
	 */
	public Address(String street, int number, String zipCode, String city) {
		this.street = street;
		this.number = number;
		this.zipCode = zipCode;
		this.city = city;
	}

	/**
	 * Reads from scanner to form address
	 * 
	 * @param sc
	 *            - The sanner readed from
	 * @return Address constructed from inputted values for the parameters
	 */
	public static Address read(Scanner sc) {

		//4 next inputted values is address
		try {
			return new Address(sc.next(), sc.nextInt(), sc.next(), sc.next());

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * String representation of address
	 */
	public String toString() {
		String res = new String();
		res = "<Address(" + street + ", " + number + ", " + zipCode + ", " + city + ")>";
		return res;
	}

	/**
	 * Checks if other is equal to address compared with
	 * 
	 * @param other
	 *            - the address you want to compare
	 */
	public boolean equals(Object other) {
		if (other instanceof Address) {
			Address as = (Address) other;

			//compares values
			if (this.getZipCode().equals(as.getZipCode()) && this.number == as.getNumber()) {
				return true;
			}
		}
		return false;

	}

}
