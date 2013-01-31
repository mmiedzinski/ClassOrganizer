package eu.anmore.classorganizer;

/**
 * Descriptor class for ClassOrganizer.
 * 
 * @author mmiedzinski
 */
public final class ClassOrganizerDescriptor {

	static final int PUBLIC_METHOD = 0;

	static final int PUBLIC_FIELD = 1;

	static final int DEFAULT_METHOD = 2;

	static final int DEFAULT_FIELD = 3;

	static final int PROTECTED_METHOD = 4;

	static final int PROTECTED_FIELD = 5;

	static final int PRIVATE_METHOD = 6;

	static final int PRIVATE_FIELD = 7;

	private ClassOrganizerDescriptor() {
		// initially left blank
	}
}
