package eu.anmore.classorganizer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.refactoring.CompilationUnitChange;
import org.eclipse.jdt.core.util.CompilationUnitSorter;
import org.eclipse.jdt.ui.cleanup.ICleanUpFix;
import org.eclipse.ltk.core.refactoring.CategorizedTextEditGroup;
import org.eclipse.ltk.core.refactoring.GroupCategory;
import org.eclipse.ltk.core.refactoring.GroupCategorySet;
import org.eclipse.text.edits.TextEdit;

/**
 * Determines order in class.
 * 
 * @author mmiedzinski
 * 
 */
public class ClassOrderFix implements ICleanUpFix {

	public static ICleanUpFix createCleanUp(CompilationUnit compilationUnit) throws CoreException {
		CategorizedTextEditGroup group = new CategorizedTextEditGroup(SORT_DESCRIPTION, new GroupCategorySet(
				new GroupCategory(SORT_DESCRIPTION, SORT_DESCRIPTION, SORT_DESCRIPTION)));

		TextEdit textEdit = CompilationUnitSorter.sort(compilationUnit, new ClassElementComparator(), 0, group, null);

		if (textEdit == null) {
			return null;
		}

		return new ClassOrderFix(textEdit, (ICompilationUnit) compilationUnit.getJavaElement());
	}

	public ClassOrderFix(TextEdit textEdit, ICompilationUnit compilationUnit) {
		this.textEdit = textEdit;
		this.compilationUnit = compilationUnit;
	}

	@Override
	public CompilationUnitChange createChange(IProgressMonitor progressMonitor) throws CoreException {
		CompilationUnitChange change = new CompilationUnitChange(SORT_DESCRIPTION, compilationUnit);
		change.setEdit(textEdit);
		change.addTextEditGroup(new CategorizedTextEditGroup(SORT_DESCRIPTION, new GroupCategorySet(new GroupCategory(
				SORT_DESCRIPTION, SORT_DESCRIPTION, SORT_DESCRIPTION))));

		return change;
	}

	private static final String SORT_DESCRIPTION = "Miedziu order";

	private final TextEdit textEdit;

	private final ICompilationUnit compilationUnit;
}
