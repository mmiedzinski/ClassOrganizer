package eu.anmore.classorganizer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.junit.Test;

/**
 * Test class for {@link ClassOrganizerSaveActionInitializer}.
 * 
 * @author mmiedzinski
 * 
 */
public class ClassOrganizerSaveActionInitializerTest {

	@Test
	public void shouldSetDefaultOptions() {
		// given
		ClassOrganizerSaveActionInitializer classOrganizerInitializer = new ClassOrganizerSaveActionInitializer();
		CleanUpOptions optionsMock = mock(CleanUpOptions.class);

		// when
		classOrganizerInitializer.setDefaultOptions(optionsMock);

		// then
		verify(optionsMock).setOption(ClassOrganizerSaveCleanUp.CLEANUP_ID, CleanUpOptions.TRUE);
	}
}
