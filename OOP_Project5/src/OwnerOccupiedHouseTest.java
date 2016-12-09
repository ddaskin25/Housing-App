import static org.junit.Assert.*;

import org.junit.Test;

public class OwnerOccupiedHouseTest {

	@Test
	public void AvTest_O() {
		Address as = new Address("a", 1, "b", "c");
		Boiler b = new Boiler();
		House hs = new OwnerOccupiedHouse(as, 1, 1, true, b);
		assertEquals("FOR SALE", hs.getAvailibilityText());
	}
	
	@Test
	public void AvTest2_O() {
		Address as = new Address("a", 1, "b", "c");
		Boiler b = new Boiler();
		House hs = new OwnerOccupiedHouse(as, 1, 1, false, b);
		assertEquals("SOLD", hs.getAvailibilityText());
	}

}
