public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    /**
     * Constructor para la clase Contact.
     *
     * @param contactId Identificador único del contacto (no nulo, máximo 10 caracteres, no actualizable).
     * @param firstName Nombre del contacto (no nulo, máximo 10 caracteres).
     * @param lastName Apellido del contacto (no nulo, máximo 10 caracteres).
     * @param phone Número telefónico (no nulo, exactamente 10 dígitos numéricos).
     * @param address Dirección del contacto (no nulo, máximo 30 caracteres).
     */
    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("El ID de contacto no debe ser nulo y no puede tener más de 10 caracteres.");
        }
        this.contactId = contactId;
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }

    /**
     * Obtiene el ID del contacto.
     *
     * @return contactId cadena con el ID único.
     */
    public String getContactId() {
        return contactId;
    }

    /**
     * Obtiene el primer nombre del contacto.
     *
     * @return firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Actualiza el primer nombre del contacto.
     *
     * @param firstName Nombre a establecer (no nulo, máximo 10 caracteres).
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("El campo firstName no debe ser nulo y no puede tener más de 10 caracteres.");
        }
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido del contacto.
     *
     * @return lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Actualiza el apellido del contacto.
     *
     * @param lastName Apellido a establecer (no nulo, máximo 10 caracteres).
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("El campo lastName no debe ser nulo y no puede tener más de 10 caracteres.");
        }
        this.lastName = lastName;
    }

    /**
     * Obtiene el teléfono del contacto.
     *
     * @return phone cadena de 10 dígitos.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Actualiza el número de teléfono del contacto.
     *
     * @param phone Teléfono a establecer (no nulo, exactamente 10 dígitos).
     */
    public void setPhone(String phone) {
        if (phone == null || phone.length() != 10 || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("El campo teléfono no debe ser nulo y debe tener exactamente 10 dígitos.");
        }
        this.phone = phone;
    }

    /**
     * Alias para getPhone(), soportando la denominación 'Número'.
     *
     * @return phone.
     */
    public String getNumber() {
        return getPhone();
    }

    /**
     * Alias para setPhone(), soportando la denominación 'Número'.
     *
     * @param number Teléfono a establecer.
     */
    public void setNumber(String number) {
        setPhone(number);
    }

    /**
     * Obtiene la dirección del contacto.
     *
     * @return address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Actualiza la dirección del contacto.
     *
     * @param address Dirección a establecer (no nula, máximo 30 caracteres).
     */
    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("El campo de dirección no debe ser nulo y no debe superar los 30 caracteres.");
        }
        this.address = address;
    }
}
