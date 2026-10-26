import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ContactService {
    // Estructura de datos en memoria para almacenar los contactos mapeados por su ID único
    private final Map<String, Contact> contacts;

    /**
     * Constructor por defecto que inicializa la estructura de datos en memoria.
     */
    public ContactService() {
        this.contacts = new HashMap<>();
    }

    /**
     * Añade un contacto al servicio con un número de identificación único.
     *
     * @param contact Objeto contacto a añadir.
     * @throws IllegalArgumentException Si el contacto es nulo o si ya existe un contacto con el mismo ID.
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("El contacto no puede ser nulo.");
        }
        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Ya existe un contacto con el ID: " + id);
        }
        contacts.put(id, contact);
    }

    /**
     * Elimina un contacto del servicio utilizando su número de identificación de contacto.
     *
     * @param contactId ID único del contacto a eliminar.
     * @throws IllegalArgumentException Si el ID es nulo o si el contacto no existe.
     */
    public void deleteContact(String contactId) {
        if (contactId == null || !contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("No se encontró ningún contacto con el ID: " + contactId);
        }
        contacts.remove(contactId);
    }

    /**
     * Actualiza el campo firstName de un contacto por su número de identificación.
     *
     * @param contactId ID único del contacto a actualizar.
     * @param firstName Nuevo nombre (no nulo, máximo 10 caracteres).
     * @throws IllegalArgumentException Si el ID no existe o si el nuevo nombre es inválido.
     */
    public void updateFirstName(String contactId, String firstName) {
        Contact contact = getContactOrThrow(contactId);
        contact.setFirstName(firstName);
    }

    /**
     * Actualiza el campo lastName de un contacto por su número de identificación.
     *
     * @param contactId ID único del contacto a actualizar.
     * @param lastName Nuevo apellido (no nulo, máximo 10 caracteres).
     * @throws IllegalArgumentException Si el ID no existe o si el nuevo apellido es inválido.
     */
    public void updateLastName(String contactId, String lastName) {
        Contact contact = getContactOrThrow(contactId);
        contact.setLastName(lastName);
    }

    /**
     * Actualiza el campo de teléfono de un contacto por su número de identificación.
     *
     * @param contactId ID único del contacto a actualizar.
     * @param phone Nuevo teléfono (no nulo, exactamente 10 dígitos).
     * @throws IllegalArgumentException Si el ID no existe o si el teléfono es inválido.
     */
    public void updatePhone(String contactId, String phone) {
        Contact contact = getContactOrThrow(contactId);
        contact.setPhone(phone);
    }

    /**
     * Actualiza el campo 'Número' de un contacto por su número de identificación (alias de updatePhone).
     *
     * @param contactId ID único del contacto a actualizar.
     * @param number Nuevo número de teléfono (no nulo, exactamente 10 dígitos).
     * @throws IllegalArgumentException Si el ID no existe o si el número es inválido.
     */
    public void updateNumber(String contactId, String number) {
        updatePhone(contactId, number);
    }

    /**
     * Actualiza el campo de dirección de un contacto por su número de identificación.
     *
     * @param contactId ID único del contacto a actualizar.
     * @param address Nueva dirección (no nula, máximo 30 caracteres).
     * @throws IllegalArgumentException Si el ID no existe o si la dirección es inválida.
     */
    public void updateAddress(String contactId, String address) {
        Contact contact = getContactOrThrow(contactId);
        contact.setAddress(address);
    }

    /**
     * Obtiene un contacto por su ID único.
     *
     * @param contactId ID del contacto.
     * @return El objeto Contact si existe, o null si no se encuentra.
     */
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    /**
     * Obtiene una vista no modificable de todos los contactos en el servicio.
     *
     * @return Mapa no modificable de ID a Contact.
     */
    public Map<String, Contact> getContacts() {
        return Collections.unmodifiableMap(contacts);
    }

    /**
     * Método auxiliar privado para buscar el contacto o lanzar una excepción si no existe.
     */
    private Contact getContactOrThrow(String contactId) {
        if (contactId == null || !contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("No se encontró ningún contacto con el ID: " + contactId);
        }
        return contacts.get(contactId);
    }
}
