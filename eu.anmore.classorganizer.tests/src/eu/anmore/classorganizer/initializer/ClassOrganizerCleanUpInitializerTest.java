package eu.anmore.classorganizer.initializer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.junit.Test;

import eu.anmore.classorganizer.ClassOrganizerCleanUp;

/**
 * Test class for {@link ClassOrganizerCleanUpInitializer}.
 * 
 * @author mmiedzinski
 */
public class ClassOrganizerCleanUpInitializerTest {

	@Test
	public void shouldSetDefaultOptions() {
		// given
		ClassOrganizerCleanUpInitializer classOrganizerInitializer = new ClassOrganizerCleanUpInitializer();
		CleanUpOptions optionsMock = mock(CleanUpOptions.class);

		// when
		classOrganizerInitializer.setDefaultOptions(optionsMock);

		// then
		verify(optionsMock).setOption(ClassOrganizerCleanUp.CLEANUP_ID, CleanUpOptions.TRUE);
	}
}
