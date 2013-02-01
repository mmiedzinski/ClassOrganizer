package eu.anmore.classorganizer;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.util.CompilationUnitSorter;
import org.eclipse.ltk.core.refactoring.CategorizedTextEditGroup;
import org.eclipse.text.edits.TextEdit;

/**
 * Facade for {@link CompilationUnitSorter}.
 * 
 * @author mmiedzinski
 */
public class CompilationUnitSorterFacade {

	public TextEdit sort(CompilationUnit compilationUnit, ClassElementComparator classElementComparator, int options,
			CategorizedTextEditGroup group) throws JavaModelException {
		return CompilationUnitSorter.sort(compilationUnit, classElementComparator, options, group, null);
	}
}
