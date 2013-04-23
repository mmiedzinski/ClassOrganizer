package eu.anmore.classorganizer.configuration;

import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.eclipse.jdt.ui.cleanup.ICleanUpConfigurationUI;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.PixelConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

/**
 * Configuration page for ClassOrganizer.
 * 
 * @author mmiedzinski
 */
public abstract class AbstractOrganizerConfigurationPage implements ICleanUpConfigurationUI {

	public AbstractOrganizerConfigurationPage(final String cleanUpId, final String ignoreCompilationErrorKey) {
		this.cleanUpId = cleanUpId;
		this.ignoreCompilationErrorKey = ignoreCompilationErrorKey;
	}

	@Override
	public Composite createContents(final Composite parent) {
		final int numColumns = 4;

		final PixelConverter pixelConverter = new PixelConverter(parent);

		final SashForm sashForm = createSashForm(parent);

		final Composite scrollContainer = new Composite(sashForm, SWT.NONE);
		scrollContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		final ScrolledComposite scroll = new ScrolledComposite(scrollContainer, SWT.V_SCROLL | SWT.H_SCROLL);
		scroll.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		scroll.setExpandHorizontal(true);
		scroll.setExpandVertical(true);

		final Composite settingsContainer = new Composite(scroll, SWT.NONE);
		settingsContainer.setFont(sashForm.getFont());

		scroll.setContent(settingsContainer);

		settingsContainer.setLayout(new PageLayout(scroll, 400, 400));
		settingsContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		final Composite settingsPane = new Composite(settingsContainer, SWT.NONE);
		settingsPane.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		setLayout(numColumns, pixelConverter, scrollContainer, settingsPane);
		doCreatePreferences(settingsPane);

		settingsContainer.setSize(settingsContainer.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		scroll.addControlListener(new ControlListener() {

			@Override
			public void controlMoved(final ControlEvent e) {
			}

			@Override
			public void controlResized(final ControlEvent e) {
				settingsContainer.setSize(settingsContainer.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			}
		});

		final Label sashHandle = new Label(scrollContainer, SWT.SEPARATOR | SWT.VERTICAL);
		sashHandle.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, true));

		return sashForm;
	}

	@Override
	public int getCleanUpCount() {
		return 1;
	}

	@Override
	public String getPreview() {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\npublic class SampleClass {\n\n");
		stringBuilder.append("\tpublic static void staticPublicMethod() {\n\t}\n\n");
		stringBuilder.append("\tpublic static int staticPublicField;\n\n");
		stringBuilder.append("\tpublic static enum NewStaticPublicType {\n\t\tSAMPLE\n\t}\n\n");
		stringBuilder.append("\tpublic SampleClass {\n\t}\n\n");
		stringBuilder.append("\tpublic void publicMethod() {\n\t}\n\n");
		stringBuilder.append("\tpublic int publicField;\n\n");
		stringBuilder.append("\tpublic enum NewPublicType {\n\t\tSAMPLE\n\t}\n\n");

		stringBuilder.append("\tprotected static void staticProtectedMethod() {\n\t}\n\n");
		stringBuilder.append("\tprotected static int staticProtectedField;\n\n");
		stringBuilder.append("\tprotected static enum NewStaticProtectedType {\n\t\tSAMPLE\n\t}\n\n");
		stringBuilder.append("\tprotected SampleClass {\n\t}\n\n");
		stringBuilder.append("\tprotected void protectedMethod() {\n\t}\n\n");
		stringBuilder.append("\tprotected int protectedField;\n\n");
		stringBuilder.append("\tprotected enum NewProtectedType {\n\t\tSAMPLE\n\t}\n\n");

		stringBuilder.append("\tstatic void staticDefaultMethod() {\n\t}\n\n");
		stringBuilder.append("\tstatic int staticDefaultField;\n\n");
		stringBuilder.append("\tstatic enum NewStaticDefaultType {\n\t\tSAMPLE\n\t}\n\n");
		stringBuilder.append("\tSampleClass {\n\t}\n\n");
		stringBuilder.append("\tvoid defaultMethod() {\n\t}\n\n");
		stringBuilder.append("\tint defaultField;\n\n");
		stringBuilder.append("\tenum NewDefaultType {\n\t\tSAMPLE\n\t}\n\n");

		stringBuilder.append("\tprivate static void staticPrivateMethod() {\n\t}\n\n");
		stringBuilder.append("\tprivate static int staticPrivateField;\n\n");
		stringBuilder.append("\tprivate static enum NewStaticPrivateType {\n\t\tSAMPLE\n\t}\n\n");
		stringBuilder.append("\tprivate SampleClass {\n\t}\n\n");
		stringBuilder.append("\tprivate void privateMethod() {\n\t}\n\n");
		stringBuilder.append("\tprivate int privateField;\n\n");
		stringBuilder.append("\tprivate enum NewPrivateType {\n\t\tSAMPLE\n\t}\n");

		stringBuilder.append("}");
		return stringBuilder.toString();
	}

	@Override
	public int getSelectedCleanUpCount() {
		return options.isEnabled(cleanUpId) ? 1 : 0;
	}

	@Override
	public void setOptions(final CleanUpOptions options) {
		this.options = options;
	}

	static final String WIDGET_ID_PROPERTY = "widgetId";

	static final String ACTIVATE_CHECKBOX_ID = "activateCheckbox";

	static final String COMPILATION_ERROR_CHECKBOX_ID = "compilationErrorCheckbox";

	private static final class PageLayout extends Layout {

		@Override
		public Point computeSize(final Composite composite, final int wHint, final int hHint, final boolean force) {
			if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT) {
				return new Point(wHint, hHint);
			}

			int x = fMinimalWidth;
			int y = fMinimalHight;
			final Control[] children = composite.getChildren();
			for (int i = 0; i < children.length; i++) {
				final Point size = children[i].computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
				x = Math.max(x, size.x);
				y = Math.max(y, size.y);
			}

			final Rectangle area = fContainer.getClientArea();
			if (area.width > x) {
				fContainer.setExpandHorizontal(true);
			} else {
				fContainer.setExpandHorizontal(false);
			}

			if (area.height > y) {
				fContainer.setExpandVertical(true);
			} else {
				fContainer.setExpandVertical(false);
			}

			if (wHint != SWT.DEFAULT) {
				x = wHint;
			}
			if (hHint != SWT.DEFAULT) {
				y = hHint;
			}

			return new Point(x, y);
		}

		@Override
		public void layout(final Composite composite, final boolean force) {
			final Rectangle rect = composite.getClientArea();
			final Control[] children = composite.getChildren();
			for (int i = 0; i < children.length; i++) {
				children[i].setSize(rect.width, rect.height);
			}
		}

		private PageLayout(final ScrolledComposite container, final int minimalWidth, final int minimalHight) {
			fContainer = container;
			fMinimalWidth = minimalWidth;
			fMinimalHight = minimalHight;
		}

		private final ScrolledComposite fContainer;

		private final int fMinimalWidth;

		private final int fMinimalHight;
	}

