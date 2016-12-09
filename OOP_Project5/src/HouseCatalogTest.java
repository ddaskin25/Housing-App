import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HouseCatalogTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void TestConstructor() {
		HouseCatalog hs = new HouseCatalog();
		assertEquals(hs.getHouses().size(), 0);

	}

	@Test
	public void AddMultipleHouses() {
		Boiler b = new Boiler();
		House hs = new RentalHouse(new Address("a", 1, "b", "c"), 10, 50000, true, b);
		House hs2 = new RentalHouse(new Address("b", 2, "c", "d"), 12, 60000, true, b);
		House hs3 = new RentalHouse(new Address("c", 3, "d", "e"), 15, 70000, true, b);

		HouseCatalog hc = new HouseCatalog();

		hc.addHouse(hs);
		hc.addHouse(hs2);
		hc.addHouse(hs3);

		assertEquals(hc.getHouses().size(), 3);
	}

	@Test
	public void HousesCostAtMostTest() {
		Boiler b = new Boiler();
		House hs = new RentalHouse(new Address("a", 1, "b", "c"), 10, 50000, true, b);
		House hs2 = new RentalHouse(new Address("b", 2, "c", "d"), 12, 60000, true, b);
		House hs3 = new RentalHouse(new Address("c", 3, "d", "e"), 15, 70000, true, b);

		HouseCatalog hc = new HouseCatalog();

		hc.addHouse(hs);
		hc.addHouse(hs2);
		hc.addHouse(hs3);

		List<House> list = hc.housesCostAtMost(65000);
		assertEquals(list.size(), 2);
	}

}
