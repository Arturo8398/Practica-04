import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Insertar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textid;
	private JTextField textNom;
	private JTextField textAp;
	private JTextField textCorreo;
	private JTextField textCon;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Insertar frame = new Insertar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Insertar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Insertar a un usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(134, 11, 168, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("id:");
		lblNewLabel_1.setBounds(10, 63, 69, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre: ");
		lblNewLabel_1_1.setBounds(10, 87, 69, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Apellido:");
		lblNewLabel_1_1_1.setBounds(10, 112, 89, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Correo: ");
		lblNewLabel_1_1_2.setBounds(10, 137, 89, 14);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Contraseña: ");
		lblNewLabel_1_1_3.setBounds(10, 162, 102, 14);
		contentPane.add(lblNewLabel_1_1_3);
		
		textid = new JTextField();
		textid.setBounds(134, 60, 127, 20);
		contentPane.add(textid);
		textid.setColumns(10);
		
		textNom = new JTextField();
		textNom.setColumns(10);
		textNom.setBounds(134, 84, 127, 20);
		contentPane.add(textNom);
		
		textAp = new JTextField();
		textAp.setColumns(10);
		textAp.setBounds(134, 109, 127, 20);
		contentPane.add(textAp);
		
		textCorreo = new JTextField();
		textCorreo.setColumns(10);
		textCorreo.setBounds(134, 134, 127, 20);
		contentPane.add(textCorreo);
		
		textCon = new JTextField();
		textCon.setColumns(10);
		textCon.setBounds(134, 159, 127, 20);
		contentPane.add(textCon);
		
		JButton btnSQL = new JButton("MySQL");
		btnSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario u1;
				UsuarioDAOMySQL daoSQL = new UsuarioDAOMySQL();
				
				int id = Integer.parseInt(textid.getText());
				String nombre  = textNom.getText();
				String apellido = textAp.getText();
				String correo = textCorreo.getText();
				String pass = textCon.getText();
				
				u1 = new Usuario(id, nombre, apellido, correo, pass);
				try {
					daoSQL.create(u1);
				} catch (SQLException e1) {
					System.out.print("No se pudo agregar");
					e1.printStackTrace();
				}
				
				try {
					InterfazListaUsuarios n = new InterfazListaUsuarios();
					n.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSQL.setBounds(285, 83, 89, 23);
		contentPane.add(btnSQL);
		
		JButton btnTXT = new JButton("TXT");
		btnTXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario u1;
				UsuarioDAOArchivo daoA = new UsuarioDAOArchivo("Usuarios.txt");
				
				int id = Integer.parseInt(textid.getText());
				String nombre  = textNom.getText();
				String apellido = textAp.getText();
				String correo = textCorreo.getText();
				String pass = textCon.getText();
				
				u1 = new Usuario(id, nombre, apellido, correo, pass);
				try {
					daoA.create(u1);
				} catch (SQLException e1) {
					System.out.print("No se pudo agregar");
					e1.printStackTrace();
				}
				
				try {
					InterfazListaTXT n = new InterfazListaTXT();
					n.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnTXT.setBounds(285, 133, 89, 23);
		contentPane.add(btnTXT);
	}
}
