package demo.mysql;

import demo.domain.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dao {
    private static final Connection connection = ConnDB.getConn();

    public static boolean loginAdmin(String un, String pwd) throws SQLException {
        String sql = "select * from admin where username = ? and password = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, un);
        pre.setString(2, pwd);
        ResultSet resultSet = pre.executeQuery();
        return resultSet.next();
    }

    public static boolean loginTea(String un, String pwd) throws SQLException {
        String sql = "select * from tea where username = ? and password = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, un);
        pre.setString(2, pwd);
        ResultSet resultSet = pre.executeQuery();
        return resultSet.next();
    }

    public static boolean findAdminByUsername(String un) throws SQLException {
        String sql = "select * from admin where username = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, un);
        ResultSet resultSet = pre.executeQuery();
        return resultSet.next();
    }

    public static boolean insertAdmin(String un, String p1) throws SQLException {
        String sql = "insert into admin(username,password) values (?,?);";
        PreparedStatement pre = connection.prepareStatement(sql);
        //设置参数
        pre.setString(1, un);
        pre.setString(2, p1);
        int ok = pre.executeUpdate();

        return ok == 1;
    }

    public static boolean findTeaByUsername(String un) throws SQLException {
        String sql = "select * from tea where username = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, un);
        ResultSet resultSet = pre.executeQuery();
        return resultSet.next();
    }

    public static boolean insertTea(String un, String p1) throws SQLException {
        String sql = "insert into tea(username,password) values (?,?);";
        PreparedStatement pre = connection.prepareStatement(sql);
        //设置参数
        pre.setString(1, un);
        pre.setString(2, p1);
        int ok = pre.executeUpdate();

        return ok == 1;
    }

    public static boolean insertHome(String s) throws SQLException {
        String sql = "insert into home(num) values (?);";
        PreparedStatement pre = connection.prepareStatement(sql);
        //设置参数
        pre.setString(1, s);
        int ok = pre.executeUpdate();

        return ok == 1;
    }

    public static boolean findHomeByNum(String s) throws SQLException {
        String sql = "select * from home where num = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, s);
        ResultSet resultSet = pre.executeQuery();
        return resultSet.next();
    }

    public static List<String> getHomes() throws SQLException {
        String sql = "select * from home;";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet resultSet = pre.executeQuery();
        List<String> res = new ArrayList<>();
        while (resultSet.next()) {
            res.add(resultSet.getString(2));
        }
        return res;
    }

    public static boolean findStuById(String i) throws SQLException {
        String sql = "select * from stu where id = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, i);
        ResultSet resultSet = pre.executeQuery();
        return resultSet.next();
    }

    public static boolean findHomeCost(String h) throws SQLException {
        String sql = "select * from home where num = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, h);
        ResultSet resultSet = pre.executeQuery();
        int cost = 0;
        if (resultSet.next()) {
            cost = resultSet.getInt(3);
        }
        return cost == 5;
    }

    public static boolean insertStu(String i, String n, String t, String h) throws SQLException {
        String sql = "insert into stu(id,name,tel,hid) values (?,?,?,?);";
        PreparedStatement pre = connection.prepareStatement(sql);
        //设置参数
        pre.setString(1, i);
        pre.setString(2, n);
        pre.setString(3, t);
        pre.setString(4, h);
        int ok = pre.executeUpdate();
        return ok == 1;
    }

    public static boolean incrHomeCost(String h, int num) throws SQLException {
        String sql = "select * from home where num = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, h);
        ResultSet resultSet = pre.executeQuery();
        int cost = 0;
        if (resultSet.next()) {
            cost = resultSet.getInt(3);
        }

        String sql1 = "update home set cost = ? where num = ?";
        PreparedStatement pre1 = connection.prepareStatement(sql1);
        pre1.setInt(1, cost + num);
        pre1.setString(2, h);
        int ok = pre1.executeUpdate();
        return ok == 1;
    }

    public static List<String> getAdmins() throws SQLException {
        String sql = "select * from admin;";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet resultSet = pre.executeQuery();
        List<String> res = new ArrayList<>();
        while (resultSet.next()) {
            res.add(resultSet.getString(2));
        }
        return res;
    }

    public static boolean deleteAdminByUn(String a) throws SQLException {
        String sql = "delete from admin where username = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, a);
        int ok = pre.executeUpdate();
        return ok == 1;
    }

    public static List<String> getTeas() throws SQLException {
        String sql = "select * from tea;";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet resultSet = pre.executeQuery();
        List<String> res = new ArrayList<>();
        while (resultSet.next()) {
            res.add(resultSet.getString(2));
        }
        return res;
    }

    public static List<String> getStus() throws SQLException {
        String sql = "select * from stu;";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet resultSet = pre.executeQuery();
        List<String> res = new ArrayList<>();
        while (resultSet.next()) {
            res.add(resultSet.getString(1));
        }
        return res;
    }

    public static boolean deleteTeaByUn(String a) throws SQLException {
        String sql = "delete from tea where username = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, a);
        int ok = pre.executeUpdate();
        return ok == 1;
    }

    public static boolean deleteHomeByNum(String a) throws SQLException {
        String sql = "delete from home where num = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, a);
        int ok = pre.executeUpdate();
        return ok == 1;
    }

    public static boolean homeHaveStu(String a) throws SQLException {
        String sql = "select * from home where num = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, a);
        ResultSet resultSet = pre.executeQuery();
        int cost = 0;
        if (resultSet.next()) {
            cost = resultSet.getInt(3);
        }
        return cost == 0;
    }

    public static boolean deleteStuById(String a) throws SQLException {
        String sql = "delete from stu where id = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, a);
        int ok = pre.executeUpdate();
        return ok == 1;
    }

    public static String getHomeByUid(String a) throws SQLException {
        String sql = "select * from stu where id = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, a);
        ResultSet resultSet = pre.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(4);
        }
        return null;
    }

    public static Log findHomeMsg(String home) throws SQLException {
        String sql = "select * from stu where hid = ?;";
        Log log = new Log();
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, home);
        ResultSet resultSet = pre.executeQuery();
        List<Log.Stu> stus = new ArrayList<>();
        while (resultSet.next()) {
            Log.Stu stu = new Log.Stu();
            stu.id = resultSet.getString(1);
            stu.name = resultSet.getString(2);
            stu.tel = resultSet.getString(3);
            stus.add(stu);
        }
        log.setStu(stus);

        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        Date nowTime = new Date();
        String t = time.format(nowTime);
        String sql1 = "select * from log where hnum = ? and time = ?;";
        PreparedStatement pre1 = connection.prepareStatement(sql1);
        pre1.setString(1, home);
        pre1.setString(2, t);
        resultSet = pre1.executeQuery();
        if (resultSet.next()) {
            log.setId(resultSet.getInt(1));
            log.setHome(resultSet.getString(2));
            log.setTime(resultSet.getString(3));
            log.setWsMsg(resultSet.getString(4));
            log.setSafeMsg(resultSet.getString(5));
            log.setWsScore(resultSet.getInt(6));
            log.setSafeScore(resultSet.getInt(7));
        }
        return log;
    }

    public static boolean findLogByHomeAndTime(String home, String t) throws SQLException {
        String sql = "select * from log where hnum = ? and time = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, home);
        pre.setString(2, t);
        ResultSet resultSet = pre.executeQuery();
        return resultSet.next();
    }

    public static boolean insertLog(String home, String t, String ws, String safe) throws SQLException {
        String sql = "insert into log(hnum,time,wsmsg,safemsg) values (?,?,?,?);";
        PreparedStatement pre = connection.prepareStatement(sql);
        //设置参数
        pre.setString(1, home);
        pre.setString(2, t);
        pre.setString(3, ws);
        pre.setString(4, safe);
        int ok = pre.executeUpdate();
        return ok == 1;
    }

    public static boolean isOkLog(int id) throws SQLException {
        String sql = "select * from log where id = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet resultSet = pre.executeQuery();
        boolean ok = false;
        if (resultSet.next()) {
            ok = resultSet.getInt(6) != -1 || resultSet.getInt(7) != -1;
        }
        return ok;
    }

    public static boolean updateLog(int id, int w, int s) throws SQLException {
        String sql = "update log set wsscore = ? where id = ?;";
        PreparedStatement pre = connection.prepareStatement(sql);
        //设置参数
        pre.setInt(1, w);
        pre.setInt(2, id);
        int ok = pre.executeUpdate();

        String sql1 = "update log set safescore = ? where id = ?;";
        PreparedStatement p1 = connection.prepareStatement(sql1);
        p1.setInt(1, s);
        p1.setInt(2, id);
        int ok1 = p1.executeUpdate();

        return ok == 1 && ok1 == 1;
    }
}
