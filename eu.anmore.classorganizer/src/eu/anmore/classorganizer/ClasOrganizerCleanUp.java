package eu.anmore.classorganizer;

import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.ui.cleanup.CleanUpContext;
import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.eclipse.jdt.ui.cleanup.CleanUpRequirements;
import org.eclipse.jdt.ui.cleanup.ICleanUp;
import org.eclipse.jdt.ui.cleanup.ICleanUpFix;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;

/**
 * Class organizer main class.
 * 
 * @author mmiedzinski
 */
public class ClasOrganizerCleanUp implements ICleanUp {

	public ClasOrganizerCleanUp() {
	}

	@Override
	public RefactoringStatus checkPreConditions(IJavaProject project, ICompilationUnit[] compilationUnits,
			IProgressMonitor monitor) throws CoreException {
		if (options.isEnabled("cleanup.update_copyrights")) { //$NON-NLS-1$
			fStatus = new RefactoringStatus();
		}
		return new RefactoringStatus();
	}

	@Override
	public RefactoringStatus checkPostConditions(IProgressMonitor monitor) throws CoreException {
		try {
			if (fStatus == null || fStatus.isOK()) {
				return new RefactoringStatus();
			} else {
				return fStatus;
			}
		} finally {
			fStatus = null;
		}
	}

	@Override
	public ICleanUpFix createFix(CleanUpContext context) throws CoreException {
		CompilationUnit compilationUnit = context.getAST();
		if (compilationUnit == null) {
			return null;
		}
		return ClassOrderFix.createCleanUp(compilationUnit);
	}

	@Override
	public CleanUpRequirements getRequirements() {
		boolean changedRegionsRequired = false;
		boolean isUpdateCopyrights = options.isEnabled("cleanup.update_copyrights");
		return new CleanUpRequirements(isUpdateCopyrights, isUpdateCopyrights, changedRegionsRequired,
				new HashMap<String, String>());
	}

	@Override
	public String[] getStepDescriptions() {
		if (options.isEnabled("cleanup.update_copyrights")) {
			return new String[] { "Update Copyrights" };
		}

		return null;
	}

	@Override
	public void setOptions(CleanUpOptions options) {
		this.options = options;
	}

	private CleanUpOptions options;
	private RefactoringStatus fStatus;
}
