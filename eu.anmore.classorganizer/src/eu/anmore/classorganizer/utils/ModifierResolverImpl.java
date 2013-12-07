package eu.anmore.classorganizer.utils;

import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import eu.anmore.classorganizer.ClassOrganizerDescriptor;

/**
 * Implementation of {@link ModifierResolver}.
 * 
 * @author mmiedzinski
 */
public class ModifierResolverImpl implements ModifierResolver {

	public ModifierResolverImpl(final ModifierFacade modifierFacade) {
		this.modifierFacade = modifierFacade;
	}

	@Override
	public int resolve(final BodyDeclaration bodyDeclaration) {
		if (isMethodDeclaration(bodyDeclaration)) {
			return resolveMethod(bodyDeclaration);
		} else if (modifierFacade.isFieldDeclaration(bodyDeclaration)) {
			return resolveField(bodyDeclaration);
		} else if (modifierFacade.isInitializerDeclaration(bodyDeclaration)) {
			return resolveInitializer(bodyDeclaration);
		} else if (modifierFacade.isTypeDeclaration(bodyDeclaration)) {
			return resolveType(bodyDeclaration);
		} else if (isConstructorDeclaration(bodyDeclaration)) {
			return resolveConstructor(bodyDeclaration);
		}
		return 0;
	}

	private int resolveMethod(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isPublic(bodyDeclaration)) {
			return resolvePublicMethod(bodyDeclaration);
		} else if (modifierFacade.isProtected(bodyDeclaration)) {
			return resolveProtectedMethod(bodyDeclaration);
		} else if (modifierFacade.isPrivate(bodyDeclaration)) {
			return resolvePrivateMethod(bodyDeclaration);
		} else {
			return resolveDefaultMethod(bodyDeclaration);
		}
	}

	private int resolveDefaultMethod(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.DEFAULT_STATIC_METHOD;
		}
		return ClassOrganizerDescriptor.DEFAULT_METHOD;
	}

	private int resolvePrivateMethod(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PRIVATE_STATIC_METHOD;
		}
		return ClassOrganizerDescriptor.PRIVATE_METHOD;
	}

	private int resolveProtectedMethod(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PROTECTED_STATIC_METHOD;
		}
		return ClassOrganizerDescriptor.PROTECTED_METHOD;
	}

	private int resolvePublicMethod(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PUBLIC_STATIC_METHOD;
		}
		return ClassOrganizerDescriptor.PUBLIC_METHOD;
	}

	private int resolveField(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isPublic(bodyDeclaration)) {
			return resolvePublicField(bodyDeclaration);
		} else if (modifierFacade.isProtected(bodyDeclaration)) {
			return resolveProtectedField(bodyDeclaration);
		} else if (modifierFacade.isPrivate(bodyDeclaration)) {
			return resolvePrivateField(bodyDeclaration);
		} else {
			return resolveDefaultField(bodyDeclaration);
		}
	}

	private int resolveDefaultField(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.DEFAULT_STATIC_FIELD;
		}
		return ClassOrganizerDescriptor.DEFAULT_FIELD;
	}

	private int resolvePrivateField(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PRIVATE_STATIC_FIELD;
		}
		return ClassOrganizerDescriptor.PRIVATE_FIELD;
	}

	private int resolveProtectedField(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PROTECTED_STATIC_FIELD;
		}
		return ClassOrganizerDescriptor.PROTECTED_FIELD;
	}

	private int resolvePublicField(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PUBLIC_STATIC_FIELD;
		}
		return ClassOrganizerDescriptor.PUBLIC_FIELD;
	}

	private int resolveInitializer(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isPublic(bodyDeclaration)) {
			return resolvePublicInitializer(bodyDeclaration);
		} else if (modifierFacade.isProtected(bodyDeclaration)) {
			return resolveProtectedInitializer(bodyDeclaration);
		} else if (modifierFacade.isPrivate(bodyDeclaration)) {
			return resolvePrivateInitializer(bodyDeclaration);
		} else {
			return resolveDefaultInitializer(bodyDeclaration);
		}
	}

	private int resolveDefaultInitializer(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.DEFAULT_STATIC_INITIALIZER;
		}
		return ClassOrganizerDescriptor.DEFAULT_INITIALIZER;
	}

	private int resolvePrivateInitializer(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PRIVATE_STATIC_INITIALIZER;
		}
		return ClassOrganizerDescriptor.PRIVATE_INITIALIZER;
	}

	private int resolveProtectedInitializer(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PROTECTED_STATIC_INITIALIZER;
		}
		return ClassOrganizerDescriptor.PROTECTED_INITIALIZER;
	}

	private int resolvePublicInitializer(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PUBLIC_STATIC_INITIALIZER;
		}
		return ClassOrganizerDescriptor.PUBLIC_INITIALIZER;
	}

	private int resolveConstructor(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isPublic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PUBLIC_CONSTRUCTOR;
		} else if (modifierFacade.isProtected(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PROTECTED_CONSTRUCTOR;
		} else if (modifierFacade.isPrivate(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PRIVATE_CONSTRUCTOR;
		} else {
			return ClassOrganizerDescriptor.DEFAULT_CONSTRUCTOR;
		}
	}

	private int resolveType(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isPublic(bodyDeclaration)) {
			return resolvePublicType(bodyDeclaration);
		} else if (modifierFacade.isProtected(bodyDeclaration)) {
			return resolveProtectedType(bodyDeclaration);
		} else if (modifierFacade.isPrivate(bodyDeclaration)) {
			return resolvePrivateType(bodyDeclaration);
		} else {
			return resolveDefaultType(bodyDeclaration);
		}
	}

	private int resolveDefaultType(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.DEFAULT_STATIC_TYPE;
		}
		return ClassOrganizerDescriptor.DEFAULT_TYPE;
	}

	private int resolvePrivateType(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PRIVATE_STATIC_TYPE;
		}
		return ClassOrganizerDescriptor.PRIVATE_TYPE;
	}

	private int resolveProtectedType(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PROTECTED_STATIC_TYPE;
		}
		return ClassOrganizerDescriptor.PROTECTED_TYPE;
	}

	private int resolvePublicType(final BodyDeclaration bodyDeclaration) {
		if (modifierFacade.isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PUBLIC_STATIC_TYPE;
		}
		return ClassOrganizerDescriptor.PUBLIC_TYPE;
	}

	private boolean isMethodDeclaration(final BodyDeclaration bodyDeclaration) {
		return bodyDeclaration instanceof MethodDeclaration && !((MethodDeclaration) bodyDeclaration).isConstructor();
	}

	private boolean isConstructorDeclaration(final BodyDeclaration bodyDeclaration) {
		return bodyDeclaration instanceof MethodDeclaration;
	}

	private final ModifierFacade modifierFacade;
}
