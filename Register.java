package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.NumericShaper;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame{
	public Register() {
		JFrame frame = new JFrame("Register Client");
        frame.setMinimumSize(new Dimension(700, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        frame.setContentPane(contentPane);
        
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(153, 0, 153));
        GridBagConstraints c = new GridBagConstraints();
        
        JLabel title = new JLabel("Register Client");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);
        title.setFont(title.getFont().deriveFont(Font.ITALIC));
        title.setFont(new Font("Serif", Font.PLAIN, 25));
        c.gridx = 0;
        c.gridy = 0;
        frame.add(title,c);
        		
        JLabel userName = new JLabel("Nume:");
        userName.setFont(new Font("Calibri", Font.PLAIN, 16));
        userName.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        frame.add(userName, c);

        JTextField userText = new JTextField(15);
        c.gridx = 1;
        c.gridy = 1;
        frame.add(userText, c);

        JLabel userLabel = new JLabel("Email:");
        userLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
        userLabel.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        frame.add(userLabel, c);
        
        JTextField userMail = new JTextField(15);
        c.gridx = 1;
        c.gridy = 2;
        frame.add(userMail, c);
        
        JLabel passLabel = new JLabel("Parola:");
        passLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
        passLabel.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 3;
        frame.add(passLabel, c);

        JPasswordField passText = new JPasswordField(15);
        c.gridx = 1;
        c.gridy = 3;
        frame.add(passText, c);
        
        JLabel passConfirm = new JLabel("Confirm parola:");
        passConfirm.setForeground(Color.white);
        passConfirm.setFont(new Font("Calibri", Font.PLAIN, 16));
        c.gridx = 0;
        c.gridy = 4;
        frame.add(passConfirm, c);
        
        JPasswordField confText = new JPasswordField(15);
        c.gridx = 1;
        c.gridy = 4;
        frame.add(confText, c);
        
        JButton regButton = new JButton("Register");
        regButton.setBackground(Color.white);
        regButton.setForeground(new Color(153,0,153));
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_START;
        frame.add(regButton , c);
        
        
        frame.pack();
        frame.setVisible(true);
        
        regButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = userText.getText();
				String email = userMail.getText();
				String pass = new String(passText.getPassword());
				String conf = new String(confText.getPassword());
				
				if(pass.equals(conf)) {
					System.out.println("Cont nou: " + name + " " + email + " " + pass + " " + conf);
					
					Connection conn = null;
					java.sql.PreparedStatement pstmt = null;
					try {
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/events","root","");
						String createAccount = "INSERT INTO users(role,name,email,password) VALUES (?,?,?,?)";
						pstmt = conn.prepareStatement(createAccount);
						pstmt.setString(1, "client");
						pstmt.setString(2, name);
						pstmt.setString(3, email);
						pstmt.setString(4, pass);
						pstmt.executeUpdate();
						
						JOptionPane.showMessageDialog(frame, "Cont creat! Vei merge la pagina de Login", "CORECT!!", JOptionPane.INFORMATION_MESSAGE);
					
						frame.dispose();
						LoginClient login = new LoginClient();
						login.setVisible(true);
						
						conn.close();
						pstmt.close();
						
					}catch (ClassNotFoundException ex) {
				    	ex.printStackTrace();
			    	}catch(SQLException ep) {
			    		ep.printStackTrace();
			    		}catch (Exception ec) {
			    			ec.printStackTrace();
			    			}
				}
				else {
					JOptionPane.showMessageDialog(frame, "Parola si confirmarea sunt diferite :(", "!!Eroare!!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
