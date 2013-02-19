package eu.anmore.classorganizer.initializer;

import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.eclipse.jdt.ui.cleanup.ICleanUpOptionsInitializer;

import eu.anmore.classorganizer.ClassOrganizerCleanUp;

/**
 * CleanUp initializer.
 * 
 * @author mmiedzinski
 */
public class ClassOrganizerCleanUpInitializer implements ICleanUpOptionsInitializer {

	@Override
	public void setDefaultOptions(CleanUpOptions options) {
		options.setOption(ClassOrganizerCleanUp.CLEANUP_ID, CleanUpOptions.TRUE);
	}
}
