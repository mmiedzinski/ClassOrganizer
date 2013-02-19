package eu.anmore.classorganizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.ui.cleanup.CleanUpContext;
import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.eclipse.jdt.ui.cleanup.CleanUpRequirements;
import org.eclipse.jdt.ui.cleanup.ICleanUpFix;
import org.eclipse.ltk.core.refactoring.CategorizedTextEditGroup;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.text.edits.TextEdit;
import org.junit.Before;
import org.junit.Test;

import eu.anmore.classorganizer.utils.ClassElementComparator;
import eu.anmore.classorganizer.utils.CompilationUnitSorterFacade;

/**
 * Test class for {@link ClassOrganizerSaveCleanUp}.
 * 
 * @author mmiedzinski
 */
public class ClassOrganizerSaveCleanUpTest {

	@Before
	public void setUp() {
		cleanOptionsMock = mock(CleanUpOptions.class);
		cleanUp = new ClassOrganizerSaveCleanUp();
		cleanUp.setOptions(cleanOptionsMock);
	}

	@Test
	public void shouldReturnRefactoringStatusWhenCleanUpEnabledWhileCheckPreConditions() throws CoreException {
		// given
		when(cleanOptionsMock.isEnabled(ClassOrganizerSaveCleanUp.CLEANUP_ID)).thenReturn(true);

		// when
		RefactoringStatus checkPreConditions = cleanUp.checkPreConditions(null, null, null);

		// then
		assertNotNull(checkPreConditions);
	}

	@Test
	public void shouldReturnRefactoringStatusWhenCleanUpDisableddWhileCheckPreConditions() throws CoreException {
		// given
		when(cleanOptionsMock.isEnabled(ClassOrganizerSaveCleanUp.CLEANUP_ID)).thenReturn(false);

		// when
		RefactoringStatus checkPreConditions = cleanUp.checkPreConditions(null, null, null);

		// then
		assertNotNull(checkPreConditions);
	}

	@Test
	public void shouldReturnNewRefactoringStatusWhenStatusIsNull() throws CoreException {
		// given

		// when
		RefactoringStatus checkPostConditions = cleanUp.checkPostConditions(null);

		// then
		assertNotNull(checkPostConditions);
	}

	@Test
	public void shouldReturnNewRefactoringStatusWhenStatusIsOk() throws CoreException {
		// given
		when(cleanOptionsMock.isEnabled(ClassOrganizerSaveCleanUp.CLEANUP_ID)).thenReturn(true);
		RefactoringStatus unexpected = cleanUp.checkPreConditions(null, null, null);

		// when
		RefactoringStatus checkPostConditions = cleanUp.checkPostConditions(null);

		// then
		assertNotNull(checkPostConditions);
		assertNotSame(unexpected, checkPostConditions);
	}

	@Test
	public void shouldGetStepDescriptions() {
		// given
		when(cleanOptionsMock.isEnabled(ClassOrganizerSaveCleanUp.CLEANUP_ID)).thenReturn(true);

		// when
		String[] stepDescriptions = cleanUp.getStepDescriptions();

		// then
		assertNotNull(stepDescriptions);
		assertEquals(1, stepDescriptions.length);
	}

	@Test
	public void shouldntGetStepDescriptions() {
		// given
		when(cleanOptionsMock.isEnabled(ClassOrganizerSaveCleanUp.CLEANUP_ID)).thenReturn(false);

		// when
		String[] stepDescriptions = cleanUp.getStepDescriptions();

		// then
		assertNull(stepDescriptions);
	}

	@Test
	public void shouldGetRequirments() {
		// given
		when(cleanOptionsMock.isEnabled(ClassOrganizerSaveCleanUp.CLEANUP_ID)).thenReturn(true);

		// when
		CleanUpRequirements requirements = cleanUp.getRequirements();

		// then
		assertNotNull(requirements);
	}

	@Test
	public void shouldntCreateFixWhenDisabled() throws CoreException {
		// given
		CompilationUnit compilationUnitMock = mock(CompilationUnit.class);

		CleanUpContext contextMock = mock(CleanUpContext.class);
		when(contextMock.getAST()).thenReturn(compilationUnitMock);

		when(cleanOptionsMock.isEnabled(ClassOrganizerSaveCleanUp.CLEANUP_ID)).thenReturn(false);

		// when
		ICleanUpFix result = cleanUp.createFix(contextMock);

		// then
		assertNull(result);
	}

