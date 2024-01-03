package javaCrud;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;



public class javaCrud {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javaCrud window = new javaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public javaCrud() {
		initialize();
		connect();
		table_load();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
public void connect() {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost/javacrud","root","");
		
	}
	catch(ClassNotFoundException ex)
	{
		
	}
	catch(SQLException ex) {
		
	}
}
public void table_load() {
	try {
		pst=con.prepareStatement("select* from book");
		rs=pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
		
		}
	catch (SQLException e) {
		e.printStackTrace();
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("scrollbar"));
		frame.getContentPane().setForeground(Color.ORANGE);
		frame.setBounds(100, 100, 824, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("KİTAPÇI");
		lblNewLabel.setBounds(306, 10, 194, 53);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(44, 122, 328, 214);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Kitap Kaydet", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Kitap Adı");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(21, 50, 108, 39);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Kat");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1_1.setBounds(21, 99, 88, 39);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Fiyat");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1_2.setBounds(21, 148, 108, 39);
		panel.add(lblNewLabel_1_2);
		
		txtbname = new JTextField();
		txtbname.setBounds(177, 62, 130, 28);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(177, 111, 130, 28);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(177, 160, 130, 27);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("kaydet");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				bname=txtbname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				
				try {
					pst=con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					pst.setString(1,bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record addedddd!!!!");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
					
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(44, 346, 94, 38);
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.setForeground(SystemColor.textHighlight);
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 19));
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("temizle");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
				
				
			}
		});
		btnNewButton_1.setBounds(148, 347, 103, 38);
		btnNewButton_1.setForeground(SystemColor.textHighlight);
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("çıkış");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				System.exit(0);
			}
		});
		btnNewButton_2.setBounds(262, 346, 94, 38);
		btnNewButton_2.setForeground(SystemColor.textHighlight);
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 19));
		btnNewButton_2.setBackground(SystemColor.inactiveCaption);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(474, 122, 295, 262);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.PINK);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ara", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(44, 413, 328, 62);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Kitap ID");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1_1_1.setBounds(10, 14, 88, 39);
		panel_1.add(lblNewLabel_1_1_1);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
                
                try {
                     
                       String id = txtbid.getText();

                           pst = con.prepareStatement("select name,edition,price from book where id = ?");
                           pst.setString(1, id);
                           ResultSet rs = pst.executeQuery();

                       if(rs.next()==true)
                       {
                         
                           String name = rs.getString(1);
                           String edition = rs.getString(2);
                           String price = rs.getString(3);
                           
                           txtbname.setText(name);
                           txtedition.setText(edition);
                           txtprice.setText(price);
   
                       }   
                       else
                       {
                           txtbname.setText("");
                           txtedition.setText("");
                           txtprice.setText("");
                            
                       }
                   } 
               
                catch (SQLException ex) {
                      
                   }
           }
		});
		txtbid.setColumns(10);
		txtbid.setBounds(108, 21, 128, 30);
		panel_1.add(txtbid);
		
		JButton btnNewButton_1_1 = new JButton("güncelle");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
                String bname,edition,price,bid;
                
                
                bname = txtbname.getText();
                edition = txtedition.getText();
                price = txtprice.getText();
                bid  = txtbid.getText();
                
                 try {
                        pst = con.prepareStatement("update book set name= ?,edition=?,price=? where id =?");
                        pst.setString(1, bname);
                        pst.setString(2, edition);
                        pst.setString(3, price);
                        pst.setString(4, bid);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Record Update!!!!!");
                        table_load();
                       
                        txtbname.setText("");
                        txtedition.setText("");
                        txtprice.setText("");
                        txtbname.requestFocus();
                    }

                    catch (SQLException e1) {
                        
                        e1.printStackTrace();
                    }
				
				
				
				
			}
		});
		btnNewButton_1_1.setForeground(Color.BLUE);
		btnNewButton_1_1.setFont(new Font("Arial", Font.BOLD, 17));
		btnNewButton_1_1.setBackground(SystemColor.inactiveCaption);
		btnNewButton_1_1.setBounds(497, 422, 103, 53);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("sil");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		           String bid;
		           bid  = txtbid.getText();
		           
		            try {
		                   pst = con.prepareStatement("delete from book where id =?");
		           
		                   pst.setString(1, bid);
		                   pst.executeUpdate();
		                   JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
		                   table_load();
		                  
		                   txtbname.setText("");
		                   txtedition.setText("");
		                   txtprice.setText("");
		                   txtbname.requestFocus();
		               }

		               catch (SQLException e1) {
		                   
		                   e1.printStackTrace();
		               }
				
				
			}
		});
		btnNewButton_1_2.setForeground(Color.RED);
		btnNewButton_1_2.setFont(new Font("Arial", Font.BOLD, 19));
		btnNewButton_1_2.setBackground(SystemColor.inactiveCaption);
		btnNewButton_1_2.setBounds(621, 422, 94, 53);
		frame.getContentPane().add(btnNewButton_1_2);
	}
}
