package eu.anmore.classorganizer;

import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.eclipse.jdt.ui.cleanup.ICleanUpOptionsInitializer;

/**
 * Class organizer initializer.
 * 
 * @author mmiedzinski
 */
public class ClassOrganizerInitializer implements ICleanUpOptionsInitializer {

	@Override
	public void setDefaultOptions(CleanUpOptions options) {
		options.setOption("cleanup.update_copyrights", CleanUpOptions.TRUE);
	}
}
