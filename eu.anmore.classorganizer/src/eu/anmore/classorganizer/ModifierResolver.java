package eu.anmore.classorganizer;

import org.eclipse.jdt.core.dom.BodyDeclaration;

/**
 * Modifier resolver.
 * 
 * @author mmiedzinski
 */
public interface ModifierResolver {

	/**
	 * Resolve type of node.
	 * 
	 * @param bodyDeclaration
	 *            the node to resolve
	 * @return
	 */
	int resolve(BodyDeclaration bodyDeclaration);
}
