package guis;

import db_objs.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGui extends BaseFrame {
    public RegisterGui() {
        super("Регистрация");
    }

    @Override
    protected void addGuiComponents() {
        JLabel bankingAppLabel = new JLabel("Регистрация");
        bankingAppLabel.setBounds(0, 40, super.getWidth(), 40);
        bankingAppLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bankingAppLabel.setForeground(Color.black);
        add(bankingAppLabel);

        JLabel loginLable = new JLabel("Login:");
        loginLable.setBounds(20, 120, getWidth() - 30, 24);
        loginLable.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(loginLable);

        JTextField loginTextField = new JTextField();
        loginTextField.setBounds(20, 160, getWidth() - 50, 40);
        loginTextField.setFont(new Font("Dialog", Font.PLAIN, 28));
        add(loginTextField);

        JLabel passwordlable = new JLabel("Password:");
        passwordlable.setBounds(20, 220, getWidth() - 50, 40); //мб 24 высота
        passwordlable.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordlable);

        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBounds(20, 260, getWidth() - 50, 40);
        passwordTextField.setFont(new Font("Dialog", Font.PLAIN, 28));
        add(passwordTextField);

        JLabel repasswordlable = new JLabel("Re-type Password");
        repasswordlable.setBounds(20, 320, getWidth() - 50, 40);
        repasswordlable.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(repasswordlable);

        JPasswordField repasswordField = new JPasswordField();
        repasswordField.setBounds(20, 360, getWidth() - 50, 40);
        repasswordField.setFont(new Font("Dialog", Font.PLAIN, 28));
        add(repasswordField);

        JButton registerButton = new JButton("Регистрация");
        registerButton.setBounds(20, 460, getWidth() - 50, 40);
        registerButton.setFont(new Font("Dialog", Font.BOLD, 20));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginTextField.getText();
                String password = String.valueOf(passwordTextField.getPassword());
                String rePassword = String.valueOf((repasswordField.getPassword()));
                if(validateUserInput(login, password, rePassword)) {
                    if (MyJDBC.register(login, password)) {
                        RegisterGui.this.dispose();
                        LoginGui loginGui = new LoginGui();
                        loginGui.setVisible(true);
                        JOptionPane.showMessageDialog(loginGui, "Регистрация аккаунта потверждена! поздравляю!!!");
                    } else {
                        JOptionPane.showMessageDialog(RegisterGui.this, "Ошибка: Логин уже используется");
                    }
                }else{
                        JOptionPane.showMessageDialog(RegisterGui.this, "Ошибка: Логин должен быть не менее 6 букв\n" + "и/или пароли не совподают" );
                }

            }
        });
        add(registerButton);

        JLabel loginLabel = new JLabel("<html><a href=\"#\">Есть аккаунт? Вход</a></html>");
        loginLabel.setBounds(0, 510, getWidth() - 10, 30);
        loginLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);
    }
    private boolean validateUserInput(String login, String password, String rePassword){
        if (login.length() == 0 || password.length()==0 || rePassword.length() == 0) return false;
        if(login.length() <6) return false;
        if(!password.equals(rePassword)) return false;
        return true;
    }
}
