import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interface defining the Data Access Object (DAO) operations for the Usuario class.
 */
public interface UsuarioDAO {
    /**
     * Creates a new Usuario record in the data source.
     */
    public int create(Usuario usuario) throws SQLException;

    /**
     * Retrieves a Usuario record from the data source based on the provided ID.
     */
    public Usuario read(String id) throws SQLException;

    /**
     * Updates an existing Usuario record in the data source.
     */
    public int update(Usuario usuario) throws SQLException;

    /**
     * Deletes a Usuario record from the data source based on the provided ID.
     */
    public int delete(int clave) throws SQLException;

    /**
     * Retrieves all Usuario records from the data source.
     */
    public ArrayList<Usuario> readAll() throws SQLException;
}
