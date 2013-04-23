package eu.anmore.classorganizer.configuration;

import eu.anmore.classorganizer.ClassOrganizerCleanUp;

/**
 * Configuration page for cleanUp.
 * 
 * @author mmiedzinski
 */
public class ClassOrganizerCleanUpConfigurationPage extends AbstractOrganizerConfigurationPage {

	public ClassOrganizerCleanUpConfigurationPage() {
		super(ClassOrganizerCleanUp.CLEANUP_ID, ClassOrganizerCleanUp.ENABLED_WHEN_COMPILATION_ERRORS_PROPERTY);
	}
}
