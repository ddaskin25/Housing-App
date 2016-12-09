import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HouseTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void GetterTestAddress() {
		Address as = new Address("a", 1, "b", "c");
		House hs = new RentalHouse(as, 1, 2, false, null);
		assertEquals(hs.getAddress(), as);
	}

	@Test
	public void GetterTestRooms() {
		Address as = new Address("a", 1, "b", "c");
		House hs = new RentalHouse(as, 1, 2, false, null);
		assertEquals(hs.getnRooms(), 1);
	}

	@Test
	public void GetterTestPrice() {
		Address as = new Address("a", 1, "b", "c");
		House hs = new RentalHouse(as, 1, 2, false, null);
		assertEquals(hs.getSalePrice(), 2);
	}

	@Test
	public void CostAtMostTest() {
		Address as = new Address("a", 1, "b", "c");
		House hs = new RentalHouse(as, 1, 2, false, null);
		assertTrue(hs.costsAtMost(2));
	}

	@Test
	public void CostAtMostTestFalse() {
		Address as = new Address("a", 1, "b", "c");
		House hs = new RentalHouse(as, 1, 2, false, null);
		assertFalse(hs.costsAtMost(1));
	}

	@Test
	public void GetterTestHeatingSystem() {
		Address as = new Address("a", 1, "b", "c");
		Boiler b = new Boiler();
		House hs = new RentalHouse(as, 1, 1, true, b);
		assertEquals(b, hs.getHeatingSystem());
	}

	@Test
	public void GetterTestIsAvailable() {
		Address as = new Address("a", 1, "b", "c");
		Boiler b = new Boiler();
		House hs = new RentalHouse(as, 1, 1, true, b);
		assertEquals(true, hs.isAvailable());
	}

	@Test
	public void ReaderTest() {
		String test = "1\nFOR SALE:\nRembrandtlaan 5\n2951PJ Alblasserdam\n3 rooms\nprice 4000\ncentralheating";
		Scanner sc = new Scanner(new ByteArrayInputStream(test.getBytes(StandardCharsets.UTF_8)));

		House a = House.read(sc);
		Address as = new Address("Rembrandtlaan", 5, "2951PJ", "Alblasserdam");
		House hs = new OwnerOccupiedHouse(as, 3, 4000, true, new CentralHeating());

		assertEquals(a, hs);

	}

	@Test
	public void ReaderTestError() {
		InputStream input = new ByteArrayInputStream("a 1 b c 1 ".getBytes(StandardCharsets.UTF_8));
		Scanner sc = new Scanner(input);

		expectedException.expect(Exception.class);
		House.read(sc);

	}

	@Test
	public void Equals() {
		Address as = new Address("a", 1, "b", "c");
		House hs = new RentalHouse(as, 1, 2, true, new Boiler());

		Address as2 = new Address("a", 1, "b", "c");
		House hs2 = new RentalHouse(as2, 1, 2, true, new Boiler());

		assertEquals(hs, hs2);
	}

	@Test
	public void EqualsOnlyAddressSameShouldBeEqual() {
		Address as = new Address("a", 1, "b", "c");
		House hs = new RentalHouse(as, 1, 2, true, new CentralHeating());

		Address as2 = new Address("a", 1, "b", "c");
		House hs2 = new RentalHouse(as2, 2, 3, false, new Boiler());

		assertEquals(hs, hs2);
	}

	@Test
	public void NotEqualsAddress() {
		Address as = new Address("a", 1, "b", "c");
		House hs = new RentalHouse(as, 1, 2, true, new Boiler());

		Address as2 = new Address("b", 2, "c", "d");
		House hs2 = new RentalHouse(as2, 1, 2, true, new Boiler());

		assertNotEquals(hs, hs2);
	}

	@Test
	public void EffTestAb() {
		Address as = new Address("a", 1, "b", "c");
		Boiler b = new Boiler();
		House hs = new RentalHouse(as, 1, 1, true, b);
		assertEquals('A', hs.getEfficiency(hs.getnRooms(), b));
	}

	@Test
	public void EffTestBb() {
		Address as = new Address("a", 1, "b", "c");
		Boiler b = new Boiler();
		House hs = new RentalHouse(as, 2, 1, true, b);
		assertEquals('B', hs.getEfficiency(hs.getnRooms(), b));
	}

	@Test
	public void EffTestCb() {
		Address as = new Address("a", 1, "b", "c");
		Boiler b = new Boiler();
		House hs = new RentalHouse(as, 3, 1, true, b);
		assertEquals('C', hs.getEfficiency(hs.getnRooms(), b));
	}

	@Test
	public void EffTestDb() {
		Address as = new Address("a", 1, "b", "c");
		Boiler b = new Boiler();
		House hs = new RentalHouse(as, 4, 1, true, b);
		assertEquals('D', hs.getEfficiency(hs.getnRooms(), b));
	}

	@Test
	public void EffTestEb() {
		Address as = new Address("a", 1, "b", "c");
		Boiler b = new Boiler();
		House hs = new RentalHouse(as, 5, 1, true, b);
		assertEquals('E', hs.getEfficiency(hs.getnRooms(), b));
	}
	
	@Test
	public void EffTestAc() {
		Address as = new Address("a", 1, "b", "c");
		CentralHeating c = new CentralHeating();
		House hs = new RentalHouse(as, 2, 1, true, c);
		assertEquals('A', hs.getEfficiency(hs.getnRooms(), c));
	}
	
	@Test
	public void EffTestBc() {
		Address as = new Address("a", 1, "b", "c");
		CentralHeating c = new CentralHeating();
		House hs = new RentalHouse(as, 4, 1, true, c);
		assertEquals('B', hs.getEfficiency(hs.getnRooms(), c));
	}
	
	@Test
	public void EffTestCc() {
		Address as = new Address("a", 1, "b", "c");
		CentralHeating c = new CentralHeating();
		House hs = new RentalHouse(as, 6, 1, true, c);
		assertEquals('C', hs.getEfficiency(hs.getnRooms(), c));
	}
	
	@Test
	public void EffTestDc() {
		Address as = new Address("a", 1, "b", "c");
		CentralHeating c = new CentralHeating();
		House hs = new RentalHouse(as, 8, 1, true, c);
		assertEquals('D', hs.getEfficiency(hs.getnRooms(), c));
	}
	
	@Test
	public void EffTestEc() {
		Address as = new Address("a", 1, "b", "c");
		CentralHeating c = new CentralHeating();
		House hs = new RentalHouse(as, 10, 1, true, c);
		assertEquals('E', hs.getEfficiency(hs.getnRooms(), c));
	}
	
	@Test
	public void EffTestError() {
		Address as = new Address("a", 1, "b", "c");
		CentralHeating c = new CentralHeating();
		House hs = new RentalHouse(as, 11, 1, true, c);
		
		expectedException.expect(Exception.class);
		hs.getEfficiency(11, c);
		
	}

	

}
