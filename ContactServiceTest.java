import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private ContactService contactService;
    private Contact sampleContact;

    @BeforeEach
    void setUp() {
        contactService = new ContactService();
        sampleContact = new Contact("C1001", "Ana", "Gomez", "5551234567", "Av. Insurgentes 456");
    }

    // ==========================================
    // PRUEBAS PARA addContact
    // ==========================================

    @Test
    @DisplayName("Añadir un contacto con ID único exitosamente")
    void testAddContactSuccess() {
        contactService.addContact(sampleContact);
        Contact retrieved = contactService.getContact("C1001");
        assertNotNull(retrieved);
        assertEquals("Ana", retrieved.getFirstName());
        assertEquals("Gomez", retrieved.getLastName());
        assertEquals("5551234567", retrieved.getPhone());
        assertEquals("Av. Insurgentes 456", retrieved.getAddress());
        assertTrue(contactService.getContacts().containsKey("C1001"));
    }

    @Test
    @DisplayName("Añadir contacto con ID duplicado lanza excepción")
    void testAddContactDuplicateIdThrowsException() {
        contactService.addContact(sampleContact);
        Contact duplicateContact = new Contact("C1001", "Maria", "Lopez", "5559876543", "Otra direccion 789");

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(duplicateContact);
        });
    }

    @Test
    @DisplayName("Añadir contacto nulo lanza excepción")
    void testAddContactNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(null);
        });
    }

    // ==========================================
    // PRUEBAS PARA deleteContact
    // ==========================================

    @Test
    @DisplayName("Eliminar un contacto existente por ID exitosamente")
    void testDeleteContactSuccess() {
        contactService.addContact(sampleContact);
        assertNotNull(contactService.getContact("C1001"));

        contactService.deleteContact("C1001");
        assertNull(contactService.getContact("C1001"));
    }

    @Test
    @DisplayName("Eliminar un contacto con ID inexistente lanza excepción")
    void testDeleteContactNotFoundThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.deleteContact("NON_EXISTENT_ID");
        });
    }

    @Test
    @DisplayName("Eliminar un contacto con ID nulo lanza excepción")
    void testDeleteContactNullIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.deleteContact(null);
        });
    }

    // ==========================================
    // PRUEBAS PARA updateFirstName
    // ==========================================

    @Test
    @DisplayName("Actualizar firstName exitosamente")
    void testUpdateFirstNameSuccess() {
        contactService.addContact(sampleContact);
        contactService.updateFirstName("C1001", "Beatriz");
        assertEquals("Beatriz", contactService.getContact("C1001").getFirstName());
    }

    @Test
    @DisplayName("Actualizar firstName con ID inexistente lanza excepción")
    void testUpdateFirstNameNotFoundThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateFirstName("NO_ID", "Beatriz");
        });
    }

    @Test
    @DisplayName("Actualizar firstName con valor inválido lanza excepción")
    void testUpdateFirstNameInvalidThrowsException() {
        contactService.addContact(sampleContact);
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateFirstName("C1001", null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateFirstName("C1001", "NombreDemasiadoLargo");
        });
    }

    // ==========================================
    // PRUEBAS PARA updateLastName
    // ==========================================

    @Test
    @DisplayName("Actualizar lastName exitosamente")
    void testUpdateLastNameSuccess() {
        contactService.addContact(sampleContact);
        contactService.updateLastName("C1001", "Fernandez");
        assertEquals("Fernandez", contactService.getContact("C1001").getLastName());
    }

    @Test
    @DisplayName("Actualizar lastName con ID inexistente lanza excepción")
    void testUpdateLastNameNotFoundThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateLastName("NO_ID", "Fernandez");
        });
    }

    @Test
    @DisplayName("Actualizar lastName con valor inválido lanza excepción")
    void testUpdateLastNameInvalidThrowsException() {
        contactService.addContact(sampleContact);
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateLastName("C1001", null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateLastName("C1001", "ApellidoDemasiadoLargo");
        });
    }

    // ==========================================
    // PRUEBAS PARA updatePhone / updateNumber
    // ==========================================

    @Test
    @DisplayName("Actualizar phone exitosamente")
    void testUpdatePhoneSuccess() {
        contactService.addContact(sampleContact);
        contactService.updatePhone("C1001", "5559876543");
        assertEquals("5559876543", contactService.getContact("C1001").getPhone());
    }

    @Test
    @DisplayName("Actualizar phone con ID inexistente lanza excepción")
    void testUpdatePhoneNotFoundThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updatePhone("NO_ID", "5559876543");
        });
    }

    @Test
    @DisplayName("Actualizar phone con valor inválido lanza excepción")
    void testUpdatePhoneInvalidThrowsException() {
        contactService.addContact(sampleContact);
        assertThrows(IllegalArgumentException.class, () -> contactService.updatePhone("C1001", null));
        assertThrows(IllegalArgumentException.class, () -> contactService.updatePhone("C1001", "123"));
        assertThrows(IllegalArgumentException.class, () -> contactService.updatePhone("C1001", "12345678901"));
        assertThrows(IllegalArgumentException.class, () -> contactService.updatePhone("C1001", "phone_not!#"));
    }

    @Test
    @DisplayName("Actualizar Número utilizando método alias updateNumber")
    void testUpdateNumberSuccess() {
        contactService.addContact(sampleContact);
        contactService.updateNumber("C1001", "9998887776");
        assertEquals("9998887776", contactService.getContact("C1001").getNumber());
    }

    // ==========================================
    // PRUEBAS PARA updateAddress
    // ==========================================

    @Test
    @DisplayName("Actualizar address exitosamente")
    void testUpdateAddressSuccess() {
        contactService.addContact(sampleContact);
        contactService.updateAddress("C1001", "Nueva Direccion 789");
        assertEquals("Nueva Direccion 789", contactService.getContact("C1001").getAddress());
    }

    @Test
    @DisplayName("Actualizar address con ID inexistente lanza excepción")
    void testUpdateAddressNotFoundThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateAddress("NO_ID", "Nueva Direccion 789");
        });
    }

    @Test
    @DisplayName("Actualizar address con valor inválido lanza excepción")
    void testUpdateAddressInvalidThrowsException() {
        contactService.addContact(sampleContact);
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateAddress("C1001", null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateAddress("C1001", "Direccion extremadamente larga que sobrepasa los 30 caracteres permitidos");
        });
    }

    @Test
    @DisplayName("Actualizar campos con ID nulo lanza excepción")
    void testUpdateFieldsWithNullIdThrowsException() {
        contactService.addContact(sampleContact);
        assertThrows(IllegalArgumentException.class, () -> contactService.updateFirstName(null, "NuevoNombre"));
        assertThrows(IllegalArgumentException.class, () -> contactService.updateLastName(null, "NuevoApellido"));
        assertThrows(IllegalArgumentException.class, () -> contactService.updatePhone(null, "1112223334"));
        assertThrows(IllegalArgumentException.class, () -> contactService.updateNumber(null, "1112223334"));
        assertThrows(IllegalArgumentException.class, () -> contactService.updateAddress(null, "Nueva Direccion"));
    }

    // ==========================================
    // PRUEBAS DE CONSULTA Y ENCAPSULAMIENTO
    // ==========================================

    @Test
    @DisplayName("getContact devuelve null si el contacto no existe")
    void testGetContactNotFoundReturnsNull() {
        assertNull(contactService.getContact("NO_EXISTE"));
    }

    @Test
    @DisplayName("getContacts devuelve un mapa no modificable que protege el estado interno")
    void testGetContactsReturnsUnmodifiableMap() {
        contactService.addContact(sampleContact);
        var map = contactService.getContacts();
        assertThrows(UnsupportedOperationException.class, () -> {
            map.remove("C1001");
        });
    }
}

