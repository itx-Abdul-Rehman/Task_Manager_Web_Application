import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class Dashboard extends JFrame {
	 DefaultTableModel model;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable taskDetails;
	private JTextField txtUsername;


	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public Dashboard() {
		setTitle("Task Manager");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("task.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 330);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 50, 450, 233);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		taskDetails = new JTable();
		scrollPane.setViewportView(taskDetails);
		taskDetails.setRowSelectionAllowed(false);
		taskDetails.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sr No", "Title", "Description"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		taskDetails.getColumnModel().getColumn(0).setPreferredWidth(15);
		taskDetails.getColumnModel().getColumn(0).setMaxWidth(2147483645);
		taskDetails.getColumnModel().getColumn(1).setPreferredWidth(15);
		taskShow();
		
		JButton addTask = new JButton("Add Task");
		addTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.EventQueue.invokeLater(new Runnable() {
		            public void run() {
		              new AddTask().setVisible(true);
		            }
		        });
			}
		});
		addTask.setBounds(559, 36, 109, 21);
		contentPane.add(addTask);
		
		JButton btnNewButton = new JButton("Delete Task");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int row=taskDetails.getSelectedRow();
			        if(row==-1){
			            JOptionPane.showMessageDialog(rootPane, "Task no selected");
			        }else{
			            GlobalVariables.tasks.remove(row);
			            model.removeRow(row);
			             FileWriter fw=null;
			              BufferedWriter bw=null;
			                
			           try {
			               fw=new FileWriter("TaskDetails.txt");
			               bw=new BufferedWriter(fw);
			               
			               for(int i=0;i<GlobalVariables.tasks.size();i++){
			                 
			                   bw.write(GlobalVariables.tasks.get(i));
			                   bw.write("\n");
			               }
			           } catch (IOException ex) {
			               JOptionPane.showMessageDialog(rootPane, ex.getMessage());
			           }finally{
			                try {
			                    bw.close();
			                    fw.close();
			                } catch (IOException ex) {
			                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
			                }
			           }
			               
			            JOptionPane.showMessageDialog(rootPane, "Task Deleted");
			        }

			}
		});
		btnNewButton.setBounds(559, 67, 109, 21);
		contentPane.add(btnNewButton);
		
		JButton btnUpdateTask = new JButton("Update Task");
		btnUpdateTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=taskDetails.getSelectedRow();
		        if(row==-1){
		            JOptionPane.showMessageDialog(rootPane,"Task not selected");
		        }else{
		           GlobalVariables.row=taskDetails.getSelectedRow();
		           GlobalVariables.taskName=(String)(model.getValueAt(row, 1));
		            GlobalVariables.taskDescription=(String)(model.getValueAt(row, 2));
		            
		          java.awt.EventQueue.invokeLater(new Runnable(){
		                    @Override
		                    public void run(){
		                        new UpdateTask().setVisible(true);
		                    }
		                    });
		      
		        }
		
			}
		});
		btnUpdateTask.setBounds(559, 98, 109, 21);
		contentPane.add(btnUpdateTask);
		
		JLabel lblNewLabel = new JLabel("Task Manager");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(236, 10, 179, 30);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.textInactiveText);
		separator.setBounds(559, 239, 109, 2);
		contentPane.add(separator);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
                java.awt.EventQueue.invokeLater(new Runnable(){
                public void run(){
                    new Login().setVisible(true);
                }});
			}
		});
		btnLogOut.setBounds(559, 251, 109, 21);
		contentPane.add(btnLogOut);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUsername.setText("Username");
		txtUsername.setEditable(false);
		txtUsername.setBounds(33, 10, 165, 30);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		 txtUsername.setText(GlobalVariables.username);
		
	}
	
	  private void taskShow(){
		   
	        GlobalVariables. tasks.clear();
	        FileReader reader=null;
	        BufferedReader br=null;
	        int count=1;
	         model = (DefaultTableModel) taskDetails.getModel();
	         try {
	             reader=new FileReader("TaskDetails.txt");
	             br=new BufferedReader(reader);
	             String line=null;
	            try {
	                while((line=br.readLine())!=null){
	                  String[] array=line.split(",");
	                  int taskNo=count++;
	                  String taskName=array[0];
	                  String taskDescription=array[1];
	                  
	                  GlobalVariables.tasks.add(line);
	                  
	                  Object[] data={taskNo,taskName,taskDescription};
	                   model.addRow(data);
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
