package eu.anmore.classorganizer;

/**
 * CleanUp on save action.
 * 
 * @author mmiedzinski
 */
public class ClassOrganizerSaveCleanUp extends AbstractOrganizerCleanUp {

	/**
	 * The cleanup id.
	 */
	public static final String CLEANUP_ID = "classOrganizer.save.cleanUp";

	/**
	 * Determines that organize class when compilation errors occurs.
	 */
	public static final String ENABLED_WHEN_COMPILATION_ERRORS_PROPERTY = "ignoreCompilationErrors.save";

	public ClassOrganizerSaveCleanUp() {
		super(CLEANUP_ID, ENABLED_WHEN_COMPILATION_ERRORS_PROPERTY);
	}
}