	private void doCreatePreferences(final Composite composite) {
		final Group group = createGroup(composite);
		createActivateCheckbox(group);
		createCompilationErrorsCheckbox(group);
	}

	private void createActivateCheckbox(final Group group) {
		final Button checkbox = new Button(group, SWT.CHECK);
		checkbox.setData(WIDGET_ID_PROPERTY, ACTIVATE_CHECKBOX_ID);
		checkbox.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		checkbox.setText("Organizer enabled");
		checkbox.setSelection(options.isEnabled(cleanUpId));
		checkbox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				options.setOption(cleanUpId, checkbox.getSelection() ? CleanUpOptions.TRUE : CleanUpOptions.FALSE);
			}
		});
	}

	private void createCompilationErrorsCheckbox(final Group group) {
		final Button checkbox = new Button(group, SWT.CHECK);
		checkbox.setData(WIDGET_ID_PROPERTY, COMPILATION_ERROR_CHECKBOX_ID);
		checkbox.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		checkbox.setText("Organize class with compilation errors");
		checkbox.setSelection(options.isEnabled(ignoreCompilationErrorKey));
		checkbox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				options.setOption(ignoreCompilationErrorKey, checkbox.getSelection() ? CleanUpOptions.TRUE
						: CleanUpOptions.FALSE);
			}
		});
	}

	private Group createGroup(final Composite composite) {
		final Group group = new Group(composite, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		group.setLayout(new GridLayout(1, false));
		group.setText("Class organizer");
		return group;
	}

	private void setLayout(final int numColumns, final PixelConverter pixelConverter, final Composite scrollContainer,
			final Composite settingsPane) {
		GridLayout layout = new GridLayout(2, false);
		scrollContainer.setLayout(layout);
		layout = new GridLayout(numColumns, false);
		layout.verticalSpacing = (int) (1.5 * pixelConverter
				.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING));
		layout.horizontalSpacing = pixelConverter.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		layout.marginHeight = pixelConverter.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth = pixelConverter.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		settingsPane.setLayout(layout);
	}

	private SashForm createSashForm(final Composite parent) {
		final SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
		sashForm.setFont(parent.getFont());
		return sashForm;
	}

	private final String ignoreCompilationErrorKey;

	private final String cleanUpId;

	private CleanUpOptions options;
}
