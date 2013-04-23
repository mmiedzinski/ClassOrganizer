package eu.anmore.classorganizer.initializer;

import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.eclipse.jdt.ui.cleanup.ICleanUpOptionsInitializer;

import eu.anmore.classorganizer.ClassOrganizerSaveCleanUp;

/**
 * Class organizer save action initializer.
 * 
 * @author mmiedzinski
 */
public class ClassOrganizerSaveActionInitializer implements ICleanUpOptionsInitializer {

	@Override
	public void setDefaultOptions(final CleanUpOptions options) {
		options.setOption(ClassOrganizerSaveCleanUp.CLEANUP_ID, CleanUpOptions.TRUE);
		options.setOption(ClassOrganizerSaveCleanUp.ENABLED_WHEN_COMPILATION_ERRORS_PROPERTY, CleanUpOptions.FALSE);
	}
}
