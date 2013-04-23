package eu.anmore.classorganizer;

/**
 * Class organizer cleanUp.
 * 
 * @author mmiedzinski
 */
public class ClassOrganizerCleanUp extends AbstractOrganizerCleanUp {

	/**
	 * Cleanup id.
	 */
	public static final String CLEANUP_ID = "classOrganizer.cleanUp";

	/**
	 * Determines that organize class when compilation errors occurs.
	 */
	public static final String ENABLED_WHEN_COMPILATION_ERRORS_PROPERTY = "ignoreCompilationErrors.cleanUp";

	public ClassOrganizerCleanUp() {
		super(CLEANUP_ID, ENABLED_WHEN_COMPILATION_ERRORS_PROPERTY);
	}
}
