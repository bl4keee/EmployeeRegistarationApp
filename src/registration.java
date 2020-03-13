import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Panel;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.mysql.cj.xdevapi.Statement;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class registration extends JFrame {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtmobile;
	private JTextField txtdepartment;
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException {	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registration frame = new registration();
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
	public registration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmployeeRegistration = new JLabel("Employee Registration");
		lblEmployeeRegistration.setBounds(0, 11, 744, 29);
		lblEmployeeRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeRegistration.setFont(new Font("Arial", Font.BOLD, 24));
		contentPane.add(lblEmployeeRegistration);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 102, 297, 211);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Name");
		lblNewLabel.setBounds(23, 44, 92, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mobile");
		lblNewLabel_1.setBounds(23, 82, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Department");
		lblNewLabel_2.setBounds(21, 119, 69, 14);
		panel.add(lblNewLabel_2);
		
		txtname = new JTextField();
		txtname.setBounds(125, 41, 154, 20);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtmobile = new JTextField();
		txtmobile.setBounds(125, 79, 154, 20);
		panel.add(txtmobile);
		txtmobile.setColumns(10);
		
		txtdepartment = new JTextField();
		txtdepartment.setBounds(125, 116, 154, 20);
		panel.add(txtdepartment);
		txtdepartment.setColumns(10);

		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			
			
			
			
			
			
			
			public void actionPerformed(ActionEvent arg0) {
				String name = txtname.getText();
				String mobile = txtmobile.getText(); //mobile in String
				String department = txtdepartment.getText();
				
				Connection myConn = null;
				PreparedStatement myStmt = null;
				
				String dbUrl = "jdbc:mysql://localhost:3306/linda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				String user = "student";
				String pass = "student";
						
					try {
						myConn = DriverManager.getConnection(dbUrl, user, pass);
						System.out.println("Database connection successfull!");
						myStmt = myConn.prepareStatement("insert into record(name,mobile,department)values(?,?,?)");
						myStmt.setString(1, name);
						myStmt.setString(2, mobile);
						myStmt.setString(3, department);
						myStmt.executeUpdate();
						JOptionPane.showMessageDialog(panel, "Record added!");
						table_update();
						
						txtname.setText("");
						txtmobile.setText("");
						txtdepartment.setText("");
						txtname.requestFocus();
						
						
						} catch (Exception exc) {
							exc.printStackTrace();
						} finally {
							if (myConn != null) {
								try {
									myConn.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							if (myStmt != null) {
								try {
									myStmt.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
						}
			}
		});
		
		btnNewButton.setBounds(10, 165, 80, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				
				
				//String name = txtname.getText();
				//String mobile = txtmobile.getText(); //mobile in String
				//String department = txtdepartment.getText();
				
				Connection myConn = null;
				PreparedStatement myStmt = null;
				
				String dbUrl = "jdbc:mysql://localhost:3306/linda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				String user = "student";
				String pass = "student";
						
					try {
						int id = Integer.parseInt(Df.getValueAt(selectedIndex, 0).toString());
						String name = txtname.getText();
						String mobile = txtmobile.getText(); //mobile in String
						String department = txtdepartment.getText();
						
						
						
						myConn = DriverManager.getConnection(dbUrl, user, pass);
						System.out.println("Database connection successfull!");
						myStmt = myConn.prepareStatement("update record set name=?,mobile=?,department=? where id=?");
						myStmt.setString(1, name);
						myStmt.setString(2, mobile);
						myStmt.setString(3, department);
						myStmt.setInt(4, id);
						myStmt.executeUpdate();
						JOptionPane.showMessageDialog(panel, "Record Updated!");
						table_update();
						
						txtname.setText("");
						txtmobile.setText("");
						txtdepartment.setText("");
						txtname.requestFocus();
						
						
						} catch (Exception exc) {
							exc.printStackTrace();
						} finally {
							if (myConn != null) {
								try {
									myConn.close();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
							if (myStmt != null) {
								try {
									myStmt.close();
								} catch (SQLException e2) {
									e2.printStackTrace();
								}
							}
						}
				
				
			}
		});
		btnNewButton_1.setBounds(100, 165, 86, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				

				
				//String name = txtname.getText();
				//String mobile = txtmobile.getText(); //mobile in String
				//String department = txtdepartment.getText();
				
				Connection myConn = null;
				PreparedStatement myStmt = null;
				
				String dbUrl = "jdbc:mysql://localhost:3306/linda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				String user = "student";
				String pass = "student";
						
					try {
						int id = Integer.parseInt(Df.getValueAt(selectedIndex, 0).toString());
						
						int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to delete the record?", "Warning", JOptionPane.YES_NO_OPTION);
						
						if (dialogResult == JOptionPane.YES_OPTION) {
							myConn = DriverManager.getConnection(dbUrl, user, pass);
							System.out.println("Database connection successfull!");
							myStmt = myConn.prepareStatement("delete from record where id=?");
							myStmt.setInt(1, id);
							
							myStmt.executeUpdate();
							JOptionPane.showMessageDialog(panel, "Record deleted!");
							table_update();
							
							txtname.setText("");
							txtmobile.setText("");
							txtdepartment.setText("");
							txtname.requestFocus();
						}
						
						} catch (Exception exc) {
							exc.printStackTrace();
						} finally {
							if (myConn != null) {
								try {
									myConn.close();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
							if (myStmt != null) {
								try {
									myStmt.close();
								} catch (SQLException e2) {
									e2.printStackTrace();
								}
							}
						}
			}
		});
		btnNewButton_2.setBounds(196, 165, 83, 23);
		panel.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel Df = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				txtname.setText(Df.getValueAt(selectedIndex, 1).toString());
				txtmobile.setText(Df.getValueAt(selectedIndex, 2).toString());
				txtdepartment.setText(Df.getValueAt(selectedIndex, 3).toString());
				
			}
		});
		scrollPane.setBounds(317, 107, 427, 300);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Name", "Mobile", "Department"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
	setVisible(true);
	setLocationRelativeTo(null); // generates in the center of a screen
	table_update();
	
	}

	private void table_update() {
		int c;
		String name = txtname.getText();
		String mobile = txtmobile.getText(); //mobile in String
		String department = txtdepartment.getText();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		ResultSetMetaData myRsmeta;
		
		String dbUrl = "jdbc:mysql://localhost:3306/linda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "student";
		String pass = "student";
				
			try {
				myConn = DriverManager.getConnection(dbUrl, user, pass);
				myStmt = myConn.prepareStatement("select * from record");
				myRs = myStmt.executeQuery();
				myRsmeta = myRs.getMetaData();
				c = myRsmeta.getColumnCount();
				
				DefaultTableModel Df = (DefaultTableModel)table.getModel();
				Df.setRowCount(0);
				
				while (myRs.next()) {
					Vector v2 = new Vector();
					
					for (int i = 1; i <= c; i++) {
						v2.add(myRs.getString("id"));
						v2.add(myRs.getString("name"));
						v2.add(myRs.getString("mobile"));
						v2.add(myRs.getString("department"));
					}
					
					Df.addRow(v2);
				}
				
				} catch (Exception exc) {
					exc.printStackTrace();
				} finally {
					if (myConn != null) {
						try {
							myConn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (myStmt != null) {
						try {
							myStmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (myRs != null) {
						try {
							myRs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
	}
	
}
