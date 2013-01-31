package eu.anmore.classorganizer;

import java.lang.reflect.Modifier;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 * Implementation of {@link ModifierResolver}.
 * 
 * @author mmiedzinski
 */
public class ModifierResolverImpl implements ModifierResolver {

	@Override
	public int resolve(BodyDeclaration bodyDeclaration) {
		if (isMethodDeclaration(bodyDeclaration)) {
			return resolveMethod(bodyDeclaration);
		} else if (isFieldDeclaration(bodyDeclaration)) {
			return resolveField(bodyDeclaration);
		} else if (isInitializerDeclaration(bodyDeclaration)) {
			return resolveInitializer(bodyDeclaration);
		} else if (isTypeDeclaration(bodyDeclaration)) {
			return resolveType(bodyDeclaration);
		} else if (isConstructorDeclaration(bodyDeclaration)) {
			return resolveConstructor(bodyDeclaration);
		}
		return 0;
	}

	private int resolveMethod(BodyDeclaration bodyDeclaration) {
		if (isPublic(bodyDeclaration)) {
			return resolvePublicMethod(bodyDeclaration);
		} else if (isProtected(bodyDeclaration)) {
			return resolveProtectedMethod(bodyDeclaration);
		} else if (isPrivate(bodyDeclaration)) {
			return resolvePrivateMethod(bodyDeclaration);
		} else {
			return resolveDefaultMethod(bodyDeclaration);
		}
	}

