package eu.anmore.classorganizer.utils;

import org.eclipse.jdt.core.dom.BodyDeclaration;

/**
 * Facade for {@link java.lang.reflectModifier}.
 * 
 * @author mmiedzinski
 */
public interface ModifierFacade {

	/**
	 * Determines that declaration contains static modifier.
	 * 
	 * @param bodyDeclaration
	 * @return
	 */
	boolean isStatic(BodyDeclaration bodyDeclaration);

	/**
	 * Determines that declaration is field.
	 * 
	 * @param bodyDeclaration
	 * @return
	 */
	boolean isFieldDeclaration(BodyDeclaration bodyDeclaration);

	/**
	 * Determines that declaration is initializer.
	 * 
	 * @param bodyDeclaration
	 * @return
	 */
	boolean isInitializerDeclaration(BodyDeclaration bodyDeclaration);

	/**
	 * Determines that declaration is type.
	 * 
	 * @param bodyDeclaration
	 * @return
	 */
	boolean isTypeDeclaration(BodyDeclaration bodyDeclaration);

	/**
	 * Determines that declaration contains private modifier.
	 * 
	 * @param bodyDeclaration
	 * @return
	 */
	boolean isPrivate(BodyDeclaration bodyDeclaration);

	/**
	 * Determines that declaration contains public modifier.
	 * 
	 * @param bodyDeclaration
	 * @return
	 */
	boolean isPublic(BodyDeclaration bodyDeclaration);

	/**
	 * Determines that declaration contains protected modifier.
	 * 
	 * @param bodyDeclaration
	 * @return
	 */
	boolean isProtected(BodyDeclaration bodyDeclaration);
}
