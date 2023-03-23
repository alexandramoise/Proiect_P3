package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.login.AppConfigurationEntry;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import user.Admin;

public class Entry extends JFrame {
    public Entry() {
    	JFrame frame = new JFrame("Intrare in aplicatie");
        frame.setMinimumSize(new Dimension(700, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(153, 0, 153));
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        //c.anchor = GridBagConstraints.CENTER;

        JLabel title = new JLabel("Evenimente Restaurant Belcanto");
        title.setForeground(new Color(249,215,220));
        title.setFont(title.getFont().deriveFont(Font.ITALIC));
        title.setFont(new Font("Monotype Corsiva", Font.PLAIN, 26));
        frame.add(title,c);
        
        JLabel label = new JLabel("Selectati rolul dvs: ");
        label.setFont(new Font("Calibri", Font.PLAIN, 18));
        label.setForeground(Color.white);
        frame.add(label, c);
        c.gridy = 1;
        frame.add(label,c);

        JButton button1 = new JButton("Administrator");
        button1.setForeground(new Color(153, 0, 153));
        button1.setPreferredSize(new Dimension(120, 30));
        button1.setBackground(Color.white);
        c.gridy = 2;
        c.gridwidth = 1;
        frame.add(button1,c);

        JButton button2 = new JButton("Client");
        button2.setForeground(new Color(153, 0, 153));
        button2.setPreferredSize(new Dimension(120, 30));
        button2.setBackground(Color.white);
        c.gridx = 1;
        c.gridwidth = 2;
        frame.add(button2, c);

        frame.pack();
        frame.setLocationRelativeTo(null);


   button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginAdmin loginPage = new LoginAdmin();
                frame.dispose();
                loginPage.setVisible(true);
            }
        });
   
   button2.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
           LoginClient loginPage = new LoginClient();
           frame.setVisible(false);
           loginPage.setVisible(true);
       }
   });

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        Entry appEntry = new Entry();
    }
}
