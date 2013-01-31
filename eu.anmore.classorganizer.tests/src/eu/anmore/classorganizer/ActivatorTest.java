package eu.anmore.classorganizer;

import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.BundleException;

/**
 * Test class for activator.
 * 
 * @author mmiedzinski
 * 
 */
public class ActivatorTest {

	@Before
	public void setUp() {
		activator = new Activator();
	}

	@Test(expected = BundleException.class)
	public void shouldThrowBundleExceptionWhenStartFail() throws BundleException {
		// given

		// when
		activator.start(null);

		// then
		// exception should be thrown
	}

	private Activator activator;
}
