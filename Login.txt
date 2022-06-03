import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.xdevapi.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.sql.*;
import java.awt.Font;
import javax.swing.JPasswordField;
public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(47, 79, 73, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(47, 137, 73, 14);
		contentPane.add(lblNewLabel_1);
		
		textUsername = new JTextField();
		textUsername.setBounds(158, 76, 96, 20);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String un =textUsername.getText();
				String p = password.getText();
				System.out.println(un);
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hospital","root","");
					java.sql.Statement st = conn.createStatement();
					String sql = "select * from user_login";
					ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);
					while(rs.next()) {
						String username=rs.getString("username");
						String password=rs.getString("password");
						if(un.equals(username) && p.equals(password)){
							welcome info = new welcome();
							welcome.main(null);
						}
						else {
							JOptionPane.showMessageDialog(null, "Wrong username and password");
						}
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Connection error!!!");
				}
			}
		});
		btnNewButton.setBounds(282, 208, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Login System");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(139, 28, 118, 14);
		contentPane.add(lblNewLabel_2);
		
		password = new JPasswordField();
		password.setBounds(158, 136, 96, 20);
		contentPane.add(password);
	}
}
