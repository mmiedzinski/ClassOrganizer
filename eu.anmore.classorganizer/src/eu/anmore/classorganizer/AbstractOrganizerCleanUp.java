package eu.anmore.classorganizer;

import java.util.ArrayList;
import java.util.List;

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

import eu.anmore.classorganizer.utils.CompilationUnitSorterFacade;

/**
 * Class organizer abstract class.
 * 
 * @author mmiedzinski
 */
public abstract class AbstractOrganizerCleanUp implements ICleanUp {

	public AbstractOrganizerCleanUp(final String cleanUpId, final String ignoreCompilationErrorKey) {
		this.cleanUpId = cleanUpId;
		this.ignoreCompilationErrorKey = ignoreCompilationErrorKey;
	}

	@Override
	public RefactoringStatus checkPreConditions(final IJavaProject project, final ICompilationUnit[] compilationUnits,
			final IProgressMonitor monitor) throws CoreException {
		if (isCleanUpEnabled()) {
			status = new RefactoringStatus();
		}
		return new RefactoringStatus();
	}

	@Override
	public RefactoringStatus checkPostConditions(final IProgressMonitor monitor) throws CoreException {
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
	public ICleanUpFix createFix(final CleanUpContext context) throws CoreException {
		final CompilationUnit compilationUnit = context.getAST();
		if (compilationUnit == null || !isCleanUpEnabled()
				|| (!ignoreCompilationErrors() && hasCompilationError(compilationUnit))) {
			return null;
		}
		return ClassOrderFix.createCleanUp(compilationUnit, getCompilationUnitSorterFacade());
	}

	@Override
	public CleanUpRequirements getRequirements() {
		final boolean changedRegionsRequired = false;
		final boolean isUpdate = isCleanUpEnabled();
		return new CleanUpRequirements(isUpdate, isUpdate, changedRegionsRequired, null);
	}

	@Override
	public String[] getStepDescriptions() {
		final List<String> result = new ArrayList<String>();
		if (isCleanUpEnabled()) {
			result.add("Organize class order using ClassOrganizer");
		}
		if (ignoreCompilationErrors()) {
			result.add("Organize class with compilation errors");
		}

		return result.toArray(new String[result.size()]);
	}

	@Override
	public void setOptions(final CleanUpOptions options) {
		this.options = options;
	}

	protected CompilationUnitSorterFacade getCompilationUnitSorterFacade() {
		return new CompilationUnitSorterFacade();
	}

	protected boolean isCleanUpEnabled() {
		return options.isEnabled(cleanUpId);
	}

	protected boolean ignoreCompilationErrors() {
		return options.isEnabled(ignoreCompilationErrorKey);
	}

	private boolean hasCompilationError(final CompilationUnit compilationUnit) throws CoreException {
		for (final IProblem problem : compilationUnit.getProblems()) {
			if (problem.isError()) {
				return true;
			}
		}
		return false;
	}

	private final String ignoreCompilationErrorKey;

	private final String cleanUpId;

	private CleanUpOptions options;

	private RefactoringStatus status;
}
