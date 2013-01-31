package eu.anmore.classorganizer;

import java.util.Comparator;

import org.eclipse.jdt.core.dom.BodyDeclaration;

/**
 * Comparator class element e.g fields, methods.
 * 
 * @author mmiedzinski
 */
public class ClassElementComparator implements Comparator<BodyDeclaration> {

	public ClassElementComparator(ModifierResolver modifierResolver) {
		this.modifierResolver = modifierResolver;
	}

	@Override
	public int compare(BodyDeclaration bodyDeclaration1, BodyDeclaration bodyDeclaration2) {
		int category1 = modifierResolver.resolve(bodyDeclaration1);
		int category2 = modifierResolver.resolve(bodyDeclaration2);

		return category1 - category2;
	}

	private ModifierResolver modifierResolver;
}
