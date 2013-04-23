package eu.anmore.classorganizer.configuration;

import static eu.anmore.classorganizer.ClassOrganizerCleanUp.ENABLED_WHEN_COMPILATION_ERRORS_PROPERTY;
import static eu.anmore.classorganizer.configuration.AbstractOrganizerConfigurationPage.ACTIVATE_CHECKBOX_ID;
import static eu.anmore.classorganizer.configuration.AbstractOrganizerConfigurationPage.COMPILATION_ERROR_CHECKBOX_ID;
import static eu.anmore.classorganizer.configuration.AbstractOrganizerConfigurationPage.WIDGET_ID_PROPERTY;
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

import eu.anmore.classorganizer.ClassOrganizerCleanUp;

/**
 * Test class for {@link ClassOrganizerCleanUpConfigurationPage}.
 * 
 * @author mmiedzinski
 */
public class ClassOrganizerCleanUpConfigurationPageTest {

	@Before
	public void setUp() {
		page = new ClassOrganizerCleanUpConfigurationPage();
	}

	@Test
	public void shouldReturnPreview() {
		// given

		// when
		final String preview = page.getPreview();

		// then
		assertNotNull(preview);
	}

	@Test
	public void shouldGetSelectedCleanUp() {
		// given
		final CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		when(optionsMock.isEnabled(ClassOrganizerCleanUp.CLEANUP_ID)).thenReturn(true);

		page.setOptions(optionsMock);

		// when
		final int result = page.getSelectedCleanUpCount();

		// then
		assertEquals(1, result);
	}

	@Test
	public void shouldntGetSelectedCleanUp() {
		// given
		final CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		// when
		final int result = page.getSelectedCleanUpCount();

		// then
		assertEquals(0, result);
	}

	@Test
	public void shouldCreateContent() {
		// given
		final CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		// when
		final Composite createContents = page.createContents(new Shell());

		// then
		assertNotNull(createContents);
	}

	@Test
	public void shouldAddActivateCheckBoxToPage() {
		// given
		final CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		// when
		final Composite createContents = page.createContents(new Shell());

		// then
		final Button checkBox = findCheckBox(createContents, ACTIVATE_CHECKBOX_ID);
		assertNotNull(checkBox);
	}

	@Test
	public void shouldAddSelectionListenerToActivateCheckbox() {
		// given
		final CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		// when
		final Composite createContents = page.createContents(new Shell());

		// then
		final TypedListener listener = (TypedListener) findCheckBox(createContents, ACTIVATE_CHECKBOX_ID).getListeners(
				SWT.Selection)[0];
		assertNotNull(listener);
	}

	@Test
	public void shouldTpdateOptionsWhenActivateCheckboxUnselect() {
		// given
		final CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		final Composite createContents = page.createContents(new Shell());

		final Button checkBox = findCheckBox(createContents, ACTIVATE_CHECKBOX_ID);
		final Event eventMock = mock(Event.class);

		// when
		checkBox.notifyListeners(SWT.Selection, eventMock);

		// then
		verify(optionsMock).setOption(ClassOrganizerCleanUp.CLEANUP_ID, CleanUpOptions.FALSE);
	}

	@Test
	public void shouldUpdateOptionsWhenActivateCheckboxSelect() {
		// given
		final CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		final Composite createContents = page.createContents(new Shell());

		final Button checkBox = findCheckBox(createContents, ACTIVATE_CHECKBOX_ID);
		checkBox.setSelection(true);
		final Event eventMock = mock(Event.class);

		// when
		checkBox.notifyListeners(SWT.Selection, eventMock);

		// then
		verify(optionsMock).setOption(ClassOrganizerCleanUp.CLEANUP_ID, CleanUpOptions.TRUE);
	}

	@Test
	public void shouldAddCompilationErrorCheckBoxToPage() {
		// given
		final CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		// when
		final Composite createContents = page.createContents(new Shell());

		// then
		final Button checkBox = findCheckBox(createContents, COMPILATION_ERROR_CHECKBOX_ID);
		assertNotNull(checkBox);
	}

	@Test
	public void shouldAddSelectionListenerToCompilationErrorCheckbox() {
		// given
		final CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		// when
		final Composite createContents = page.createContents(new Shell());

		// then
		final TypedListener listener = (TypedListener) findCheckBox(createContents, COMPILATION_ERROR_CHECKBOX_ID)
				.getListeners(SWT.Selection)[0];
		assertNotNull(listener);
	}

	@Test
	public void shouldTpdateOptionsWhenCompilationErrorCheckboxUnselect() {
		// given
		final CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		final Composite createContents = page.createContents(new Shell());

		final Button checkBox = findCheckBox(createContents, COMPILATION_ERROR_CHECKBOX_ID);
		final Event eventMock = mock(Event.class);

		// when
		checkBox.notifyListeners(SWT.Selection, eventMock);

		// then
		verify(optionsMock).setOption(ENABLED_WHEN_COMPILATION_ERRORS_PROPERTY, CleanUpOptions.FALSE);
	}

	@Test
	public void shouldUpdateOptionsWhenCompilationErrorCheckboxSelect() {
		// given
		final CleanUpOptions optionsMock = mock(CleanUpOptions.class);
		page.setOptions(optionsMock);

		final Composite createContents = page.createContents(new Shell());

		final Button checkBox = findCheckBox(createContents, COMPILATION_ERROR_CHECKBOX_ID);
		checkBox.setSelection(true);
		final Event eventMock = mock(Event.class);

		// when
		checkBox.notifyListeners(SWT.Selection, eventMock);

		// then
		verify(optionsMock).setOption(ENABLED_WHEN_COMPILATION_ERRORS_PROPERTY, CleanUpOptions.TRUE);
	}

	private Button findCheckBox(final Composite composite, final String widgetId) {
		for (final Control child : composite.getChildren()) {
			if (child instanceof Button && widgetId.equals(child.getData(WIDGET_ID_PROPERTY))) {
				return (Button) child;
			} else if (child instanceof Composite) {
				return findCheckBox((Composite) child, widgetId);
			}
		}
		return null;
	}

	private AbstractOrganizerConfigurationPage page;
}