	@Test
	public void shouldntCreateFixWhenCompilationUnitIsNull() throws CoreException {
		// given
		CleanUpContext contextMock = mock(CleanUpContext.class);

		// when
		ICleanUpFix result = cleanUp.createFix(contextMock);

		// then
		assertNull(result);
	}

	@Test
	public void shouldntCreateFixWhenCompileErrorsOccur() throws CoreException {
		// given
		IProblem warningMock = mock(IProblem.class);
		when(warningMock.isError()).thenReturn(false);
		IProblem errorMock = mock(IProblem.class);
		when(errorMock.isError()).thenReturn(true);

		CompilationUnit compilationUnitMock = mock(CompilationUnit.class);
		when(compilationUnitMock.getProblems()).thenReturn(new IProblem[] { warningMock, errorMock });

		CleanUpContext contextMock = mock(CleanUpContext.class);
		when(contextMock.getAST()).thenReturn(compilationUnitMock);

		when(cleanOptionsMock.isEnabled(ClassOrganizerSaveCleanUp.CLEANUP_ID)).thenReturn(true);

		// when
		ICleanUpFix result = cleanUp.createFix(contextMock);

		// then
		assertNull(result);
	}

	@Test
	public void shouldntCreateFix() throws CoreException {
		// given
		CompilationUnit compilationUnitMock = mock(CompilationUnit.class);
		when(compilationUnitMock.getProblems()).thenReturn(new IProblem[] {});

		CleanUpContext contextMock = mock(CleanUpContext.class);
		when(contextMock.getAST()).thenReturn(compilationUnitMock);

		when(cleanOptionsMock.isEnabled(ClassOrganizerSaveCleanUp.CLEANUP_ID)).thenReturn(true);
		preparePartialMockOfCleanUp(contextMock);

		// when
		ICleanUpFix result = cleanUp.createFix(contextMock);

		// then
		assertNotNull(result);
	}

	@Test
	public void shouldCreateFixWhenCompileWarningsOccur() throws CoreException {
		// given
		IProblem warningMock = mock(IProblem.class);
		when(warningMock.isError()).thenReturn(false);

		CompilationUnit compilationUnitMock = mock(CompilationUnit.class);
		when(compilationUnitMock.getProblems()).thenReturn(new IProblem[] { warningMock });

		CleanUpContext contextMock = mock(CleanUpContext.class);
		when(contextMock.getAST()).thenReturn(compilationUnitMock);

		when(cleanOptionsMock.isEnabled(ClassOrganizerSaveCleanUp.CLEANUP_ID)).thenReturn(true);
		preparePartialMockOfCleanUp(contextMock);

		// when
		ICleanUpFix result = cleanUp.createFix(contextMock);

		// then
		assertNotNull(result);
	}

	@Test
	public void shouldReturnCompilationUnitSorterFacade() {
		// given

		// when
		CompilationUnitSorterFacade compilationUnitSorterFacade = cleanUp.getCompilationUnitSorterFacade();

		// then
		assertNotNull(compilationUnitSorterFacade);
	}

	private void preparePartialMockOfCleanUp(CleanUpContext contextMock) throws JavaModelException, CoreException {
		CompilationUnitSorterFacade facadeMock = mockFacade();
		cleanUp = mock(ClassOrganizerSaveCleanUp.class);
		doCallRealMethod().when(cleanUp).setOptions(cleanOptionsMock);
		when(cleanUp.createFix(contextMock)).thenCallRealMethod();
		when(cleanUp.getCompilationUnitSorterFacade()).thenReturn(facadeMock);
		when(cleanUp.isCleanUpEnabled()).thenReturn(true);
		cleanUp.setOptions(cleanOptionsMock);
	}

	private CompilationUnitSorterFacade mockFacade() throws JavaModelException {
		CompilationUnitSorterFacade facadeMock = mock(CompilationUnitSorterFacade.class);
		TextEdit textEditMock = mock(TextEdit.class);
		when(
				facadeMock.sort(any(CompilationUnit.class), any(ClassElementComparator.class), anyInt(),
						any(CategorizedTextEditGroup.class))).thenReturn(textEditMock);
		return facadeMock;
	}

	private CleanUpOptions cleanOptionsMock;

	private ClassOrganizerSaveCleanUp cleanUp;
}
