import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implementation of UsuarioDAO for data storage and retrieval using text files.
 */
public class UsuarioDAOArchivo implements UsuarioDAO {

    private String nombreArchivo;
    private ArrayList<Usuario> listaUsuarios;

    /**
     * Constructor for UsuarioDAOArchivo with a specified file name.
     */
    public UsuarioDAOArchivo(String nomAr) {
        this.nombreArchivo = nomAr;
    }

    /**
     * Constructor for UsuarioDAOArchivo with an existing list of Usuario objects.
     */
    public UsuarioDAOArchivo(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    /**
     * Clears the contents of the file associated with the UsuarioDAOArchivo instance.
     */
    public void borrarContenido() {
        try {
            FileWriter escribir = new FileWriter(nombreArchivo);
            PrintWriter linea = new PrintWriter(escribir);

            // Close streams to ensure changes are saved
            linea.close();
            escribir.close();

        } catch (IOException e) {
            System.out.println("Error al borrar el contenido: " + e);
        }
    }

    @Override
    public int create(Usuario usuario) throws SQLException {
        PrintWriter linea = null;
        FileWriter escribir = null;
        File archivo = new File(nombreArchivo);

        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            // Open the file in append mode (true)
            escribir = new FileWriter(archivo, true);
            linea = new PrintWriter(escribir);

            // Write to the file
            linea.println(usuario.formatoArchivo());

        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            // Make sure to close PrintWriter and FileWriter in the finally block
            if (linea != null) {
                linea.close();
            }
            if (escribir != null) {
                try {
                    escribir.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar FileWriter: " + e);
                }
            }
        }
        return 0;
    }

    @Override
    public Usuario read(String id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public int update(Usuario usuario) throws SQLException {
        int result = -1;

        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                ArrayList<Usuario> listaUsuarios = readAll();
                for (Usuario usuarioAux : listaUsuarios) {
                    if (usuarioAux.getId() == usuario.getId()) {
                        usuarioAux.setNombre(usuario.getNombre());
                        usuarioAux.setApellido(usuario.getApellido());
                        usuarioAux.setCorreo(usuario.getCorreo());
                        usuarioAux.setContrasena(usuario.getContrasena());
                        break;
                    }
                }

                PrintWriter linea = null;
                FileWriter escribir = null;

                try {
                    escribir = new FileWriter(archivo, false);
                    linea = new PrintWriter(escribir);

                    for (Usuario usuarioAux : listaUsuarios) {
                        linea.println(usuarioAux.formatoArchivo());
                    }
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                } finally {
                    if (linea != null) {
                        linea.close();
                    }
                    if (escribir != null) {
                        try {
                            escribir.close();
                        } catch (IOException e) {
                            System.out.println("Error al cerrar FileWriter: " + e);
                        }
                    }
                }

                result = 0;
            } else {
                System.out.println("Lista no existente");
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }

        return result;
    }

    /**
     * Removes a Usuario from the list based on the provided ID.
     */
    public ArrayList<Usuario> borrar(int clave) throws SQLException {
        Iterator<Usuario> usuarioIterator = listaUsuarios.iterator();
        while (usuarioIterator.hasNext()) {
            Usuario usuario = usuarioIterator.next();
            if (usuario.getId() == clave) {
                usuarioIterator.remove();
            }
        }
        return listaUsuarios;
    }

    @Override
    public ArrayList<Usuario> readAll() throws SQLException {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                FileReader leer = new FileReader(archivo);
                BufferedReader br = new BufferedReader(leer);
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] valores = linea.split("\\|");
                    int id = Integer.parseInt(valores[0]);
                    String nombre = valores[1];
                    String apellido = valores[2];
                    String correo = valores[3];
                    String contrasena = valores[4];

                    Usuario aux = new Usuario(id, nombre, apellido, correo, contrasena);

                    listaUsuarios.add(aux);

                }
            } else {
                System.out.println("Lista no existente");
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return listaUsuarios;
    }

    @Override
    public int delete(int clave) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
