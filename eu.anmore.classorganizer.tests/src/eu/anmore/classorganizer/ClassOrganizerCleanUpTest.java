package eu.anmore.classorganizer;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.junit.Test;

/**
 * Test class for {@link ClassOrganizerCleanUp}
 * 
 * @author mmiedzinski
 */
public class ClassOrganizerCleanUpTest {

	@Test
	public void shouldReturnRefactoringStatusWhenCleanUpEnabledWhileCheckPreConditions() throws CoreException {
		// given
		CleanUpOptions cleanOptionsMock;
		cleanOptionsMock = mock(CleanUpOptions.class);

		ClassOrganizerCleanUp cleanUp = new ClassOrganizerCleanUp();
		cleanUp.setOptions(cleanOptionsMock);

		when(cleanOptionsMock.isEnabled(ClassOrganizerCleanUp.CLEANUP_ID)).thenReturn(true);

		// when
		RefactoringStatus checkPreConditions = cleanUp.checkPreConditions(null, null, null);

		// then
		assertNotNull(checkPreConditions);
	}
}
