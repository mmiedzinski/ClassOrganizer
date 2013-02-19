package eu.anmore.classorganizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.refactoring.CompilationUnitChange;
import org.eclipse.jdt.ui.cleanup.ICleanUpFix;
import org.eclipse.ltk.core.refactoring.CategorizedTextEditGroup;
import org.eclipse.text.edits.TextEdit;
import org.junit.Test;

import eu.anmore.classorganizer.utils.ClassElementComparator;
import eu.anmore.classorganizer.utils.CompilationUnitSorterFacade;

/**
 * Test class for {@link ClassOrderFix}.
 * 
 * @author mmiedzinski
 */
public class ClassOrderFixTest {

	@Test
	public void shouldCreateCleanUp() throws CoreException {
		// given
		TextEdit textEditMock = mock(TextEdit.class);

		CompilationUnit compilationUnitMock = mock(CompilationUnit.class);
		CompilationUnitSorterFacade compilationUnitSorterFacadeMock = mock(CompilationUnitSorterFacade.class);
		when(
				compilationUnitSorterFacadeMock.sort(any(CompilationUnit.class), any(ClassElementComparator.class),
						anyInt(), any(CategorizedTextEditGroup.class))).thenReturn(textEditMock);

		// when
		ICleanUpFix cleanUp = ClassOrderFix.createCleanUp(compilationUnitMock, compilationUnitSorterFacadeMock);

		// then
		assertNotNull(cleanUp);
	}

	@Test
	public void shouldntCreateCleanUpWhenEditIsNull() throws CoreException {
		// given
		CompilationUnit compilationUnitMock = mock(CompilationUnit.class);
		CompilationUnitSorterFacade compilationUnitSorterFacadeMock = mock(CompilationUnitSorterFacade.class);

		// when
		ICleanUpFix cleanUp = ClassOrderFix.createCleanUp(compilationUnitMock, compilationUnitSorterFacadeMock);

		// then
		assertNull(cleanUp);
	}

	@Test
	public void shouldCreateCompilationUnitChange() throws CoreException {
		// given
		TextEdit textEditMock = mock(TextEdit.class);

		IFile iFileMock = mock(IFile.class);
		ICompilationUnit compilationUnitMock = mock(ICompilationUnit.class);
		when(compilationUnitMock.getResource()).thenReturn(iFileMock);

		ClassOrderFix classOrderFix = new ClassOrderFix(textEditMock, compilationUnitMock);

		// when
		CompilationUnitChange result = classOrderFix.createChange(null);

		// then
		assertNotNull(result);
		assertEquals(compilationUnitMock, result.getCompilationUnit());
		assertEquals(textEditMock, result.getEdit());
	}
}
