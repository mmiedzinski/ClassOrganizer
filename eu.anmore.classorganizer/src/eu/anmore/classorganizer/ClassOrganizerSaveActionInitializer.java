package eu.anmore.classorganizer;

import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.eclipse.jdt.ui.cleanup.ICleanUpOptionsInitializer;

/**
 * Class organizer save action initializer.
 * 
 * @author mmiedzinski
 */
public class ClassOrganizerSaveActionInitializer implements ICleanUpOptionsInitializer {

	@Override
	public void setDefaultOptions(CleanUpOptions options) {
		options.setOption(ClassOrganizerSaveCleanUp.CLEANUP_ID, CleanUpOptions.TRUE);
	}
}
