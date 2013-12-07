package eu.anmore.classorganizer.utils;

import java.lang.reflect.Modifier;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;

/**
 * Implementation of {@link ModifierFacade}.
 * 
 * @author mmiedzinski
 */
public class ModifierFacadeImpl implements ModifierFacade {

	@Override
	public boolean isStatic(final BodyDeclaration bodyDeclaration) {
		return Modifier.isStatic(bodyDeclaration.getModifiers());
	}

	@Override
	public boolean isFieldDeclaration(final BodyDeclaration bodyDeclaration) {
		return bodyDeclaration.getNodeType() == ASTNode.FIELD_DECLARATION;
	}

	@Override
	public boolean isInitializerDeclaration(final BodyDeclaration bodyDeclaration) {
		return bodyDeclaration.getNodeType() == ASTNode.INITIALIZER;
	}

	@Override
	public boolean isTypeDeclaration(final BodyDeclaration bodyDeclaration) {
		return bodyDeclaration.getNodeType() == ASTNode.TYPE_DECLARATION
				|| bodyDeclaration.getNodeType() == ASTNode.ENUM_DECLARATION;
	}

	@Override
	public boolean isPrivate(final BodyDeclaration bodyDeclaration) {
		return Modifier.isPrivate(bodyDeclaration.getModifiers());
	}

	@Override
	public boolean isPublic(final BodyDeclaration bodyDeclaration) {
		return Modifier.isPublic(bodyDeclaration.getModifiers());
	}

	@Override
	public boolean isProtected(final BodyDeclaration bodyDeclaration) {
		return Modifier.isProtected(bodyDeclaration.getModifiers());
	}
}
