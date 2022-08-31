package demo.listener;

import demo.Main;
import demo.mysql.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateHomeListener implements ActionListener {

    private JTextField home;
    private JTextField room;

    public CreateHomeListener(JTextField home, JTextField room) {
        this.home = home;
        this.room = room;
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
        String home = this.home.getText();
        String room = this.room.getText();
        //身份验证并反馈
        try {
            if (home.isEmpty() || room.isEmpty()) {
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
            if (Dao.findHomeByNum(home + "-" + room)) {
                JLabel message = new JLabel("该宿舍已存在，请重新输入");
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
            } else {
                boolean ok = Dao.insertHome(home+"-"+room);
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
                        Main.createHome.setVisible(false);
                        Main.mainAdmin.setVisible(true);
                    }
                });
            }
            login2.setResizable(false);
            login2.setVisible(true);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
