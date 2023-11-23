import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        // ArrayList<Usuario> usuarios;
        Usuario us1 = new Usuario(1, "Juan", "Perez", "ejemplo@.com", "1234");
        Usuario us2 = new Usuario(2, "Pedro", "Lopez", "ejemplo2@.com", "1234");
        Usuario us3 = new Usuario(3, "Maria", "Gonzalez", "ejemplo3@.com", "1234");
        Usuario us4 = new Usuario(4, "Jose", "Garcia", "ejemplo4@.com", "1234");
        Usuario us5 = new Usuario(5, "Ana", "Martinez", "ejemplo5@.com", "1234");
        Usuario us6 = new Usuario(6, "Luis", "Rodriguez", "ejemplo6@.com", "1234");
        Usuario us7 = new Usuario(7, "Carlos", "Sanchez", "ejemplo7@.com", "1234");
        Usuario us8 = new Usuario(8, "Jorge", "Gomez", "ejemplo8@.com", "1234");
        // UsuarioDAOMySQL dao = new UsuarioDAOMySQL();
        /*
         * dao.create(us1);
         * //dao.create(us2);
         * 
         * Usuario ob = dao.read("2");
         * System.out.println(ob.toString());
         * 
         * System.out.println("Lista de usuarios: ");
         * 
         * usuarios = dao.readAll();
         * for (Usuario us : usuarios) {
         * System.out.println(us.toString());
         * System.out.println();
         * }
         */
        UsuarioDAOArchivo daoArchivo = new UsuarioDAOArchivo("Usuarios.txt");
        ArrayList<Usuario> usuariosArchivo = new ArrayList<>();
        usuariosArchivo = daoArchivo.readAll();
        /*
         * usuariosArchivo.add(us3);
         * usuariosArchivo.add(us1);
         * usuariosArchivo.add(us2);
         * usuariosArchivo.add(us4);
         * usuariosArchivo.add(us5);
         * usuariosArchivo.add(us6);
         * 
         * for (Usuario us : usuariosArchivo) {
         * daoArchivo.create(us);
         * }
         */

        System.out.println("\nLista de usuarios antes de eliminar 2: ");
        for (Usuario us : usuariosArchivo) {
            System.out.println(us.toString());
        }

        System.out.println("Borrar todos los usuarios");
        daoArchivo.borrarContenido();
        UsuarioDAOArchivo lista = new UsuarioDAOArchivo(usuariosArchivo);
        usuariosArchivo = lista.borrar(5);

        System.out.println("\nLista de usuarios después de eliminar 2: ");
        for (Usuario us : usuariosArchivo) {
            System.out.println(us.toString());
        }

        System.out.println("Número de elementos: " + usuariosArchivo.size());

        // Ahora inserta los usuarios en la lista original
        UsuarioDAOArchivo listaOriginal = new UsuarioDAOArchivo("Usuarios.txt");
        for (Usuario us : usuariosArchivo) {
            listaOriginal.create(us);
        }

        System.out.println("\nLista de usuarios después de insertar de nuevo: ");
        usuariosArchivo = listaOriginal.readAll();
        for (Usuario us : usuariosArchivo) {
            System.out.println(us.toString());
        }

    }
}
