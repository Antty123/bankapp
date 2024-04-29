package guis;

import db_objs.User;

import javax.swing.*;
import java.awt.*;

public abstract class BaseFrame extends JFrame {
    protected User user;
    public BaseFrame(String title){
        initialize(title);
    }

    public BaseFrame(String title, User user) {
        this.user = user;
        initialize(title);
    }

    private  void initialize(String title){
        setTitle(title);
        setSize(420, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        addGuiComponents();
        setBackground(Color.GREEN);//help why don't this work?
    }
    protected abstract void addGuiComponents();

}
