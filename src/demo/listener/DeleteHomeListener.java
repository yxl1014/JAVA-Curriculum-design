package demo.listener;

import demo.Main;
import demo.mysql.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteHomeListener implements ActionListener {

    private Choice homes;

    public DeleteHomeListener(Choice homes) {
        this.homes = homes;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Dimension dim = new Dimension(300, 30);
        //生成新界面
        JFrame login2 = new JFrame();
        login2.setSize(400, 200);
        login2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login2.setLocationRelativeTo(null);
        login2.setFont(Main.font);  //宋体，正常风格，14号字体

        String a = homes.getSelectedItem();
        //身份验证并反馈
        try {
            if (a.isEmpty()) {
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
            } else if (!Dao.homeHaveStu(a)) {
                JLabel message = new JLabel("该宿舍还有学生，请删除学生！");
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
                boolean ok = Dao.deleteHomeByNum(a);
                JLabel message = new JLabel(ok ? "删除成功" : "删除失败");
                //刷新列表
                Main.initChoice();
                //刷新页面
                Main.updateFrame();


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
                        Main.deleteAdmin.setVisible(false);
                        Main.mainAdmin.setVisible(true);
                    }
                });
            }
            login2.setResizable(false);
            login2.setVisible(true);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
