import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Implementation of UsuarioDAO for data storage and retrieval using MySQL database.
 */
public class UsuarioDAOMySQL implements UsuarioDAO {

    @Override
    public int create(Usuario usuario) throws SQLException {
        Connection con = ConexionDB.getConnection();
        String query = "INSERT INTO usuarios(id, nombre, apellido, correo, contraseña) VALUES (?,?,?,?,?);";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setInt(1, usuario.getId());
        pstm.setString(2, usuario.getNombre());
        pstm.setString(3, usuario.getApellido());
        pstm.setString(4, usuario.getCorreo());
        pstm.setString(5, usuario.getContrasena());

        int res = pstm.executeUpdate();

        return res;
    }

    @Override
    public Usuario read(String id) throws SQLException {
        Connection con = ConexionDB.getConnection();
        String query = "SELECT * FROM usuarios WHERE id = ?";

        Usuario ex = null;
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            int idRs = rs.getInt(1);
            String nom = rs.getString(2);
            String ap = rs.getString(3);
            String correo = rs.getString(4);
            String contr = rs.getString(5);
            ex = new Usuario(idRs, nom, ap, correo, contr);
        }
        return ex;
    }

    @Override
    public ArrayList<Usuario> readAll() throws SQLException {
        Connection con = ConexionDB.getConnection();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        // (id, nombre, apellido, correo, contraseña
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String ap = rs.getString("apellido");
            String correo = rs.getString("apellido");
            String conTra = rs.getString("contraseña");

            Usuario aux = new Usuario(id, nombre, ap, correo, conTra);

            usuarios.add(aux);
        }
        return usuarios;
    }

    @Override //id, nombre, apellido, correo, contraseña
    public int update(Usuario usuario) throws SQLException {
        Connection con = ConexionDB.getConnection();
        String upd = "UPDATE usuarios SET id = ?, nombre = ?, apellido = ?, correo = ?, contraseña = ? WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(upd);
        ps.setInt(1, usuario.getId());
        ps.setString(2, usuario.getNombre());
        ps.setString(3, usuario.getApellido());
        ps.setString(4, usuario.getCorreo());
        ps.setString(5, usuario.getContrasena());
        ps.setInt(6, usuario.getId());

        int result = ps.executeUpdate();
        return result;
    }

    @Override
    public int delete(int clave) throws SQLException {
        Connection con = ConexionDB.getConnection();
        String sql = "DELETE FROM usuarios WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, clave);
        int result = ps.executeUpdate();
        return result;
    }
}
