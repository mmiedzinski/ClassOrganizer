package eu.anmore.classorganizer;

import java.lang.reflect.Modifier;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;

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
		}
		return 0;
	}

	private int resolveMethod(BodyDeclaration bodyDeclaration) {
		if (isPublic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PUBLIC_METHOD;
		} else if (isProtected(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PROTECTED_METHOD;
		} else if (isPrivate(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PRIVATE_METHOD;
		} else {
			return ClassOrganizerDescriptor.DEFAULT_METHOD;
		}
	}

	private int resolveField(BodyDeclaration bodyDeclaration) {
		if (isPublic(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PUBLIC_FIELD;
		} else if (isProtected(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PROTECTED_FIELD;
		} else if (isPrivate(bodyDeclaration)) {
			return ClassOrganizerDescriptor.PRIVATE_FIELD;
		} else {
			return ClassOrganizerDescriptor.DEFAULT_FIELD;
		}
	}

	private boolean isMethodDeclaration(BodyDeclaration bodyDeclaration) {
		return bodyDeclaration.getNodeType() == ASTNode.METHOD_DECLARATION;
	}

	private boolean isFieldDeclaration(BodyDeclaration bodyDeclaration) {
		return bodyDeclaration.getNodeType() == ASTNode.FIELD_DECLARATION;
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
