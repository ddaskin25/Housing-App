import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AddressTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void GetStreetTest() {
		Address a = new Address("a", 1, "b", "c");

		assertEquals(a.getStreet(), "a");

	}

	@Test
	public void GetNumberTest() {
		Address a = new Address("a", 1, "b", "c");

		assertEquals(a.getNumber(), 1);

	}

	@Test
	public void GetZipCodeTest() {
		Address a = new Address("a", 1, "b", "c");

		assertEquals(a.getZipCode(), "b");

	}

	@Test
	public void GetCityTest() {
		Address a = new Address("a", 1, "b", "c");

		assertEquals(a.getCity(), "c");

	}

	@Test
	public void ReaderTest() {
		InputStream input = new ByteArrayInputStream("a 1 b c".getBytes(StandardCharsets.UTF_8));
		Scanner sc = new Scanner(input);

		Address a = Address.read(sc);
		Address b = new Address("a", 1, "b", "c");
		assertEquals(a, b);

	}

	@Test
	public void ReaderTestError() {
		InputStream input = new ByteArrayInputStream("1 1 b ".getBytes(StandardCharsets.UTF_8));
		Scanner sc = new Scanner(input);

		expectedException.expect(Exception.class);
		Address.read(sc);

	}

	@Test
	public void EqualsSame() {
		Address a = new Address("a", 1, "b", "c");
		Address b = new Address("a", 1, "b", "c");

		assertEquals(a, b);

	}

	@Test
	public void EqualsSame2() {
		Address a = new Address("a", 1, "b", "c");
		Address b = new Address("jd", 1, "b", "cwhj");

		assertEquals(a, b);

	}

	@Test
	public void NotEqualsAll() {
		Address a = new Address("a", 1, "b", "c");
		Address b = new Address("we", 2, "n", "cwhj");

		assertNotEquals(a, b);

	}

	@Test
	public void NotEquals2Fields() {
		Address a = new Address("a", 1, "b", "c");
		Address b = new Address("a", 2, "n", "c");

		assertNotEquals(a, b);

	}

}
