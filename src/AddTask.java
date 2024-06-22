import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AddTask extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField title;
	private JTextField description;

	/**
	 * Create the frame.
	 */
	public AddTask() {
		setTitle("Task Manager- Add Task");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("task.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Task");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(159, 10, 80, 33);
		contentPane.add(lblNewLabel);
		
		title = new JTextField();
		title.setBounds(139, 53, 139, 19);
		contentPane.add(title);
		title.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Title*");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(43, 59, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		description = new JTextField();
		description.setBounds(139, 97, 139, 19);
		contentPane.add(description);
		description.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Description*");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(43, 98, 86, 13);
		contentPane.add(lblNewLabel_2);
		
		JButton confrimBtn = new JButton("Confirm");
		confrimBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		confrimBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			        String taskName=title.getText().trim();
			        String taskDescription=description.getText().trim();
			       
			        if(taskName.isEmpty()||taskDescription.isEmpty()){
			            JOptionPane.showMessageDialog(rootPane, "All fields are neccessary");
			        }else{
			            try {
			                 FileWriter fw=new FileWriter("TaskDetails.txt",true);
			                BufferedWriter bw=new BufferedWriter(fw);
			                
			                 bw.write(taskName+","+taskDescription);
			                 bw.write("\n");
			              
			                   bw.close();
			                   fw.close();
			                   JOptionPane.showMessageDialog(rootPane, "Item Added");
			                    dispose();
			                   java.awt.EventQueue.invokeLater(new Runnable(){
			                   public void run(){
			                       new Dashboard().setVisible(true);
			                   }});
			            } catch (IOException ex) {
			               JOptionPane.showMessageDialog(rootPane, ex.getMessage());
			            }
			        }

			}
		});
		confrimBtn.setBounds(139, 145, 139, 33);
		contentPane.add(confrimBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		cancelBtn.setBounds(139, 188, 139, 33);
		contentPane.add(cancelBtn);
	}
}
