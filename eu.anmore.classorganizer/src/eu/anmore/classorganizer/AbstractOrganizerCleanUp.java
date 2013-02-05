package eu.anmore.classorganizer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.ui.cleanup.CleanUpContext;
import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.eclipse.jdt.ui.cleanup.CleanUpRequirements;
import org.eclipse.jdt.ui.cleanup.ICleanUp;
import org.eclipse.jdt.ui.cleanup.ICleanUpFix;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;

/**
 * Class organizer abstract class.
 * 
 * @author mmiedzinski
 */
public abstract class AbstractOrganizerCleanUp implements ICleanUp {

	public AbstractOrganizerCleanUp(String cleanUpId) {
		this.cleanUpId = cleanUpId;
	}

	@Override
	public RefactoringStatus checkPreConditions(IJavaProject project, ICompilationUnit[] compilationUnits,
			IProgressMonitor monitor) throws CoreException {
		if (isCleanUpEnabled()) {
			status = new RefactoringStatus();
		}
		return new RefactoringStatus();
	}

	@Override
	public RefactoringStatus checkPostConditions(IProgressMonitor monitor) throws CoreException {
		try {
			if (status == null || status.isOK()) {
				return new RefactoringStatus();
			} else {
				return status;
			}
		} finally {
			status = null;
		}
	}

	@Override
	public ICleanUpFix createFix(CleanUpContext context) throws CoreException {
		CompilationUnit compilationUnit = context.getAST();
		if (compilationUnit == null || !isCleanUpEnabled() || hasCompilationError(compilationUnit)) {
			return null;
		}
		return ClassOrderFix.createCleanUp(compilationUnit, getCompilationUnitSorterFacade());
	}

	@Override
	public CleanUpRequirements getRequirements() {
		boolean changedRegionsRequired = false;
		boolean isUpdate = isCleanUpEnabled();
		return new CleanUpRequirements(isUpdate, isUpdate, changedRegionsRequired, null);
	}

	@Override
	public String[] getStepDescriptions() {
		if (isCleanUpEnabled()) {
			return new String[] { "Organize class order using ClassOrganizer" };
		}

		return null;
	}

	@Override
	public void setOptions(CleanUpOptions options) {
		this.options = options;
	}

	protected CompilationUnitSorterFacade getCompilationUnitSorterFacade() {
		return new CompilationUnitSorterFacade();
	}

	protected boolean isCleanUpEnabled() {
		return options.isEnabled(cleanUpId);
	}

	private boolean hasCompilationError(CompilationUnit compilationUnit) throws CoreException {
		for (IProblem problem : compilationUnit.getProblems()) {
			if (problem.isError()) {
				return true;
			}
		}
		return false;
	}

	private final String cleanUpId;

	private CleanUpOptions options;

	private RefactoringStatus status;
}
