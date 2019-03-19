package lab1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WalletTest {

	@Test
	public void testCanGive() {
		assertEquals(true,new Wallet().canGive(50));
		assertEquals(false,new Wallet().canGive(100));
	}

}
