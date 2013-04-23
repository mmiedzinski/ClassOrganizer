package eu.anmore.classorganizer.configuration;

import eu.anmore.classorganizer.ClassOrganizerSaveCleanUp;

/**
 * Configuration page for save action.
 * 
 * @author mmiedzinski
 */
public class ClassOrganizerSaveActionConfigurationPage extends AbstractOrganizerConfigurationPage {

	public ClassOrganizerSaveActionConfigurationPage() {
		super(ClassOrganizerSaveCleanUp.CLEANUP_ID, ClassOrganizerSaveCleanUp.ENABLED_WHEN_COMPILATION_ERRORS_PROPERTY);
	}
}
