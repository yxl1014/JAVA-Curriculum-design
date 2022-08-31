package demo.listener;

import demo.Main;
import demo.mysql.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateTeaListener implements ActionListener {
    private JTextField name;
    private JPasswordField password;
    private JPasswordField password2;

    public CreateTeaListener(JTextField name, JPasswordField password, JPasswordField password2) {
        this.name = name;
        this.password = password;
        this.password2 = password2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Dimension dim = new Dimension(300, 30);
        //生成新界面
        JFrame login2 = new JFrame();
        login2.setSize(400, 200);
        login2.setDefaultCloseOperation(3);
        login2.setLocationRelativeTo(null);
        login2.setFont(Main.font);  //宋体，正常风格，14号字体
        String un = name.getText();
        String p1 = password.getText();
        String p2 = password2.getText();
        //身份验证并反馈
        try {
            if (un.isEmpty() || p1.isEmpty() || p2.isEmpty()) {
                JLabel message = new JLabel("数据不能为空！");
                message.setFont(Main.font);  //宋体，正常风格，14号字体
                message.setPreferredSize(dim);

                //将textName标签添加到窗体上
                JPanel jp1 = new JPanel();
                JPanel jp2 = new JPanel();
                jp1.add(message);
                login2.add(jp1, BorderLayout.CENTER);
                JButton close = new JButton("确定");
                close.setFont(Main.font);
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
            }
            if (Dao.findTeaByUsername(un)) {
                JLabel message = new JLabel("该老师已存在，请重新输入");
                message.setFont(Main.font);  //宋体，正常风格，14号字体
                message.setPreferredSize(dim);

                //将textName标签添加到窗体上
                JPanel jp1 = new JPanel();
                JPanel jp2 = new JPanel();
                jp1.add(message);
                login2.add(jp1, BorderLayout.CENTER);
                JButton close = new JButton("确定");
                close.setFont(Main.font);
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
            } else if (!p1.isEmpty() && !p1.equals(p2)) {
                JLabel message = new JLabel("密码不相同，请确认你的密码！");
                message.setFont(Main.font);  //宋体，正常风格，14号字体
                message.setPreferredSize(dim);

                //将textName标签添加到窗体上
                JPanel jp1 = new JPanel();
                JPanel jp2 = new JPanel();
                jp1.add(message);
                login2.add(jp1, BorderLayout.CENTER);
                JButton close = new JButton("确定");
                close.setFont(Main.font);
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
            } else {
                boolean ok = Dao.insertTea(un, p1);
                JLabel message = new JLabel(ok ? "添加成功" : "添加失败");
                message.setFont(Main.font);  //宋体，正常风格，14号字体
                message.setPreferredSize(dim);
                //刷新列表
                Main.initChoice();
                //刷新页面
                Main.updateFrame();
                //将textName标签添加到窗体上
                JPanel jp1 = new JPanel();
                JPanel jp2 = new JPanel();
                jp1.add(message);
                login2.add(jp1, BorderLayout.CENTER);
                JButton close = new JButton("确定");
                close.setFont(Main.font);
                //设置按键大小
                close.setSize(dim);
                jp2.add(close);
                login2.add(jp2, BorderLayout.SOUTH);

                close.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        login2.dispose();
                        Main.createTea.setVisible(false);
                        Main.mainAdmin.setVisible(true);
                    }
                });
                login2.setResizable(false);
                login2.setVisible(true);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
