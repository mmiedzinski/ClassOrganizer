package eu.anmore.classorganizer;

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
	public boolean isStatic(BodyDeclaration bodyDeclaration) {
		return Modifier.isStatic(bodyDeclaration.getModifiers());
	}

	@Override
	public boolean isFieldDeclaration(BodyDeclaration bodyDeclaration) {
		return bodyDeclaration.getNodeType() == ASTNode.FIELD_DECLARATION;
	}

	@Override
	public boolean isInitializerDeclaration(BodyDeclaration bodyDeclaration) {
		return bodyDeclaration.getNodeType() == ASTNode.INITIALIZER;
	}

	@Override
	public boolean isTypeDeclaration(BodyDeclaration bodyDeclaration) {
		return bodyDeclaration.getNodeType() == ASTNode.TYPE_DECLARATION;
	}

	@Override
	public boolean isPrivate(BodyDeclaration bodyDeclaration) {
		return Modifier.isPrivate(bodyDeclaration.getModifiers());
	}

	@Override
	public boolean isPublic(BodyDeclaration bodyDeclaration) {
		return Modifier.isPublic(bodyDeclaration.getModifiers());
	}

	@Override
	public boolean isProtected(BodyDeclaration bodyDeclaration) {
		return Modifier.isProtected(bodyDeclaration.getModifiers());
	}
}
