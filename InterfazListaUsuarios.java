import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class InterfazListaUsuarios extends JFrame {
    DefaultTableModel modelo = new DefaultTableModel();
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	InterfazListaUsuarios frame = new InterfazListaUsuarios();
                    frame.setVisible(true);
                    frame.setSize(1000, 600);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public InterfazListaUsuarios() throws SQLException {
        JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                
			}
		});
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        Color colorBorde = Color.decode("#8c8c8c");
        scrollPane.setBorder(new LineBorder(colorBorde, 2));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addGap(65)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                                .addGap(51)));
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addGap(105)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                                .addGap(29)));

        modelo.addColumn("id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Correo");
        modelo.addColumn("Contraseña");
        table = new JTable(modelo);
        table.setModel(new DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null},
                        { null, null, null, null, null},
                        { null, null, null, null, null},
                        { null, null, null, null, null},
                },
                new String[] {
                        "id", "Nombre", "Apellido", "Correo", "Contraseña"
                }));
        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);
        table.setEnabled(false);
        CargarUsuarios();
    }

    public void CargarUsuarios() throws SQLException {
        // Obtener los registros pero ahora en ArrayList
        UsuarioDAOMySQL dao = new UsuarioDAOMySQL();
        ArrayList<Usuario> usuarios = new ArrayList<>();
    
        usuarios = dao.readAll();
        Object datos[] = new Object[5];
        if (usuarios != null) {
            for (Usuario u : usuarios) {
                datos[0] = u.getId();
                datos[1] = u.getNombre();
                datos[2] = u.getApellido();
                datos[3] = u.getCorreo();
                datos[4] = u.getContrasena();
                modelo.addRow(datos);
            }
        }
        table.setModel(modelo);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(30);
        table.getColumnModel().getColumn(3).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(30);
    }
    
}
