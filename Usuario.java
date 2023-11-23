/**
 * Represents a user entity with basic information.
 */
public class Usuario {
    private int id;            // User ID
    private String nombre;     // User's first name
    private String apellido;   // User's last name
    private String correo;     // User's email address
    private String contrasena; // User's password

    /**
     * Constructor to initialize the user object with specified values.
     * @param id The unique identifier for the user.
     * @param nombre The first name of the user.
     * @param apellido The last name of the user.
     * @param correo The email address of the user.
     * @param contrasena The password associated with the user.
     */
    public Usuario(int id, String nombre, String apellido, String correo, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    /**
     * Setter method to update the user's ID.
     * @param id The new user ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter method to update the user's first name.
     * @param nombre The new first name.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Setter method to update the user's last name.
     * @param apellido The new last name.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Setter method to update the user's email address.
     * @param correo The new email address.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Setter method to update the user's password.
     * @param contrasena The new password.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Getter method to retrieve the user's ID.
     * @return The user's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter method to retrieve the user's first name.
     * @return The user's first name.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter method to retrieve the user's last name.
     * @return The user's last name.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Getter method to retrieve the user's email address.
     * @return The user's email address.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Getter method to retrieve the user's password.
     * @return The user's password.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Overrides the default toString() method to provide a formatted string representation of the user object.
     * @return A string representation of the user object.
     */
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo
                + ", contrasena=" + contrasena + "]";
    }

    /**
     * Generates a formatted string for writing the user information to a file.
     * @return A formatted string for file storage.
     */
    public String formatoArchivo(){
        return id + "|"+ nombre + "|"+ apellido + "|"+ correo + "|"+ contrasena + "|";
    }
}
