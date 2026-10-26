import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContactTest {

    private final String validId = "1234567890"; // 10 caracteres
    private final String validFirstName = "Juan";
    private final String validLastName = "Perez";
    private final String validPhone = "1234567890"; // exactamente 10 dígitos
    private final String validAddress = "Calle Principal 123";

    @Test
    @DisplayName("Verificar creación exitosa de un contacto con datos válidos")
    void testContactCreationSuccess() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);

        assertNotNull(contact);
        assertAll("Verificación de atributos del contacto",
            () -> assertEquals(validId, contact.getContactId()),
            () -> assertEquals(validFirstName, contact.getFirstName()),
            () -> assertEquals(validLastName, contact.getLastName()),
            () -> assertEquals(validPhone, contact.getPhone()),
            () -> assertEquals(validPhone, contact.getNumber()),
            () -> assertEquals(validAddress, contact.getAddress())
        );
    }

    // ==========================================
    // PRUEBAS PARA contactId
    // ==========================================

    @Test
    @DisplayName("contactId no puede ser nulo")
    void testContactIdNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, validFirstName, validLastName, validPhone, validAddress);
        });
    }

    @Test
    @DisplayName("contactId no puede tener más de 10 caracteres")
    void testContactIdTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", validFirstName, validLastName, validPhone, validAddress);
        });
    }

    @Test
    @DisplayName("contactId con exactamente 10 caracteres es válido")
    void testContactIdBoundaryLengthSuccess() {
        Contact contact = new Contact("1234567890", validFirstName, validLastName, validPhone, validAddress);
        assertEquals("1234567890", contact.getContactId());
    }

    // ==========================================
    // PRUEBAS PARA firstName
    // ==========================================

    @Test
    @DisplayName("firstName no puede ser nulo en constructor")
    void testFirstNameNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validId, null, validLastName, validPhone, validAddress);
        });
    }

    @Test
    @DisplayName("firstName no puede tener más de 10 caracteres en constructor")
    void testFirstNameTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validId, "12345678901", validLastName, validPhone, validAddress);
        });
    }

    @Test
    @DisplayName("firstName con exactamente 10 caracteres es válido")
    void testFirstNameBoundaryLengthSuccess() {
        Contact contact = new Contact(validId, "1234567890", validLastName, validPhone, validAddress);
        assertEquals("1234567890", contact.getFirstName());
    }

    @Test
    @DisplayName("setFirstName actualiza correctamente el nombre")
    void testSetFirstNameSuccess() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);
        contact.setFirstName("Carlos");
        assertEquals("Carlos", contact.getFirstName());
    }

    @Test
    @DisplayName("setFirstName lanza excepción si es nulo")
    void testSetFirstNameNullThrowsException() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName(null);
        });
    }

    @Test
    @DisplayName("setFirstName lanza excepción si tiene más de 10 caracteres")
    void testSetFirstNameTooLongThrowsException() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName("NombreDemasiadoLargo");
        });
    }

    // ==========================================
    // PRUEBAS PARA lastName
    // ==========================================

    @Test
    @DisplayName("lastName no puede ser nulo en constructor")
    void testLastNameNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validId, validFirstName, null, validPhone, validAddress);
        });
    }

    @Test
    @DisplayName("lastName no puede tener más de 10 caracteres en constructor")
    void testLastNameTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validId, validFirstName, "12345678901", validPhone, validAddress);
        });
    }

    @Test
    @DisplayName("lastName con exactamente 10 caracteres es válido")
    void testLastNameBoundaryLengthSuccess() {
        Contact contact = new Contact(validId, validFirstName, "1234567890", validPhone, validAddress);
        assertEquals("1234567890", contact.getLastName());
    }

    @Test
    @DisplayName("setLastName actualiza correctamente el apellido")
    void testSetLastNameSuccess() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);
        contact.setLastName("Gomez");
        assertEquals("Gomez", contact.getLastName());
    }

    @Test
    @DisplayName("setLastName lanza excepción si es nulo")
    void testSetLastNameNullThrowsException() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName(null);
        });
    }

    @Test
    @DisplayName("setLastName lanza excepción si tiene más de 10 caracteres")
    void testSetLastNameTooLongThrowsException() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName("ApellidoDemasiadoLargo");
        });
    }

    // ==========================================
    // PRUEBAS PARA phone / number
    // ==========================================

    @Test
    @DisplayName("phone no puede ser nulo en constructor")
    void testPhoneNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validId, validFirstName, validLastName, null, validAddress);
        });
    }

    @Test
    @DisplayName("phone con menos de 10 dígitos lanza excepción")
    void testPhoneTooShortThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validId, validFirstName, validLastName, "123456789", validAddress);
        });
    }

    @Test
    @DisplayName("phone con más de 10 dígitos lanza excepción")
    void testPhoneTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validId, validFirstName, validLastName, "12345678901", validAddress);
        });
    }

    @Test
    @DisplayName("phone con caracteres no numéricos lanza excepción")
    void testPhoneNonDigitsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validId, validFirstName, validLastName, "12345abcde", validAddress);
        });
    }

    @Test
    @DisplayName("phone con exactamente 10 dígitos numéricos es válido")
    void testPhoneExactTenDigitsSuccess() {
        Contact contact = new Contact(validId, validFirstName, validLastName, "0987654321", validAddress);
        assertEquals("0987654321", contact.getPhone());
    }

    @Test
    @DisplayName("setPhone actualiza correctamente el teléfono")
    void testSetPhoneSuccess() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);
        contact.setPhone("9876543210");
        assertEquals("9876543210", contact.getPhone());
    }

    @Test
    @DisplayName("setPhone lanza excepción si es nulo")
    void testSetPhoneNullThrowsException() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone(null);
        });
    }

    @Test
    @DisplayName("setPhone lanza excepción si no cumple con 10 dígitos")
    void testSetPhoneInvalidThrowsException() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345"));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345678901"));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("abcdefghij"));
    }

    @Test
    @DisplayName("setNumber y getNumber funcionan como alias de teléfono")
    void testSetNumberAndGetNumberSuccess() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);
        contact.setNumber("5551234567");
        assertEquals("5551234567", contact.getNumber());
        assertEquals("5551234567", contact.getPhone());
    }

    // ==========================================
    // PRUEBAS PARA address
    // ==========================================

    @Test
    @DisplayName("address no puede ser nulo en constructor")
    void testAddressNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validId, validFirstName, validLastName, validPhone, null);
        });
    }

    @Test
    @DisplayName("address no puede tener más de 30 caracteres en constructor")
    void testAddressTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validId, validFirstName, validLastName, validPhone, "1234567890123456789012345678901"); // 31 caracteres
        });
    }

    @Test
    @DisplayName("address con exactamente 30 caracteres es válida")
    void testAddressBoundaryLengthSuccess() {
        String thirtyChars = "123456789012345678901234567890"; // 30 caracteres
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, thirtyChars);
        assertEquals(thirtyChars, contact.getAddress());
    }

    @Test
    @DisplayName("setAddress actualiza correctamente la dirección")
    void testSetAddressSuccess() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);
        contact.setAddress("Avenida Central 456");
        assertEquals("Avenida Central 456", contact.getAddress());
    }

    @Test
    @DisplayName("setAddress lanza excepción si es nulo")
    void testSetAddressNullThrowsException() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress(null);
        });
    }

    @Test
    @DisplayName("setAddress lanza excepción si supera 30 caracteres")
    void testSetAddressTooLongThrowsException() {
        Contact contact = new Contact(validId, validFirstName, validLastName, validPhone, validAddress);
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress("Esta direccion tiene mas de treinta caracteres en total");
        });
    }
}
