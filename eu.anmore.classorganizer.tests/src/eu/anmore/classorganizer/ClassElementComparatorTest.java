package eu.anmore.classorganizer;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link ClassElementComparator}.
 * 
 * @author mmiedzinski
 */
public class ClassElementComparatorTest {

	@Before
	public void setUp() {
		modifierResolverMock = mock(ModifierResolver.class);
		comparator = new ClassElementComparator(modifierResolverMock);
	}

	@Test
	public void shouldCompareElements() {
		// given
		BodyDeclaration bodyDeclaration1Mock = mock(BodyDeclaration.class);
		BodyDeclaration bodyDeclaration2Mock = mock(BodyDeclaration.class);

		when(modifierResolverMock.resolve(bodyDeclaration1Mock)).thenReturn(ClassOrganizerDescriptor.DEFAULT_FIELD);
		when(modifierResolverMock.resolve(bodyDeclaration2Mock)).thenReturn(ClassOrganizerDescriptor.DEFAULT_METHOD);

		// when
		int result = comparator.compare(bodyDeclaration1Mock, bodyDeclaration2Mock);

		// then
		assertEquals(ClassOrganizerDescriptor.DEFAULT_FIELD - ClassOrganizerDescriptor.DEFAULT_METHOD, result);
	}

	private ModifierResolver modifierResolverMock;

	private ClassElementComparator comparator;
}
