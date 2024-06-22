import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailAddress;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Task Manager");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("task.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Log in");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(463, 10, 120, 35);
		contentPane.add(lblNewLabel);
		
		JButton signUpBtn = new JButton("Register");
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
                 java.awt.EventQueue.invokeLater(new Runnable(){
                 public void run(){
                     new Signup().setVisible(true);
                 }});
			}
		});
		signUpBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		signUpBtn.setBounds(648, 10, 109, 33);
		contentPane.add(signUpBtn);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userEmail=emailAddress.getText().toString();
				String userPassword=password.getText().toString();
				
			        FileReader reader=null;
			        BufferedReader br=null;
			        boolean isLogin=false;
			     
			      if(userEmail.isEmpty()||userPassword.isEmpty()) {
			    	  JOptionPane.showMessageDialog(rootPane,"All fields are neccessary");
			      }else {
			    	  try {
				             reader=new FileReader("User.txt");
				             br=new BufferedReader(reader);
				             String line=null;
				            try {
				                while((line=br.readLine())!=null){
				                  String[] array=line.split(",");
				                  String name=array[0];
				                  String email=array[1];
				                  String password=array[2];
				                  
				                  if(userEmail.equals(email)||userPassword.equals(password)) {
				                	  isLogin=true;
				                	  GlobalVariables.username=name;
				                	  dispose();
					                   java.awt.EventQueue.invokeLater(new Runnable(){
					                   public void run(){
					                       new Dashboard().setVisible(true);
					                   }});
				                	  
				                  }
				               
				                }
				                
				                if(isLogin==false) {
				                	JOptionPane.showMessageDialog(rootPane, "Incorrect Credentials");
				                }
				              
				            } catch (IOException ex) {
				               JOptionPane.showMessageDialog(rootPane, ex.getMessage());
				            }
				             
				         } catch (FileNotFoundException ex) {
				           JOptionPane.showMessageDialog(rootPane, ex.getMessage());
				         }finally{
				            try {
				                br.close();
				                reader.close();
				            } catch (IOException ex) {
				                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
				            }
				             
				         }
			      }
			        
			         
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogIn.setBounds(557, 174, 109, 33);
		contentPane.add(btnLogIn);
		
		emailAddress = new JTextField();
		emailAddress.setColumns(10);
		emailAddress.setBounds(508, 91, 158, 19);
		contentPane.add(emailAddress);
		
		JLabel lblEmailAddress = new JLabel("Email Address*");
		lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmailAddress.setBounds(384, 92, 99, 13);
		contentPane.add(lblEmailAddress);
		
		password = new JPasswordField();
		password.setBounds(508, 130, 158, 19);
		contentPane.add(password);
		
		JLabel lblPassword = new JLabel("Password*");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(384, 131, 89, 13);
		contentPane.add(lblPassword);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.textInactiveText);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(355, 25, 1, 228);
		contentPane.add(separator);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Nobita\\Downloads\\pngwing.com (1).png"));
		lblNewLabel_2.setBounds(47, 10, 248, 270);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setBounds(47, 10, 248, 270);
		contentPane.add(lblNewLabel_2_1);
	}

}
