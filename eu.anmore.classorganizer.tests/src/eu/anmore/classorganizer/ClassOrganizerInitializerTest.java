package eu.anmore.classorganizer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.junit.Test;

/**
 * Test class for ClassOrganizerInitializer.
 * 
 * @author mmiedzinski
 * 
 */
public class ClassOrganizerInitializerTest {

	@Test
	public void shouldSetDefaultOptions() {
		// given
		ClassOrganizerInitializer classOrganizerInitializer = new ClassOrganizerInitializer();
		CleanUpOptions optionsMock = mock(CleanUpOptions.class);

		// when
		classOrganizerInitializer.setDefaultOptions(optionsMock);

		// then
		verify(optionsMock).setOption(ClassOrganizerDescriptor.CLEANUP_ID, CleanUpOptions.TRUE);
	}
}
