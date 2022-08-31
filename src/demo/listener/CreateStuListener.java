package demo.listener;

import demo.Main;
import demo.mysql.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateStuListener implements ActionListener {

    private JTextField id;
    private JTextField name;
    private JTextField tel;

    public CreateStuListener(JTextField id, JTextField name, JTextField tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
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
        String i = id.getText();
        String n = name.getText();
        String t = tel.getText();
        String h = Main.homeChoice.getSelectedItem();
        //身份验证并反馈
        try {
            if (i.isEmpty() || n.isEmpty() || t.isEmpty() || h.isEmpty()) {
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
            if (Dao.findStuById(i)) {
                JLabel message = new JLabel("该学生已存在，请重新输入");
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
            } else if (Dao.findHomeCost(h)) {
                JLabel message = new JLabel("该宿舍已满5人，请重新选择宿舍");
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
                boolean ok = Dao.insertStu(i, n, t, h);
                boolean ok2 = Dao.incrHomeCost(h, 1);
                JLabel message = new JLabel(ok && ok2 ? "添加成功" : "添加失败");
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
                        Main.createStu.setVisible(false);
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
