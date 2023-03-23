package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class CreateEvent extends JFrame {
	public CreateEvent(String nume) {
		JFrame frame = new JFrame("Creare eveniment");
        frame.setMinimumSize(new Dimension(700,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(153, 0, 153));
        
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        frame.setLayout(gridBag);

        // Create a label for the title
        JLabel titleLabel = new JLabel("Creare eveniment pentru " + nume);
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 24));
        titleLabel.setForeground(new Color(249,215,220));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gridBag.setConstraints(titleLabel, c);
        frame.add(titleLabel,c);
        
        JLabel textFieldLabel = new JLabel("Data evenimentului:");
        textFieldLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
        textFieldLabel.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        gridBag.setConstraints(textFieldLabel, c);
        frame.add(textFieldLabel,c);
        
        JTextField textField = new JTextField(10);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 0, 10);
        c.anchor = GridBagConstraints.WEST;
        gridBag.setConstraints(textField, c);
        frame.add(textField,c);
        
        
        JLabel comboBoxLabel = new JLabel("Tipul evenimentului:");
        comboBoxLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
        comboBoxLabel.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 10, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        gridBag.setConstraints(comboBoxLabel, c);
        frame.add(comboBoxLabel);
        
        String[] options = {"Nunta", "Botez", "Majorat"};
        JComboBox comboBox = new JComboBox(options);
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(10, 10, 0, 10);
        c.anchor = GridBagConstraints.WEST;
        gridBag.setConstraints(comboBox, c);
        frame.add(comboBox,c);
        
        JSpinner spinner = new JSpinner();
        c.gridx = 1;
        c.gridy = 3;
        spinner.setPreferredSize(new Dimension(70,20));
        frame.add(spinner, c);

        JLabel spinnerLabel = new JLabel("Numar invitati:");
        spinnerLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
        spinnerLabel.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_END;
        frame.add(spinnerLabel, c);
        
        JButton button = new JButton("Confirma");
        c.gridx = 1;
        c.gridy = 4;
        button.setBackground(Color.white);
        button.setForeground(new Color(153,0,153));
        c.anchor = GridBagConstraints.LINE_START;
        frame.add(button, c);
    
        
        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String data = textField.getText();
				String tip = (String)comboBox.getSelectedItem();
				tip = tip.toLowerCase();
				int invitati = (Integer)spinner.getValue();
				
				String room = "";
				if(invitati < 150)
					room = "Sala Bronze";
				else if(invitati >=150 && invitati <300)
					room = "Sala Silver";
				else if(invitati >=300 && invitati <= 450)
					room = "Sala Gold";
				else if(invitati > 450) {
					JOptionPane.showMessageDialog(frame, "Numarul de invitati e prea mare pentru restaurantul nostru, ne pare rau", "Problema", JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(frame, "La revedere, " + nume, "Iesire aplicatie", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
				}
				
				if(!room.equals("")) {
				java.sql.PreparedStatement pstmt = null;
				Connection conn = null;
				Map <String,String> taken = new HashMap<String,String>();
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/events","root","");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM event");
					
					String d,t;
					while(rs.next()) {
						d = rs.getString(3);
						t = rs.getString(7);
						taken.put(d, t);
					}
					int result = 0; 
					boolean reserved = false;
					for(Map.Entry<String, String> p : taken.entrySet()) {
						if(p.getKey().equals(data) && p.getValue().equals(room)) 
							reserved = true;
					}
		
					if(reserved) {
						result = JOptionPane.showConfirmDialog(null, "Sala este ocupata in data aleasa de tine! Doresti sa alegi alta data?", "Sala ocupata :(", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						//System.out.println("yes");
					} else {
						JOptionPane.showMessageDialog(frame, "Ne pare rau ca nu te-am putut ajuta", "Problema", JOptionPane.INFORMATION_MESSAGE);
						JOptionPane.showMessageDialog(frame, "La revedere, " + nume, "Iesire aplicatie", JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
					}	
					}
					else {
						//System.out.println("Felicitari se va crea evenimentul!");
						CreateEvent nextPage = new CreateEvent(nume,data,tip,invitati,room);
						nextPage.setVisible(true);
						frame.dispose();
					}
					
					rs.close();
					stmt.close();	
					conn.close();
					
				}catch (ClassNotFoundException ex) {
			    	ex.printStackTrace();
		    	}catch(SQLException ep) {
		    		ep.printStackTrace();
		    		}catch (Exception ec) {
		    			ec.printStackTrace();
		    			}
				}
				
			}
		});
        frame.setVisible(true);
        frame.pack();
	}
	
	public CreateEvent(String name,String d,String t, int i,String r) {
		
		JComboBox<String> comboBox;
	    JTextArea textArea;
	    JButton button;
	    
	    JFrame frame = new JFrame("Alegere meniu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(700,500));
        frame.getContentPane().setBackground(new Color(153,0,153));
        frame.setLocationRelativeTo(null);

        frame.setLayout(new GridBagLayout());

        comboBox = new JComboBox<>(new String[] { "Meniu Clasic", "Meniu Star", "Meniu President" });

        textArea = new JTextArea("Alege un meniu ca sa vezi detaliile!");
        textArea.setBackground(new Color(153,0,153));
        textArea.setForeground(Color.white);
        textArea.setFont(new Font("Calibri", Font.PLAIN, 18));
       
        comboBox.addActionListener(e -> {
        	int price = 0;
            String selectedItem = (String) comboBox.getSelectedItem();
            if (selectedItem.equals("Meniu Clasic")) {
            	textArea.setText(null);
                textArea.append("Aperitiv taranesc \n");
                textArea.append("Sarmale \n");
                textArea.append("Tort cu fructe \n");
                textArea.append("100 lei / pers \n");
                textArea.append("Numar invitati: " + i + "\n");
                textArea.append("Total meniu: " + 100*i + " lei");
                price = 100 * i;
            } else if (selectedItem.equals("Meniu Star")) {
            	textArea.setText(null);
                textArea.append("Aperitiv simplu \n");
                textArea.append("Vita cu garnituri \n");
                textArea.append("Tort de ciocolata \n");
                textArea.append("150 lei / pers \n");
                textArea.append("Numar invitati: " + i + "\n");
                textArea.append("Total meniu: " + 150*i + " lei");
                price = 150 * i;
            } else if (selectedItem.equals("Meniu President")) {
            	textArea.setText(null);
                textArea.append("Aperitiv elegant \n");
                textArea.append("Piept de rata cu portocale \n");
                textArea.append("Tort cu inghetata \n");
                textArea.append("180 lei / pers \n");
                textArea.append("Numar invitati: " + i + "\n");
                textArea.append("Total meniu: " + 180*i + " lei");
                price = 180 * i;
            }
        });

        button = new JButton("OK");

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(153,0,153));
        leftPanel.add(comboBox);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(153,0,153));
        rightPanel.add(textArea);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(153,0,153));
        bottomPanel.add(button);

        frame.add(leftPanel);
        frame.add(rightPanel);
        frame.add(bottomPanel);

        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Connection conn = null;
				java.sql.PreparedStatement pstmt = null;
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/events","root","");
					String createEvent = "INSERT INTO event(client_name,date,type,invites,price,room_name,food_name) VALUES (?,?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(createEvent);
					
					pstmt.setString(1, name);
					pstmt.setString(2, d);
					pstmt.setString(3, t);
					pstmt.setInt(4, i);
					pstmt.setInt(5, 0);
					pstmt.setString(6, r);
					pstmt.setString(7, (String)comboBox.getSelectedItem());
					pstmt.executeUpdate();
				
					JOptionPane.showMessageDialog(frame, "S-a creat evenimentul tau: " + t + " in data de " + d + " in sala " + r, "Confirmare succes", JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(frame, "La revedere, " + name, "Iesire aplicatie", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					
				} catch (ClassNotFoundException ex) {
			    	ex.printStackTrace();
		    	}catch(SQLException ep) {
		    		ep.printStackTrace();
		    		}catch (Exception ec) {
		    			ec.printStackTrace();
		    			}
			}
		});
        
        frame.setVisible(true);
	}
}