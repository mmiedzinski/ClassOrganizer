package eu.anmore.classorganizer;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link ModifierResolverImpl}.
 * 
 * @author mmiedzinski
 */
public class ModifierResolverImplTest {

	@Before
	public void setUp() {
		modifierFacadeMock = mock(ModifierFacade.class);
		bodyDeclarationMock = mock(BodyDeclaration.class);
		resolver = new ModifierResolverImpl(modifierFacadeMock);
	}

	@Test
	public void shouldResolveStaticPublicMethod() {
		// given
		bodyDeclarationMock = mock(MethodDeclaration.class);

		when(modifierFacadeMock.isPublic(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PUBLIC_STATIC_METHOD, result);
	}

	@Test
	public void shouldResolveStaticPublicField() {
		// given
		when(modifierFacadeMock.isFieldDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isPublic(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PUBLIC_STATIC_FIELD, result);
	}

	@Test
	public void shouldResolveStaticPublicInitializer() {
		// given
		when(modifierFacadeMock.isInitializerDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isPublic(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PUBLIC_STATIC_INITIALIZER, result);
	}

	@Test
	public void shouldResolveStaticPublicType() {
		// given
		when(modifierFacadeMock.isTypeDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isPublic(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PUBLIC_STATIC_TYPE, result);
	}

	@Test
	public void shouldResolvePublicConstructor() {
		// given
		bodyDeclarationMock = mock(MethodDeclaration.class);
		when(((MethodDeclaration) bodyDeclarationMock).isConstructor()).thenReturn(true);

		when(modifierFacadeMock.isPublic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PUBLIC_CONSTRUCTOR, result);
	}

	@Test
	public void shouldResolvePublicMethod() {
		// given
		bodyDeclarationMock = mock(MethodDeclaration.class);

		when(modifierFacadeMock.isPublic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PUBLIC_METHOD, result);
	}

	@Test
	public void shouldResolvePublicField() {
		// given
		when(modifierFacadeMock.isFieldDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isPublic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PUBLIC_FIELD, result);
	}

	@Test
	public void shouldResolvePublicInitializer() {
		// given
		when(modifierFacadeMock.isInitializerDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isPublic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PUBLIC_INITIALIZER, result);
	}

	@Test
	public void shouldResolvePublicType() {
		// given
		when(modifierFacadeMock.isTypeDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isPublic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PUBLIC_TYPE, result);
	}

	@Test
	public void shouldResolveStaticProtectedMethod() {
		// given
		bodyDeclarationMock = mock(MethodDeclaration.class);

		when(modifierFacadeMock.isProtected(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PROTECTED_STATIC_METHOD, result);
	}

	@Test
	public void shouldResolveStaticProtectedField() {
		// given
		when(modifierFacadeMock.isFieldDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isProtected(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PROTECTED_STATIC_FIELD, result);
	}

	@Test
	public void shouldResolveStaticProtectedInitializer() {
		// given
		when(modifierFacadeMock.isInitializerDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isProtected(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PROTECTED_STATIC_INITIALIZER, result);
	}

	@Test
	public void shouldResolveStaticProtectedType() {
		// given
		when(modifierFacadeMock.isTypeDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isProtected(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PROTECTED_STATIC_TYPE, result);
	}

	@Test
	public void shouldResolveProtectedConstructor() {
		// given
		bodyDeclarationMock = mock(MethodDeclaration.class);
		when(((MethodDeclaration) bodyDeclarationMock).isConstructor()).thenReturn(true);

		when(modifierFacadeMock.isProtected(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PROTECTED_CONSTRUCTOR, result);
	}

	@Test
	public void shouldResolveProtectedMethod() {
		// given
		bodyDeclarationMock = mock(MethodDeclaration.class);

		when(modifierFacadeMock.isProtected(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PROTECTED_METHOD, result);
	}

	@Test
	public void shouldResolveProtectedField() {
		// given
		when(modifierFacadeMock.isFieldDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isProtected(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PROTECTED_FIELD, result);
	}

	@Test
	public void shouldResolveProtectedInitializer() {
		// given
		when(modifierFacadeMock.isInitializerDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isProtected(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PROTECTED_INITIALIZER, result);
	}

	@Test
	public void shouldResolveProtectedType() {
		// given
		when(modifierFacadeMock.isTypeDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isProtected(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PROTECTED_TYPE, result);
	}

	@Test
	public void shouldResolveStaticPrivateMethod() {
		// given
		bodyDeclarationMock = mock(MethodDeclaration.class);

		when(modifierFacadeMock.isPrivate(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PRIVATE_STATIC_METHOD, result);
	}

	@Test
	public void shouldResolveStaticPrivateField() {
		// given
		when(modifierFacadeMock.isFieldDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isPrivate(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PRIVATE_STATIC_FIELD, result);
	}

	@Test
	public void shouldResolveStaticPrivateInitializer() {
		// given
		when(modifierFacadeMock.isInitializerDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isPrivate(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PRIVATE_STATIC_INITIALIZER, result);
	}

	@Test
	public void shouldResolveStaticPrivateType() {
		// given
		when(modifierFacadeMock.isTypeDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isPrivate(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PRIVATE_STATIC_TYPE, result);
	}

	@Test
	public void shouldResolvePrivateConstructor() {
		// given
		bodyDeclarationMock = mock(MethodDeclaration.class);
		when(((MethodDeclaration) bodyDeclarationMock).isConstructor()).thenReturn(true);

		when(modifierFacadeMock.isPrivate(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PRIVATE_CONSTRUCTOR, result);
	}

	@Test
	public void shouldResolvePrivateMethod() {
		// given
		bodyDeclarationMock = mock(MethodDeclaration.class);

		when(modifierFacadeMock.isPrivate(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PRIVATE_METHOD, result);
	}

	@Test
	public void shouldResolvePrivateField() {
		// given
		when(modifierFacadeMock.isFieldDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isPrivate(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PRIVATE_FIELD, result);
	}

	@Test
	public void shouldResolvePrivateInitializer() {
		// given
		when(modifierFacadeMock.isInitializerDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isPrivate(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PRIVATE_INITIALIZER, result);
	}

	@Test
	public void shouldResolvePrivateType() {
		// given
		when(modifierFacadeMock.isTypeDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isPrivate(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.PRIVATE_TYPE, result);
	}

	@Test
	public void shouldResolveStaticDefaultMethod() {
		// given
		bodyDeclarationMock = mock(MethodDeclaration.class);

		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.DEFAULT_STATIC_METHOD, result);
	}

	@Test
	public void shouldResolveStaticDefaultField() {
		// given
		when(modifierFacadeMock.isFieldDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.DEFAULT_STATIC_FIELD, result);
	}

	@Test
	public void shouldResolveStaticDefaultInitializer() {
		// given
		when(modifierFacadeMock.isInitializerDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.DEFAULT_STATIC_INITIALIZER, result);
	}

	@Test
	public void shouldResolveStaticDefaultType() {
		// given
		when(modifierFacadeMock.isTypeDeclaration(bodyDeclarationMock)).thenReturn(true);
		when(modifierFacadeMock.isStatic(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.DEFAULT_STATIC_TYPE, result);
	}

	@Test
	public void shouldResolveDefaultConstructor() {
		// given
		bodyDeclarationMock = mock(MethodDeclaration.class);
		when(((MethodDeclaration) bodyDeclarationMock).isConstructor()).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.DEFAULT_CONSTRUCTOR, result);
	}

	@Test
	public void shouldResolveDefaultMethod() {
		// given
		bodyDeclarationMock = mock(MethodDeclaration.class);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.DEFAULT_METHOD, result);
	}

	@Test
	public void shouldResolveDefaultField() {
		// given
		when(modifierFacadeMock.isFieldDeclaration(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.DEFAULT_FIELD, result);
	}

	@Test
	public void shouldResolveDefaultInitializer() {
		// given
		when(modifierFacadeMock.isInitializerDeclaration(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.DEFAULT_INITIALIZER, result);
	}

	@Test
	public void shouldResolveDefaultType() {
		// given
		when(modifierFacadeMock.isTypeDeclaration(bodyDeclarationMock)).thenReturn(true);

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(ClassOrganizerDescriptor.DEFAULT_TYPE, result);
	}

	@Test
	public void shouldReturnZeroWhenNotResolver() {
		// given

		// when
		int result = resolver.resolve(bodyDeclarationMock);

		// then
		assertEquals(0, result);
	}

	private ModifierFacade modifierFacadeMock;

	private BodyDeclaration bodyDeclarationMock;

	private ModifierResolver resolver;
}
