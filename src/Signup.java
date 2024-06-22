import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Signup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField emailAddress;
	private JPasswordField password;
	private JPasswordField confirmPassword;


	/**
	 * Create the frame.
	 */
	public Signup() {
		setTitle("Task Manager");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("task.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(397, 0, 423, 315);
		contentPane.add(panel);
		panel.setLayout(null);
		
		username = new JTextField();
		username.setBounds(192, 77, 158, 19);
		panel.add(username);
		username.setColumns(10);
		
		emailAddress = new JTextField();
		emailAddress.setColumns(10);
		emailAddress.setBounds(192, 106, 158, 19);
		panel.add(emailAddress);
		
		JLabel lblNewLabel = new JLabel("Username*");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(66, 83, 89, 13);
		panel.add(lblNewLabel);
		
		JLabel lblEmailAddress = new JLabel("Email Address*");
		lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmailAddress.setBounds(66, 112, 99, 13);
		panel.add(lblEmailAddress);
		
		JLabel lblPassword = new JLabel("Password*");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(66, 145, 89, 13);
		panel.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password*");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConfirmPassword.setBounds(66, 174, 130, 13);
		panel.add(lblConfirmPassword);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName=username.getText().toString();
				String userEmail=emailAddress.getText().toString();
				String userPassword=password.getText().toString();
				String userConfirmPassword=confirmPassword.getText().toString();
				
				if(userName.isEmpty()|| userEmail.isEmpty()||userPassword.isEmpty()||userConfirmPassword.isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "All fields are neccessary");
				}else {
					
					 if(!(userPassword.equals(userConfirmPassword))) {
						 JOptionPane.showMessageDialog(rootPane, "Password does not match");
					 }else {
						 FileWriter fw=null;
						 BufferedWriter bw=null;
						 try {
			                  fw=new FileWriter("User.txt",true);
			                  bw=new BufferedWriter(fw);
			                
			                 bw.write(userName+","+userEmail+","+userPassword);
			                 bw.write("\n");
			              
			                   bw.close();
			                   fw.close();
			                   JOptionPane.showMessageDialog(rootPane, "Registered Successfully");
			                    dispose();
			                   java.awt.EventQueue.invokeLater(new Runnable(){
			                   public void run(){
			                       new Login().setVisible(true);
			                   }});
			            } catch (IOException ex) {
			               JOptionPane.showMessageDialog(rootPane, ex.getMessage());
			            }
					 }
					
					
				}
				
			}
		});
		registerBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		registerBtn.setBounds(211, 211, 139, 33);
		panel.add(registerBtn);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
                 java.awt.EventQueue.invokeLater(new Runnable(){
                 public void run(){
                     new Login().setVisible(true);
                 }});
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogIn.setBounds(304, 10, 109, 33);
		panel.add(btnLogIn);
		
		JLabel lblNewLabel_1 = new JLabel("Registration");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(79, 22, 171, 33);
		panel.add(lblNewLabel_1);
		
		password = new JPasswordField();
		password.setBounds(192, 139, 158, 19);
		panel.add(password);
		
		confirmPassword = new JPasswordField();
		confirmPassword.setBounds(192, 168, 158, 19);
		panel.add(confirmPassword);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(SystemColor.textInactiveText);
		separator.setBounds(10, 30, 1, 228);
		panel.add(separator);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Nobita\\Downloads\\pngwing.com (1).png"));
		lblNewLabel_2.setBounds(64, 25, 248, 270);
		contentPane.add(lblNewLabel_2);
	}
}
