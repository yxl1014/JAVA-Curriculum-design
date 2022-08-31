package demo.listener;

import demo.Main;
import demo.mysql.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeLogListener implements ActionListener {
    private JTextField ws;
    private JTextField safe;
    private Choice homes;

    SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");

    public HomeLogListener(JTextField ws, JTextField safe, Choice homes) {
        this.ws = ws;
        this.safe = safe;
        this.homes = homes;
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
        String ws = this.ws.getText();
        String safe = this.safe.getText();
        String home = homes.getSelectedItem();
        Date nowTime = new Date();
        String t = time.format(nowTime);
        //身份验证并反馈
        try {
            if (ws.isEmpty() || safe.isEmpty() || home.isEmpty()) {
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
            } else if (Dao.findLogByHomeAndTime(home, t)) {
                JLabel message = new JLabel("今天已经记录过了！");
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
                boolean ok = Dao.insertLog(home, t, ws, safe);
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
                        Main.homeLog.setVisible(false);
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
