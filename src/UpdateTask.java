import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class UpdateTask extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField title;
	private JTextField description;

	
	/**
	 * Create the frame.
	 */
	public UpdateTask() {
		setTitle("Task Manager- Update Task");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("task.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUpdateTask = new JLabel("Update Task");
		lblUpdateTask.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUpdateTask.setBounds(151, 10, 104, 33);
		contentPane.add(lblUpdateTask);
		
		title = new JTextField();
		title.setColumns(10);
		title.setBounds(128, 62, 139, 19);
		contentPane.add(title);
		
		description = new JTextField();
		description.setColumns(10);
		description.setBounds(128, 105, 139, 19);
		contentPane.add(description);
		
		JLabel lblNewLabel_1 = new JLabel("Title*");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(37, 63, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Description*");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(37, 106, 86, 13);
		contentPane.add(lblNewLabel_2);
		
		JButton updateBtn = new JButton("Confirm");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String taskName=title.getText().trim();
				String taskDescription=description.getText().trim();
				String line=taskName+","+taskDescription;
		        
				if(taskName.isEmpty()||taskDescription.isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "All fields are necessary");
				}else {
					 FileWriter writer=null;
				        BufferedWriter bw=null;
				        try {
				            writer=new FileWriter("TaskDetails.txt");
				             bw=new BufferedWriter(writer);
				             
				             for(int i=0;i<GlobalVariables.tasks.size();i++){
				                 if(i==GlobalVariables.row){
				                     GlobalVariables.tasks.set(i, line);
				                 }
				                 bw.write(GlobalVariables.tasks.get(i));
				                 bw.write("\n");
				             }
				             JOptionPane.showMessageDialog(rootPane, "Task Updated");
				             
				        } catch (IOException ex) {
				           JOptionPane.showMessageDialog(rootPane, ex.getMessage());
				        }finally{
				            try {
				                bw.close();
				                writer.close();
				            } catch (IOException ex) {
				               JOptionPane.showMessageDialog(rootPane, ex.getMessage());
				            }
				        }
				 
				     dispose();
				       java.awt.EventQueue.invokeLater(new Runnable(){
				                    public void run(){
				                        new Dashboard().setVisible(true);
				                    }
				                    });
						
					}
				}
		       
		});
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		updateBtn.setBounds(128, 134, 139, 33);
		contentPane.add(updateBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
			}
		});
		cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		cancelBtn.setBounds(128, 191, 139, 33);
		contentPane.add(cancelBtn);
		
		showDetails();
	}
	
	private void showDetails() {
		
		title.setText(GlobalVariables.taskName);
		description.setText(GlobalVariables.taskDescription);
		
		
		
	}

}
