package demo.listener;

import demo.Main;
import demo.mysql.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginListener implements ActionListener {
    private JTextField text_name;
    private JPasswordField text_password;
    private JFrame login;

    public LoginListener(JFrame login,
                         JTextField text_name,
                         JPasswordField text_password) {//获取登录界面、账号密码输入框对象
        this.login = login;
        this.text_name = text_name;
        this.text_password = text_password;
    }

    int i = 3;//3次登录机会

    @Override
    public void actionPerformed(ActionEvent e) {
        Dimension dim = new Dimension(300, 30);
        //生成新界面
        javax.swing.JFrame login2 = new javax.swing.JFrame();
        login2.setSize(400, 200);
        login2.setDefaultCloseOperation(3);
        login2.setLocationRelativeTo(null);
        login2.setFont(new Font("宋体", Font.PLAIN, 14));  //宋体，正常风格，14号字体
        //创建组件
        javax.swing.JPanel jp1 = new JPanel();
        javax.swing.JPanel jp2 = new JPanel();
        String s = null;
        //身份验证并反馈
        try {
            if (Main.type ?
                    Dao.loginAdmin(text_name.getText(), text_password.getText()) :
                    Dao.loginTea(text_name.getText(), text_password.getText())) {
                login.setVisible(false);
                if (Main.type) {
                    Main.mainAdmin.setVisible(true);
                } else {
                    Main.mainTea.setVisible(true);
                }
            } else if (i >= 2) {
                JLabel message = new JLabel("账号或密码错误,您今天还有" + (i - 1) + "次机会");
                message.setFont(new Font("宋体", Font.PLAIN, 14));  //宋体，正常风格，14号字体
                message.setPreferredSize(dim);
                //将textName标签添加到窗体上
                jp1.add(message);
                login2.add(jp1, BorderLayout.CENTER);
                JButton close = new JButton("确定");
                close.setFont(new Font("宋体", Font.PLAIN, 14));
                //设置按键大小
                close.setSize(dim);
                jp2.add(close);
                login2.add(jp2, BorderLayout.SOUTH);
                i--;//次数减少
                close.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        login2.dispose();
                    }
                });
                login2.setResizable(false);
                login2.setVisible(true);
            } else if (i == 1) {
                JLabel message = new JLabel("账号已锁定，请明天再试");
                message.setFont(new Font("宋体", Font.PLAIN, 14));  //宋体，正常风格，14号字体
                message.setPreferredSize(dim);
                //将textName标签添加到窗体上
                jp1.add(message);
                login2.add(jp1, BorderLayout.CENTER);
                JButton close = new JButton("确定");
                close.setFont(new Font("宋体", Font.PLAIN, 14));
                //设置按键大小
                close.setSize(dim);
                jp2.add(close);
                login2.add(jp2, BorderLayout.SOUTH);
                close.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        login2.dispose();
                    }
                });
                login2.setResizable(false);
                login2.setVisible(true);
                //通过我们获取的登录界面对象，用dispose方法关闭它
                login.dispose();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
