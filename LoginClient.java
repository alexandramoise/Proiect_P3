package gui;

import event.*;
import user.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginClient extends JFrame {
	public LoginClient() {
		JFrame frame = new JFrame("Login Client");
        frame.setMinimumSize(new Dimension(700, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        frame.setContentPane(contentPane);
        
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(153, 0, 153));
        GridBagConstraints c = new GridBagConstraints();
        
        JLabel title = new JLabel("Login Client");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);
        title.setFont(title.getFont().deriveFont(Font.ITALIC));
        title.setFont(new Font("Serif", Font.PLAIN, 25));
        c.gridx = 0;
        c.gridy = 0;
        frame.add(title,c);
        		
        JLabel userLabel = new JLabel("Email:");
        userLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
        userLabel.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        frame.add(userLabel, c);

        JTextField userText = new JTextField(15);
        c.gridx = 1;
        c.gridy = 1;
        frame.add(userText, c);

        JLabel passLabel = new JLabel("Parola");
        passLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
        passLabel.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 2;
        frame.add(passLabel, c);

        JPasswordField passText = new JPasswordField(15);
        c.gridx = 1;
        c.gridy = 2;
        frame.add(passText, c);

        JButton loginButton = new JButton("Log In");
        loginButton.setBackground(Color.white);
        loginButton.setForeground(new Color(153,0,153));
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_START;
        frame.add(loginButton, c);
        
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(Color.white);
        registerButton.setForeground(new Color(153,0,153));
        c.gridx = 1;
        c.gridy = 3;
        frame.add(registerButton,c);
	
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String pass = new String(passText.getPassword());
                Customer cus = new Customer();
    			cus = new Customer(user,pass);
    			
    			if(cus.login(cus,"client"))
    			{ System.out.println("Conectat: " + cus);
       			 frame.dispose();
    			 JOptionPane.showMessageDialog(frame, "Vei merge la crearea evenimentelor", "CORECT!!", JOptionPane.INFORMATION_MESSAGE);  			
    			 
    			 CreateEvent create = new CreateEvent(cus.getName());
    			 create.setVisible(true);
    			 create.pack();
    			}
    			else {
    				JOptionPane.showMessageDialog(frame, "Incorect :(", "!!Eroare!!", JOptionPane.ERROR_MESSAGE);
    			}
            }
        });
        
        registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Vei merge la crearea unui cont nou", "Creare cont", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				Register registerPage = new Register();
				registerPage.setVisible(true);
			}
		});
        
        frame.setVisible(true);
    }
}