	private int resolveDefaultMethod(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.DEFAULT_STATIC_METHOD;
		}
		return ClassOrganizerDescriptor.DEFAULT_METHOD;
	}

	private int resolvePrivateMethod(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PRIVATE_STATIC_METHOD;
		}
		return ClassOrganizerDescriptor.PRIVATE_METHOD;
	}

	private int resolveProtectedMethod(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PROTECTED_STATIC_METHOD;
		}
		return ClassOrganizerDescriptor.PROTECTED_METHOD;
	}

	private int resolvePublicMethod(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PUBLIC_STATIC_METHOD;
		}
		return ClassOrganizerDescriptor.PUBLIC_METHOD;
	}

	private int resolveField(BodyDeclaration bodyDeclaration) {
		if (isPublic(bodyDeclaration)) {
			return resolvePublicField(bodyDeclaration);
		} else if (isProtected(bodyDeclaration)) {
			return resolveProtectedField(bodyDeclaration);
		} else if (isPrivate(bodyDeclaration)) {
			return resolvePrivateField(bodyDeclaration);
		} else {
			return resolveDefaultField(bodyDeclaration);
		}
	}

	private int resolveDefaultField(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.DEFAULT_STATIC_FIELD;
		}
		return ClassOrganizerDescriptor.DEFAULT_FIELD;
	}

	private int resolvePrivateField(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PRIVATE_STATIC_FIELD;
		}
		return ClassOrganizerDescriptor.PRIVATE_FIELD;
	}

	private int resolveProtectedField(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PROTECTED_STATIC_FIELD;
		}
		return ClassOrganizerDescriptor.PROTECTED_FIELD;
	}

	private int resolvePublicField(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PUBLIC_STATIC_FIELD;
		}
		return ClassOrganizerDescriptor.PUBLIC_FIELD;
	}

	private int resolveInitializer(BodyDeclaration bodyDeclaration) {
		if (isPublic(bodyDeclaration)) {
			return resolvePublicInitializer(bodyDeclaration);
		} else if (isProtected(bodyDeclaration)) {
			return resolveProtectedInitializer(bodyDeclaration);
		} else if (isPrivate(bodyDeclaration)) {
			return resolvePrivateInitializer(bodyDeclaration);
		} else {
			return resolveDefaultInitializer(bodyDeclaration);
		}
	}

	private int resolveDefaultInitializer(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.DEFAULT_STATIC_INITIALIZER;
		}
		return ClassOrganizerDescriptor.DEFAULT_INITIALIZER;
	}

	private int resolvePrivateInitializer(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PRIVATE_STATIC_INITIALIZER;
		}
		return ClassOrganizerDescriptor.PRIVATE_INITIALIZER;
	}

	private int resolveProtectedInitializer(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PROTECTED_STATIC_INITIALIZER;
		}
		return ClassOrganizerDescriptor.PROTECTED_INITIALIZER;
	}

	private int resolvePublicInitializer(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PUBLIC_STATIC_INITIALIZER;
		}
		return ClassOrganizerDescriptor.PUBLIC_INITIALIZER;
	}

	private int resolveConstructor(BodyDeclaration bodyDeclaration) {
		if (isPublic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PUBLIC_CONSTRUCTOR;
		} else if (isProtected(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PROTECTED_CONSTRUCTOR;
		} else if (isPrivate(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PRIVATE_CONSTRUCTOR;
		} else {
			return ClassOrganizerDescriptor.DEFAULT_CONSTRUCTOR;
		}
	}

	private int resolveType(BodyDeclaration bodyDeclaration) {
		if (isPublic(bodyDeclaration)) {
			return resolvePublicType(bodyDeclaration);
		} else if (isProtected(bodyDeclaration)) {
			return resolveProtectedType(bodyDeclaration);
		} else if (isPrivate(bodyDeclaration)) {
			return resolvePrivateType(bodyDeclaration);
		} else {
			return resolveDefaultType(bodyDeclaration);
		}
	}

	private int resolveDefaultType(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.DEFAULT_STATIC_TYPE;
		}
		return ClassOrganizerDescriptor.DEFAULT_TYPE;
	}

	private int resolvePrivateType(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PRIVATE_STATIC_TYPE;
		}
		return ClassOrganizerDescriptor.PRIVATE_TYPE;
	}

	private int resolveProtectedType(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PROTECTED_STATIC_TYPE;
		}
		return ClassOrganizerDescriptor.PROTECTED_TYPE;
	}

	private int resolvePublicType(BodyDeclaration bodyDeclaration) {
		if (isStatic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PUBLIC_STATIC_TYPE;
		}
		return ClassOrganizerDescriptor.PUBLIC_TYPE;
	}

	private boolean isMethodDeclaration(BodyDeclaration bodyDeclaration) {
		return bodyDeclaration.getNodeType() == ASTNode.METHOD_DECLARATION
				&& !((MethodDeclaration) bodyDeclaration).isConstructor();
	}

	private boolean isConstructorDeclaration(BodyDeclaration bodyDeclaration) {
		return bodyDeclaration.getNodeType() == ASTNode.METHOD_DECLARATION
				&& ((MethodDeclaration) bodyDeclaration).isConstructor();
	}

	private boolean isStatic(BodyDeclaration bodyDeclaration) {
		return Modifier.isStatic(bodyDeclaration.getModifiers());
	}

	private boolean isFieldDeclaration(BodyDeclaration bodyDeclaration) {
		return bodyDeclaration.getNodeType() == ASTNode.FIELD_DECLARATION;
	}

	private boolean isInitializerDeclaration(BodyDeclaration bodyDeclaration) {
		return bodyDeclaration.getNodeType() == ASTNode.INITIALIZER;
	}

	private boolean isTypeDeclaration(BodyDeclaration bodyDeclaration) {
		return bodyDeclaration.getNodeType() == ASTNode.TYPE_DECLARATION;
	}

	private boolean isPrivate(BodyDeclaration bodyDeclaration) {
		return Modifier.isPrivate(bodyDeclaration.getModifiers());
	}

	private boolean isPublic(BodyDeclaration bodyDeclaration) {
		return Modifier.isPublic(bodyDeclaration.getModifiers());
	}

	private boolean isProtected(BodyDeclaration bodyDeclaration) {
		return Modifier.isProtected(bodyDeclaration.getModifiers());
	}
}
