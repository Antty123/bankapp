package guis;


import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankingAppGui extends BaseFrame implements ActionListener {
    private JTextField currentBalanceField;
    public JTextField getCurrentBalanceField() {return currentBalanceField;}
    public BankingAppGui(User user){
        super("ТИНЬКОФ", user);

    }
    @Override
    protected void addGuiComponents() {
        String welcome = "<html>" +
                "<body style='text-align:center'>" +
                "<b>Привет " + user.getLogin() + "</b><br>" +
                "Что вы ходите делать сегодня?</body></html>";
        JLabel welcomeMessageLabel = new JLabel(welcome);
        welcomeMessageLabel.setBounds(0,20, getWidth()-10, 40);
        welcomeMessageLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        welcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeMessageLabel);

        JLabel currentBalanceLabel = new JLabel("Текущий баланс");
        currentBalanceLabel.setBounds(0,80, getWidth()-10, 30);
        currentBalanceLabel.setFont(new Font("Dialog", Font.BOLD, 22));
        currentBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(currentBalanceLabel);

        currentBalanceField = new JTextField("$"+ user.getCurrentBalance());
        currentBalanceField.setBounds(15, 120, getWidth() - 50, 40);
        currentBalanceField.setFont(new Font("Dialog", Font.PLAIN, 28));
        currentBalanceField.setHorizontalAlignment(SwingConstants.RIGHT);
        currentBalanceField.setEditable(false);
        add(currentBalanceField);

        JButton depositButton = new JButton("Дэп");
        depositButton.setBounds(15, 180, getWidth()-50, 50);
        depositButton.setFont(new Font("Dialog",Font.BOLD, 22));
        depositButton.addActionListener(this);
        add(depositButton);

        JButton withdrawButton = new JButton("Вывод");
        withdrawButton.setBounds(15, 250, getWidth()-50, 50);
        withdrawButton.setFont(new Font("Dialog",Font.BOLD, 22));
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        JButton pastTransactionButton = new JButton("Прошлые Транзакции");
        pastTransactionButton.setBounds(15, 320, getWidth()-50, 50);
        pastTransactionButton.setFont(new Font("Dialog",Font.BOLD, 22));
        pastTransactionButton.addActionListener(this);
        add(pastTransactionButton);

        JButton transferButton = new JButton("Перевод");
        transferButton.setBounds(15,390,getWidth()-50, 50);
        transferButton.setFont(new Font("Dialog",Font.BOLD, 22));
        transferButton.addActionListener(this);
        add(transferButton);

        JButton logutButton = new JButton("Выход");
        logutButton.setBounds(15,500,getWidth()-50, 50);
        logutButton.setFont(new Font("Dialog",Font.BOLD, 22));
        logutButton.addActionListener(this);
        add(logutButton);






    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
        if (buttonPressed.equalsIgnoreCase("Выход")){
            new LoginGui().setVisible(true);
            this.dispose();
            return;
        }
        BankingAppDialog bankingAppDialog = new BankingAppDialog(this, user);
        bankingAppDialog.setTitle(buttonPressed);


        if (buttonPressed.equalsIgnoreCase("Дэп")|| buttonPressed.equalsIgnoreCase("Вывод")
                || buttonPressed.equalsIgnoreCase("Перевод")){
            bankingAppDialog.addCurrentBalanceAmount();

            bankingAppDialog.addActionButton(buttonPressed);

            if (buttonPressed.equalsIgnoreCase("Перевод")){
                bankingAppDialog.addUserField();

            }

        } else if (buttonPressed.equalsIgnoreCase("Прошлые Транзакции")) {
            bankingAppDialog.addPassTransactionComponents();

        }

        bankingAppDialog.setVisible(true);
    }
}
