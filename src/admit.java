import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
public class admit extends JFrame implements Runnable{
 int hour,seconds,minutes;
	private JPanel contentPane;
	private JTextField pid;
	private JTextField pn;
	private JTextField pd;
	private JTextField pad;
	private JTextField pat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admit frame = new admit();
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
	public admit() {
		Thread t = new Thread(this);
		t.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADMIT PATIENT");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(132, 23, 147, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(59, 62, 49, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Patient's Name:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(59, 95, 114, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patient info = new patient();
				patient.main(null);
			}}
		);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(10, 229, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("LOGOUT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frmLoginPage = new JFrame("LOGOUT");
				if(JOptionPane.showConfirmDialog(frmLoginPage, "Confirm if you want to logout","Login Systems",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(318, 231, 108, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Disease:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(59, 120, 82, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Admit Date:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(59, 152, 114, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Admit Time:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(59, 185, 114, 14);
		contentPane.add(lblNewLabel_5);
		
		pid = new JTextField();
		pid.setBounds(183, 59, 96, 20);
		contentPane.add(pid);
		pid.setColumns(10);
		
		pn = new JTextField();
		pn.setBounds(183, 92, 96, 20);
		contentPane.add(pn);
		pn.setColumns(10);
		
		pd = new JTextField();
		pd.setBounds(183, 117, 96, 20);
		contentPane.add(pd);
		pd.setColumns(10);
		
		pad = new JTextField();
		pad.setBounds(183, 149, 96, 20);
		contentPane.add(pad);
		pad.setColumns(10);
		
		pat = new JTextField();
		pat.setBounds(183, 182, 96, 20);
		contentPane.add(pat);
		pat.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("ADMIT");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
					String sql ="insert into patient_record values(?,?,?,?,?)";
					PreparedStatement ptst = conn.prepareStatement(sql);
					ptst.setString(1, pid.getText());
					ptst.setString(2, pn.getText());
					ptst.setString(3, pd.getText());
					ptst.setString(4, pad.getText());
					ptst.setString(5, pat.getText());
					ptst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
					conn.close();
					pid.setText("");pn.setText("");pd.setText("");pad.setText("");
					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(223, 207, 89, 23);
		contentPane.add(btnNewButton_2);
	}
	void showDate() {
		Date d = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd-mm-yyyy");
		pad.setText(sd.format(d));
}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			Calendar cal = Calendar.getInstance();
			hour=cal.get(Calendar.HOUR_OF_DAY);
			minutes=cal.get(Calendar.MINUTE);
			seconds=cal.get(Calendar.SECOND);
			SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm:ss:aa");
			Date dat = cal.getTime();
			String time24=sdf24.format(dat);
			pat.setText(time24);
		}
		
	}
}
