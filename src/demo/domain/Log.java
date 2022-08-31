package demo.domain;

import java.util.List;

public class Log {
    private int id;
    private String home;
    private List<Stu> stu;
    private String time;
    private String wsMsg;
    private String safeMsg;
    private int wsScore;
    private int safeScore;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public List<Stu> getStu() {
        return stu;
    }

    public void setStu(List<Stu> stu) {
        this.stu = stu;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWsMsg() {
        return wsMsg;
    }

    public void setWsMsg(String wsMsg) {
        this.wsMsg = wsMsg;
    }

    public String getSafeMsg() {
        return safeMsg;
    }

    public void setSafeMsg(String safeMsg) {
        this.safeMsg = safeMsg;
    }

    public int getWsScore() {
        return wsScore;
    }

    public void setWsScore(int wsScore) {
        this.wsScore = wsScore;
    }

    public int getSafeScore() {
        return safeScore;
    }

    public void setSafeScore(int safeScore) {
        this.safeScore = safeScore;
    }

    public static class Stu {
        public String id;
        public String name;
        public String tel;
    }
}
