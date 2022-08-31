package demo;

import demo.domain.Log;
import demo.listener.*;
import demo.mysql.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class Main {

    static {
        initChoice();
    }

    public static JFrame init = createInit();
    public static JFrame login = createLogin();
    public static JFrame mainTea = createMainTea();
    public static JFrame mainAdmin = createMainAdmin();

    public static JFrame createAdmin = createCreateAdmin();
    public static JFrame createTea = createCreateTea();
    public static JFrame createHome = createCreateHome();
    public static JFrame createStu = createCreateStu();

    public static JFrame deleteAdmin = createDeleteAdmin();

    public static JFrame deleteTea = createDeleteTea();

    public static JFrame deleteHome = createDeleteHome();

    public static JFrame deleteStu = createDeleteStu();

    public static JFrame homeLog = createHomeLog();
    public static JFrame logMain;

    public static Font font = new Font("宋体", Font.PLAIN, 14);

    public static boolean type; //true ---> 管理员   false ---> 辅导员

    public static Choice adminChoice;
    public static Choice teaChoice;
    public static Choice homeChoice;
    public static Choice stuChoice;


    public static void main(String[] args) {
        //createStu.setVisible(true);
        init.setVisible(true);
    }

    public static void updateFrame() {
        login = createLogin();
        mainTea = createMainTea();
        mainAdmin = createMainAdmin();
        createAdmin = createCreateAdmin();
        createTea = createCreateTea();
        createHome = createCreateHome();
        createStu = createCreateStu();
        deleteAdmin = createDeleteAdmin();
        deleteTea = createDeleteTea();
        deleteHome = createDeleteHome();
        deleteStu = createDeleteStu();
        homeLog = createHomeLog();
    }

    public static void initChoice() {
        //初始化宿舍列表
        Dimension dim1 = new Dimension(100, 30);
        Choice homes = new Choice();
        List<String> hs;
        try {
            hs = Dao.getHomes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (String s : hs) {
            homes.add(s);
        }
        homes.setPreferredSize(dim1);
        homeChoice = homes;

        //初始话管理员列表
        Choice admins = new Choice();
        List<String> hs1;
        try {
            hs1 = Dao.getAdmins();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (String s : hs1) {
            admins.add(s);
        }
        admins.setPreferredSize(dim1);
        adminChoice = admins;

        //初始化辅导员列表
        Choice teas = new Choice();
        List<String> hs2;
        try {
            hs2 = Dao.getTeas();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (String s : hs2) {
            teas.add(s);
        }
        teas.setPreferredSize(dim1);
        teaChoice = teas;
        //初始化学生列表
        Choice stu = new Choice();
        List<String> hs3;
        try {
            hs3 = Dao.getStus();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (String s : hs3) {
            stu.add(s);
        }
        stu.setPreferredSize(dim1);
        stuChoice = stu;
    }

    private static JFrame createInit() {
        JFrame frame = new JFrame();
        //设置窗体对象的属性值
        frame.setTitle("选择登录方式");//设置窗体标题
        frame.setSize(400, 250);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(font);//设置字体，显示格式正常，大小

        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
        //实例化流式布局类的对象
        frame.setLayout(fl);

        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(100, 30);
        button1.setText("辅导员登录");
        button1.setFont(font);
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                init.setVisible(false);
                login.setVisible(true);
                type = false;
            }
        });

        JButton button2 = new JButton();
        //设置按键的显示内容
        button2.setText("管理员登录");
        button2.setFont(font);
        //设置按键大小
        button2.setSize(dim2);
        frame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                init.setVisible(false);
                login.setVisible(true);
                type = true;
            }
        });

        return frame;
    }

    private static JFrame createLogin() {
        JFrame frame = new JFrame();

        //设置窗体对象的属性值
        frame.setTitle("登录");//设置窗体标题
        frame.setSize(400, 250);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(font);//设置字体，显示格式正常，大小

        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
        //实例化流式布局类的对象
        frame.setLayout(fl);

        //实例化JLabel标签对象，该对象显示“账号”
        JLabel username = new JLabel("账号：");
        username.setFont(font);
        //将username标签添加到窗体上
        frame.add(username);

        //实例化JTextField标签对象化
        JTextField textName = new JTextField();
        Dimension dim1 = new Dimension(300, 30);
        textName.setPreferredSize(dim1);//设置除顶级容器组件以外其他组件的大小
        //将textName标签添加到窗体上
        frame.add(textName);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel labpass = new JLabel("密码：");
        labpass.setFont(font);
        //将labpass添加到窗体上
        frame.add(labpass);
        //实例化JPasswordField
        JPasswordField textPassword = new JPasswordField();
        //设置大小
        textPassword.setPreferredSize(dim1);
        //添加到窗体
        frame.add(textPassword);

        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(100, 30);
        button1.setText("登录");
        button1.setFont(font);
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);
        LoginListener ll = new LoginListener(frame, textName, textPassword);
        button1.addActionListener(ll);

        JButton button2 = new JButton();
        //设置按键的显示内容
        Dimension dim3 = new Dimension(100, 30);
        button2.setText("返回");
        button2.setFont(font);
        //设置按键大小
        button2.setSize(dim3);
        frame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.setVisible(false);
                init.setVisible(true);
            }
        });
        return frame;
    }

    private static JFrame createMainTea() {
        JFrame frame = new JFrame();
        //设置窗体对象的属性值
        frame.setTitle("辅导员菜单");//设置窗体标题
        frame.setSize(400, 250);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(font);//设置字体，显示格式正常，大小
        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
        //实例化流式布局类的对象
        frame.setLayout(fl);

        JLabel label = new JLabel("选择宿舍：");
        label.setFont(font);
        frame.add(label);
        Choice homes = new Choice();
        List<String> hs;
        try {
            hs = Dao.getHomes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (String s : hs) {
            homes.add(s);
        }
        Dimension dim1 = new Dimension(100, 30);
        homes.setPreferredSize(dim1);
        frame.add(homes);

        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(100, 30);
        button1.setText("查询");
        button1.setFont(font);
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Log text = Dao.findHomeMsg(homes.getSelectedItem());
                    mainTea.setVisible(false);
                    logMain = createLogsMain(text);
                    logMain.setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        return frame;
    }

    public static JFrame createLogsMain(Log log) {
        JFrame frame = new JFrame();
        //设置窗体对象的属性值
        frame.setTitle("日志信息");//设置窗体标题
        frame.setSize(700, 600);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(true);//禁止调整窗体大小
        frame.setFont(font);//设置字体，显示格式正常，大小
        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 200, 10);
        //实例化流式布局类的对象
        frame.setLayout(fl);

        JLabel l1 = new JLabel("日志编号 --> " + log.getId());
        l1.setFont(font);
        frame.add(l1);
        JLabel l2 = new JLabel("宿舍号 --> " + log.getHome());
        l2.setFont(font);
        frame.add(l2);
        JLabel l3 = new JLabel("宿舍成员------start");
        l3.setFont(font);
        frame.add(l3);
        for (Log.Stu s : log.getStu()) {
            JLabel l4 = new JLabel("学生学号 --> " + s.id + "  学生姓名 --> " + s.name + "  学生电话 --> " + s.tel);
            l4.setFont(font);
            frame.add(l4);
        }
        JLabel l5 = new JLabel("宿舍成员------end");
        l5.setFont(font);
        frame.add(l5);
        JLabel l6 = new JLabel("记录时间 --> " + log.getTime());
        l6.setFont(font);
        frame.add(l6);
        JLabel l7 = new JLabel("卫生情况 --> " + log.getWsMsg());
        l7.setFont(font);
        frame.add(l7);
        JLabel l8 = new JLabel("安全情况 --> " + log.getSafeMsg());
        l8.setFont(font);
        frame.add(l8);

        if (log.getWsScore() != -1) {
            JLabel l9 = new JLabel("卫生分数 --> " + log.getWsScore());
            l9.setFont(font);
            frame.add(l9);
        }
        if (log.getSafeScore() != -1) {
            JLabel l9 = new JLabel("安全分数 --> " + log.getSafeScore());
            l9.setFont(font);
            frame.add(l9);
        }


        //创建一个ScrollPane
/*        java.awt.List sp = new java.awt.List();
        sp.setFont(font);
        sp.add("日志编号 --> " + log.getId());
        sp.add("宿舍号 --> " + log.getHome());
        sp.add("宿舍成员------start");
        for (Log.Stu s : log.getStu()) {
            sp.add("学生学号 --> " + s.id + "  学生姓名 --> " + s.name + "  学生电话 --> " + s.tel);
        }
        sp.add("宿舍成员------end");
        sp.add("记录时间 --> " + log.getTime());
        sp.add("卫生情况 --> " + log.getWsMsg());
        sp.add("安全情况 --> " + log.getSafeMsg());
        if (log.getWsScore() != -1)
            sp.add("卫生分数 --> " + log.getWsScore());
        if (log.getSafeScore() != -1)
            sp.add("安全分数 --> " + log.getSafeScore());
        //将ScrollPane加入Frame
        frame.add(sp);*/


        JLabel ws = new JLabel("卫生分数：");
        ws.setFont(font);
        //将username标签添加到窗体上
        frame.add(ws);

        //实例化JTextField标签对象化
        JTextField wsText = new JTextField();
        Dimension dim1 = new Dimension(300, 30);
        wsText.setPreferredSize(dim1);//设置除顶级容器组件以外其他组件的大小
        //将textName标签添加到窗体上
        frame.add(wsText);
        JLabel safe = new JLabel("安全分数：");
        safe.setFont(font);
        //将username标签添加到窗体上
        frame.add(safe);

        //实例化JTextField标签对象化
        JTextField safeText = new JTextField();
        safeText.setPreferredSize(dim1);//设置除顶级容器组件以外其他组件的大小
        //将textName标签添加到窗体上
        frame.add(safeText);

        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(200, 60);
        button1.setText("上传");
        button1.setFont(font);
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);

        JButton button2 = new JButton();
        //设置按键的显示内容
        button2.setText("返回");
        button2.setFont(font);
        //设置按键大小
        button2.setSize(dim2);
        frame.add(button2);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainTea.setVisible(true);
                logMain.dispose();
            }
        });

        Dimension dim = new Dimension(300, 30);
        //生成新界面
        JFrame login2 = new JFrame();
        login2.setSize(400, 200);
        login2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login2.setLocationRelativeTo(null);
        login2.setFont(Main.font);  //宋体，正常风格，14号字体
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int w = Integer.parseInt(wsText.getText());
                int s = Integer.parseInt(safeText.getText());
                try {
                    if (wsText.getText().isEmpty() || safeText.getText().isEmpty()) {
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
                    } else if (Dao.isOkLog(log.getId())) {
                        JLabel message = new JLabel("该信息已经评过分了！");
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
                        boolean ok = Dao.updateLog(log.getId(), w, s);
                        JLabel message = new JLabel(ok ? "上传成功" : "上传失败");
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
                                try {
                                    Log text = Dao.findHomeMsg(log.getHome());
                                    logMain.dispose();
                                    logMain = createLogsMain(text);
                                    logMain.setVisible(true);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        });
                        login2.setResizable(false);
                        login2.setVisible(true);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        return frame;
    }

    private static JFrame createMainAdmin() {
        JFrame frame = new JFrame();
        //设置窗体对象的属性值
        frame.setTitle("选择登录方式");//设置窗体标题
        frame.setSize(800, 500);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(font);//设置字体，显示格式正常，大小


        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 100, 100);
        //实例化流式布局类的对象
        frame.setLayout(fl);


        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(200, 60);
        button1.setText("添加管理员");
        button1.setFont(font);
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAdmin.setVisible(false);
                createAdmin.setVisible(true);
            }
        });

        JButton button2 = new JButton();
        //设置按键的显示内容
        button2.setText("添加辅导员");
        button2.setFont(font);
        //设置按键大小
        button2.setSize(dim2);
        frame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAdmin.setVisible(false);
                createTea.setVisible(true);
            }
        });

        JButton button3 = new JButton();
        //设置按键的显示内容
        button3.setText("添加宿舍");
        button3.setFont(font);
        //设置按键大小
        button3.setSize(dim2);
        frame.add(button3);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAdmin.setVisible(false);
                createHome.setVisible(true);
            }
        });

        JButton button4 = new JButton();
        //设置按键的显示内容
        button4.setText("添加学生");
        button4.setFont(font);
        //设置按键大小
        button4.setSize(dim2);
        frame.add(button4);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAdmin.setVisible(false);
                createStu.setVisible(true);
            }
        });

        JButton button5 = new JButton();
        //设置按键的显示内容
        button5.setText("删除管理员");
        button5.setFont(font);
        //设置按键大小
        button5.setSize(dim2);
        frame.add(button5);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAdmin.setVisible(false);
                deleteAdmin.setVisible(true);
            }
        });

        JButton button6 = new JButton();
        //设置按键的显示内容
        button6.setText("删除辅导员");
        button6.setFont(font);
        //设置按键大小
        button6.setSize(dim2);
        frame.add(button6);
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAdmin.setVisible(false);
                deleteTea.setVisible(true);
            }
        });

        JButton button7 = new JButton();
        //设置按键的显示内容
        button7.setText("删除宿舍");
        button7.setFont(font);
        //设置按键大小
        button7.setSize(dim2);
        frame.add(button7);
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAdmin.setVisible(false);
                deleteHome.setVisible(true);
            }
        });

        JButton button8 = new JButton();
        //设置按键的显示内容
        button8.setText("删除学生");
        button8.setFont(font);
        //设置按键大小
        button8.setSize(dim2);
        frame.add(button8);
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAdmin.setVisible(false);
                deleteStu.setVisible(true);
            }
        });


        JButton button9 = new JButton();
        //设置按键的显示内容
        button9.setText("宿舍情况");
        button9.setFont(font);
        //设置按键大小
        button9.setSize(dim2);
        frame.add(button9);
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAdmin.setVisible(false);
                homeLog.setVisible(true);
            }
        });

        return frame;
    }

    private static JFrame createCreateAdmin() {
        JFrame frame = new JFrame();
        //设置窗体对象的属性值
        frame.setTitle("创建管理员");//设置窗体标题
        frame.setSize(400, 250);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(font);//设置字体，显示格式正常，大小

        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
        //实例化流式布局类的对象
        frame.setLayout(fl);

        //实例化JLabel标签对象，该对象显示“账号”
        JLabel username = new JLabel("账号：");
        username.setFont(font);
        //将username标签添加到窗体上
        frame.add(username);

        //实例化JTextField标签对象化
        JTextField textName = new JTextField();
        Dimension dim1 = new Dimension(300, 30);
        textName.setPreferredSize(dim1);//设置除顶级容器组件以外其他组件的大小
        //将textName标签添加到窗体上
        frame.add(textName);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel labpass = new JLabel("密码：");
        labpass.setFont(font);
        //将labpass添加到窗体上
        frame.add(labpass);
        //实例化JPasswordField
        JPasswordField textPassword = new JPasswordField();
        //设置大小
        textPassword.setPreferredSize(dim1);
        //添加到窗体
        frame.add(textPassword);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel labpass2 = new JLabel("确认密码：");
        labpass2.setFont(font);
        //将labpass2添加到窗体上
        frame.add(labpass2);
        //实例化JPasswordField
        JPasswordField textPassword2 = new JPasswordField();
        //设置大小
        textPassword2.setPreferredSize(dim1);
        //添加到窗体
        frame.add(textPassword2);


        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(100, 30);
        button1.setText("添加");
        button1.setFont(font);
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);
        CreateAdminListener ll = new CreateAdminListener(textName, textPassword, textPassword2);
        button1.addActionListener(ll);

        JButton button2 = new JButton();
        //设置按键的显示内容
        Dimension dim3 = new Dimension(100, 30);
        button2.setText("返回");
        button2.setFont(font);
        //设置按键大小
        button2.setSize(dim3);
        frame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAdmin.setVisible(false);
                mainAdmin.setVisible(true);
            }
        });
        return frame;
    }

    private static JFrame createCreateTea() {
        JFrame frame = new JFrame();
        //设置窗体对象的属性值
        frame.setTitle("创建辅导员");//设置窗体标题
        frame.setSize(400, 250);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(font);//设置字体，显示格式正常，大小

        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
        //实例化流式布局类的对象
        frame.setLayout(fl);

        //实例化JLabel标签对象，该对象显示“账号”
        JLabel username = new JLabel("工号：");
        username.setFont(font);
        //将username标签添加到窗体上
        frame.add(username);

        //实例化JTextField标签对象化
        JTextField textName = new JTextField();
        Dimension dim1 = new Dimension(300, 30);
        textName.setPreferredSize(dim1);//设置除顶级容器组件以外其他组件的大小
        //将textName标签添加到窗体上
        frame.add(textName);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel labpass = new JLabel("密码：");
        labpass.setFont(font);
        //将labpass添加到窗体上
        frame.add(labpass);
        //实例化JPasswordField
        JPasswordField textPassword = new JPasswordField();
        //设置大小
        textPassword.setPreferredSize(dim1);
        //添加到窗体
        frame.add(textPassword);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel labpass2 = new JLabel("确认密码：");
        labpass2.setFont(font);
        //将labpass2添加到窗体上
        frame.add(labpass2);
        //实例化JPasswordField
        JPasswordField textPassword2 = new JPasswordField();
        //设置大小
        textPassword2.setPreferredSize(dim1);
        //添加到窗体
        frame.add(textPassword2);


        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(100, 30);
        button1.setText("添加");
        button1.setFont(font);
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);
        CreateTeaListener ll = new CreateTeaListener(textName, textPassword, textPassword2);
        button1.addActionListener(ll);

        JButton button2 = new JButton();
        //设置按键的显示内容
        Dimension dim3 = new Dimension(100, 30);
        button2.setText("返回");
        button2.setFont(font);
        //设置按键大小
        button2.setSize(dim3);
        frame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTea.setVisible(false);
                mainAdmin.setVisible(true);
            }
        });
        return frame;
    }

    private static JFrame createCreateHome() {
        JFrame frame = new JFrame();
        //设置窗体对象的属性值
        frame.setTitle("创建宿舍");//设置窗体标题
        frame.setSize(400, 250);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(font);//设置字体，显示格式正常，大小

        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
        //实例化流式布局类的对象
        frame.setLayout(fl);

        //实例化JLabel标签对象，该对象显示“账号”
        JLabel username = new JLabel("楼号：");
        username.setFont(font);
        //将username标签添加到窗体上
        frame.add(username);

        //实例化JTextField标签对象化
        JTextField home = new JTextField();
        Dimension dim1 = new Dimension(300, 30);
        home.setPreferredSize(dim1);//设置除顶级容器组件以外其他组件的大小
        //将textName标签添加到窗体上
        frame.add(home);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel labpass = new JLabel("房间号：");
        labpass.setFont(font);
        //将labpass添加到窗体上
        frame.add(labpass);
        //实例化JPasswordField
        JPasswordField room = new JPasswordField();
        //设置大小
        room.setPreferredSize(dim1);
        //添加到窗体
        frame.add(room);


        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(100, 30);
        button1.setText("添加");
        button1.setFont(font);
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);
        CreateHomeListener ll = new CreateHomeListener(home, room);
        button1.addActionListener(ll);

        JButton button2 = new JButton();
        //设置按键的显示内容
        Dimension dim3 = new Dimension(100, 30);
        button2.setText("返回");
        button2.setFont(font);
        //设置按键大小
        button2.setSize(dim3);
        frame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createHome.setVisible(false);
                mainAdmin.setVisible(true);
            }
        });
        return frame;
    }

    private static JFrame createCreateStu() {
        JFrame frame = new JFrame();
        //设置窗体对象的属性值
        frame.setTitle("创建学生");//设置窗体标题
        frame.setSize(400, 250);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(font);//设置字体，显示格式正常，大小

        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
        //实例化流式布局类的对象
        frame.setLayout(fl);

        //实例化JLabel标签对象，该对象显示“账号”
        JLabel username = new JLabel("学号：");
        username.setFont(font);
        //将username标签添加到窗体上
        frame.add(username);

        //实例化JTextField标签对象化
        JTextField sid = new JTextField();
        Dimension dim1 = new Dimension(300, 30);
        sid.setPreferredSize(dim1);//设置除顶级容器组件以外其他组件的大小
        //将textName标签添加到窗体上
        frame.add(sid);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel labpass = new JLabel("姓名：");
        labpass.setFont(font);
        //将labpass添加到窗体上
        frame.add(labpass);
        //实例化JPasswordField
        JPasswordField name = new JPasswordField();
        //设置大小
        name.setPreferredSize(dim1);
        //添加到窗体
        frame.add(name);


        //实例化JLabel标签对象，该对象显示“密码”
        JLabel labpass1 = new JLabel("电话：");
        labpass1.setFont(font);
        //将labpass添加到窗体上
        frame.add(labpass1);
        //实例化JPasswordField
        JPasswordField tel = new JPasswordField();
        //设置大小
        tel.setPreferredSize(dim1);
        //添加到窗体
        frame.add(tel);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel label = new JLabel("选择宿舍：");
        label.setFont(font);
        frame.add(label);
        frame.add(homeChoice);


        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(100, 30);
        button1.setText("添加");
        button1.setFont(font);
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);

        CreateStuListener ll = new CreateStuListener(sid, name, tel);
        button1.addActionListener(ll);

        JButton button2 = new JButton();
        //设置按键的显示内容
        Dimension dim3 = new Dimension(100, 30);
        button2.setText("返回");
        button2.setFont(font);
        //设置按键大小
        button2.setSize(dim3);
        frame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createStu.setVisible(false);
                mainAdmin.setVisible(true);
            }
        });
        return frame;
    }

    private static JFrame createDeleteAdmin() {
        JFrame frame = new JFrame();
        //设置窗体对象的属性值
        frame.setTitle("删除管理员");//设置窗体标题
        frame.setSize(400, 250);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(font);//设置字体，显示格式正常，大小

        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
        //实例化流式布局类的对象
        frame.setLayout(fl);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel label = new JLabel("选择管理员：");
        label.setFont(font);
        frame.add(label);


        frame.add(adminChoice);

        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(100, 30);
        button1.setText("删除");
        button1.setFont(font);
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);
        DeleteAdminListener ll = new DeleteAdminListener(adminChoice);
        button1.addActionListener(ll);

        JButton button2 = new JButton();
        //设置按键的显示内容
        Dimension dim3 = new Dimension(100, 30);
        button2.setText("返回");
        button2.setFont(font);
        //设置按键大小
        button2.setSize(dim3);
        frame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAdmin.setVisible(false);
                mainAdmin.setVisible(true);
            }
        });
        return frame;
    }

    private static JFrame createDeleteTea() {
        JFrame frame = new JFrame();
        //设置窗体对象的属性值
        frame.setTitle("删除辅导员");//设置窗体标题
        frame.setSize(400, 250);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(font);//设置字体，显示格式正常，大小

        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
        //实例化流式布局类的对象
        frame.setLayout(fl);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel label = new JLabel("选择辅导员：");
        label.setFont(font);
        frame.add(label);


        frame.add(teaChoice);

        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(100, 30);
        button1.setText("删除");
        button1.setFont(font);
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);
        DeleteTeaListener ll = new DeleteTeaListener();
        button1.addActionListener(ll);

        JButton button2 = new JButton();
        //设置按键的显示内容
        Dimension dim3 = new Dimension(100, 30);
        button2.setText("返回");
        button2.setFont(font);
        //设置按键大小
        button2.setSize(dim3);
        frame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAdmin.setVisible(false);
                mainAdmin.setVisible(true);
            }
        });
        return frame;
    }

    private static JFrame createDeleteHome() {
        JFrame frame = new JFrame();
        //设置窗体对象的属性值
        frame.setTitle("删除宿舍");//设置窗体标题
        frame.setSize(400, 250);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(font);//设置字体，显示格式正常，大小

        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
        //实例化流式布局类的对象
        frame.setLayout(fl);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel label = new JLabel("选择宿舍：");
        label.setFont(font);
        frame.add(label);

        Choice homes = new Choice();
        List<String> hs;
        try {
            hs = Dao.getHomes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (String s : hs) {
            homes.add(s);
        }
        Dimension dim1 = new Dimension(100, 30);
        homes.setPreferredSize(dim1);

        frame.add(homes);

        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(100, 30);
        button1.setText("删除");
        button1.setFont(font);
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);
        DeleteHomeListener ll = new DeleteHomeListener(homes);
        button1.addActionListener(ll);

        JButton button2 = new JButton();
        //设置按键的显示内容
        Dimension dim3 = new Dimension(100, 30);
        button2.setText("返回");
        button2.setFont(font);
        //设置按键大小
        button2.setSize(dim3);
        frame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteHome.setVisible(false);
                mainAdmin.setVisible(true);
            }
        });
        return frame;
    }

    private static JFrame createDeleteStu() {
        JFrame frame = new JFrame();
        //设置窗体对象的属性值
        frame.setTitle("删除学生");//设置窗体标题
        frame.setSize(400, 250);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(font);//设置字体，显示格式正常，大小

        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
        //实例化流式布局类的对象
        frame.setLayout(fl);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel label = new JLabel("选择学生：");
        label.setFont(font);
        frame.add(label);
        frame.add(stuChoice);

        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(100, 30);
        button1.setText("删除");
        button1.setFont(font);
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);
        DeleteStuListener ll = new DeleteStuListener();
        button1.addActionListener(ll);

        JButton button2 = new JButton();
        //设置按键的显示内容
        Dimension dim3 = new Dimension(100, 30);
        button2.setText("返回");
        button2.setFont(font);
        //设置按键大小
        button2.setSize(dim3);
        frame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStu.setVisible(false);
                mainAdmin.setVisible(true);
            }
        });
        return frame;
    }

    private static JFrame createHomeLog() {
        JFrame frame = new JFrame();
        //设置窗体对象的属性值
        frame.setTitle("宿舍情况");//设置窗体标题
        frame.setSize(400, 250);//设置窗体大小，只对顶层容器生效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体关闭操作，3表示关闭窗体退出程序
        frame.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setResizable(false);//禁止调整窗体大小
        frame.setFont(font);//设置字体，显示格式正常，大小

        //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
        //实例化流式布局类的对象
        frame.setLayout(fl);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel label = new JLabel("选择宿舍：");
        label.setFont(font);
        frame.add(label);

        Choice homes = new Choice();
        List<String> hs;
        try {
            hs = Dao.getHomes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (String s : hs) {
            homes.add(s);
        }
        Dimension dim1 = new Dimension(100, 30);
        homes.setPreferredSize(dim1);

        frame.add(homes);

        //实例化JLabel标签对象，该对象显示“密码”
        JLabel labpass = new JLabel("卫生情况：");
        labpass.setFont(font);
        //将labpass添加到窗体上
        frame.add(labpass);
        //实例化JPasswordField
        JTextField ws = new JTextField();
        //设置大小
        ws.setPreferredSize(dim1);
        //添加到窗体
        frame.add(ws);


        //实例化JLabel标签对象，该对象显示“密码”
        JLabel labpass1 = new JLabel("安全情况：");
        labpass1.setFont(font);
        //将labpass添加到窗体上
        frame.add(labpass1);
        //实例化JPasswordField
        JTextField safe = new JTextField();
        //设置大小
        safe.setPreferredSize(dim1);
        //添加到窗体
        frame.add(safe);

        JButton button1 = new JButton();
        //设置按键的显示内容
        Dimension dim2 = new Dimension(100, 30);
        button1.setText("发布");
        button1.setFont(font);
        //设置按键大小
        button1.setSize(dim2);
        frame.add(button1);
        HomeLogListener ll = new HomeLogListener(ws, safe, homes);
        button1.addActionListener(ll);

        JButton button2 = new JButton();
        //设置按键的显示内容
        Dimension dim3 = new Dimension(100, 30);
        button2.setText("返回");
        button2.setFont(font);
        //设置按键大小
        button2.setSize(dim3);
        frame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeLog.setVisible(false);
                mainAdmin.setVisible(true);
            }
        });
        return frame;
    }
}
