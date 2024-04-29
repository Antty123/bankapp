package db_objs;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class User {
    private final int id;
    private final String login, password;
    private BigDecimal currentBalance;

    public User(int id, String login, String password, BigDecimal currentBalance) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.currentBalance = currentBalance;

    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal newBalance) {
        currentBalance = newBalance.setScale(2, RoundingMode.FLOOR);

    }
}
