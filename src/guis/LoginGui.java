package guis;

import db_objs.MyJDBC;
import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGui extends BaseFrame{
    public LoginGui() {
        super("ТИНЬКОФ");
    }


    @Override
    protected void addGuiComponents() {
        JLabel bankingAppLabel = new JLabel("Вход");
        bankingAppLabel.setBounds(0, 20, super.getWidth(), 40);
        bankingAppLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bankingAppLabel.setForeground(Color.black);
        add(bankingAppLabel);

        JLabel loginLable = new JLabel("Login:");
        loginLable.setBounds(20, 120, getWidth()-30, 24);
        loginLable.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(loginLable);

        JTextField loginTextField = new JTextField();
        loginTextField.setBounds(20, 160, getWidth()-50,40);
        loginTextField.setFont(new Font("Dialog",Font.PLAIN, 28));
        add(loginTextField);

        JLabel passwordlable = new JLabel("Password:");
        passwordlable.setBounds(20, 280, getWidth()-50, 40); //мб 24 высота
        passwordlable.setFont(new Font("Dialog",Font.PLAIN, 20));
        add(passwordlable);

        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBounds(20, 320, getWidth()-50,40);
        passwordTextField.setFont(new Font("Dialog",Font.PLAIN, 28));
        add(passwordTextField);

        JButton registerButton = new JButton("Вход");
        registerButton.setBounds(20,440,getWidth()-50,40);
        registerButton.setFont(new Font("Dialog",Font.BOLD,20));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginTextField.getText();
                String password = String.valueOf(passwordTextField.getPassword());
                User user = MyJDBC.validateLogin(login, password);
                if(user != null){
                    LoginGui.this.dispose();
                    BankingAppGui bankingAppGui = new BankingAppGui(user);
                    bankingAppGui.setVisible(true);
                    JOptionPane.showMessageDialog(bankingAppGui, "Вход выполнен");
                }else{
                    JOptionPane.showMessageDialog(LoginGui.this, "Вход невыполнен");

                }
            }
        });
        add(registerButton);

        JLabel registerLabel = new JLabel("<html><a href=\"#\">Нету аккаунта? Регистрация тута</a></html>");
        registerLabel.setBounds(20, 510, getWidth()-50, 40);
        registerLabel.setFont(new Font("Dialog",Font.BOLD, 20));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
               LoginGui.this.dispose();
               new RegisterGui().setVisible(true);
            }
        });
        add(registerLabel);






    }
}
