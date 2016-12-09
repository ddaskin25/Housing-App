import static org.junit.Assert.*;

import org.junit.Test;

public class RentalHouseTest {

	@Test
	public void AvTest() {
		Address as = new Address("a", 1, "b", "c");
		Boiler b = new Boiler();
		House hs = new RentalHouse(as, 1, 1, true, b);
		assertEquals("FOR RENT", hs.getAvailibilityText());
	}
	
	@Test
	public void AvTest2() {
		Address as = new Address("a", 1, "b", "c");
		Boiler b = new Boiler();
		House hs = new RentalHouse(as, 1, 1, false, b);
		assertEquals("RENTED", hs.getAvailibilityText());
	}

}
