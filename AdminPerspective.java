package gui;

import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminPerspective extends JFrame {
	JTable table;
	JComboBox<String> comboBox;
    DefaultTableModel tableModel;
    
	public AdminPerspective() {
		setTitle("Evenimente create");
        setMinimumSize(new Dimension(700, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        String[] columns = {"Nume Client","Data Eveniment","Tip Eveniment","Sala","Nr invitati"};
        List <String[]> data = new ArrayList<>();
        String[][] dataArray = {} ;
		Connection conn = null;
		
		try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/events","root","");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM event");
		
		while (rs.next()) {
		    String[] row = new String[5];
		    row[0] = rs.getString("client_name");
		    row[1] = rs.getString("date");
		    row[2] = rs.getString("type");
		    row[3] = rs.getString("room_name");
		    row[4] = rs.getString("invites");
		    data.add(row);
		}
		
		 dataArray = data.toArray(new String[data.size()][]);		
		 
		 conn.close();
		 rs.close();
		 stmt.close();
		}
		catch (ClassNotFoundException e) {
	    	e.printStackTrace();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    		}catch (Exception e) {
	    			e.printStackTrace();
	    			}
		
		tableModel = new DefaultTableModel(dataArray, columns);
		table = new JTable(tableModel);
		
		JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setForeground(new Color(153,0,153));
        header.setBackground(Color.white);
        
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setForeground(Color.BLACK);
        table.setBackground(Color.WHITE);
        
		table.setEnabled(false);
		
		comboBox = new JComboBox<>(new String[] {"Toate Evenimentele", "Nunta", "Botez","Majorat"});
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterTable();
            }
        });
		comboBox.setBackground(new Color(255,153,255));
		comboBox.setForeground(Color.black);
		comboBox.setFont(new Font("Calibri", Font.BOLD, 16));
		
		add(new JScrollPane(table), BorderLayout.CENTER);    
		add(comboBox, BorderLayout.NORTH);
		setBackground(new Color(153,0,153));
	}
	
	private void filterTable() {
		String selectedItem = (String) comboBox.getSelectedItem();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
        if (!selectedItem.equals("Toate Evenimentele")) {
            sorter.setRowFilter(RowFilter.regexFilter(selectedItem.toLowerCase(), 2));
        }
        table.setRowSorter(sorter);
	}
	
	public static void main(String[] args) {
		AdminPerspective ap = new AdminPerspective();
		ap.pack();
		ap.setVisible(true);
	}

}
