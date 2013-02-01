package eu.anmore.classorganizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TypedListener;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link ClassOrganizerConfigurationPage}.
 * 
 * @author mmiedzinski
 */
public class ClassOrganizerConfigurationPageTest {

	@Before
	public void setUp() {
		page = new ClassOrganizerConfigurationPage();
	}

	@Test
	public void shouldReturnPreview() {
		// given

		// when
		String preview = page.getPreview();

		// then
		assertNotNull(preview);
	}

	@Test
	public void shouldGetSelectedCleanUp() {
		// given
		CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		when(optionsMock.isEnabled(ClassOrganizerDescriptor.CLEANUP_ID)).thenReturn(true);

		page.setOptions(optionsMock);

		// when
		int result = page.getSelectedCleanUpCount();

		// then
		assertEquals(1, result);
	}

	@Test
	public void shouldntGetSelectedCleanUp() {
		// given
		CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		// when
		int result = page.getSelectedCleanUpCount();

		// then
		assertEquals(0, result);
	}

	@Test
	public void shouldCreateContent() {
		// given
		CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		// when
		Composite createContents = page.createContents(new Shell());

		// then
		assertNotNull(createContents);
	}

	@Test
	public void shouldAddCheckBoxToPage() {
		// given
		CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		// when
		Composite createContents = page.createContents(new Shell());

		// then
		Button checkBox = findCheckBox(createContents);
		assertNotNull(checkBox);
	}

	@Test
	public void shouldAddSelectionListenerToCheckbox() {
		// given
		CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		// when
		Composite createContents = page.createContents(new Shell());

		// then
		TypedListener listener = (TypedListener) findCheckBox(createContents).getListeners(SWT.Selection)[0];
		assertNotNull(listener);
	}

	@Test
	public void shouldTpdateOptionsWhenCheckboxUnselect() {
		// given
		CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		Composite createContents = page.createContents(new Shell());

		Button checkBox = findCheckBox(createContents);
		Event eventMock = mock(Event.class);

		// when
		checkBox.notifyListeners(SWT.Selection, eventMock);

		// then
		verify(optionsMock).setOption(ClassOrganizerDescriptor.CLEANUP_ID, CleanUpOptions.FALSE);
	}

	@Test
	public void shouldTpdateOptionsWhenCheckboxSelect() {
		// given
		CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		Composite createContents = page.createContents(new Shell());

		Button checkBox = findCheckBox(createContents);
		checkBox.setSelection(true);
		Event eventMock = mock(Event.class);

		// when
		checkBox.notifyListeners(SWT.Selection, eventMock);

		// then
		verify(optionsMock).setOption(ClassOrganizerDescriptor.CLEANUP_ID, CleanUpOptions.TRUE);
	}

	private Button findCheckBox(Composite composite) {
		for (Control child : composite.getChildren()) {
			if (child instanceof Button) {
				return (Button) child;
			} else if (child instanceof Composite) {
				return findCheckBox((Composite) child);
			}
		}
		return null;
	}

	private ClassOrganizerConfigurationPage page;
}
