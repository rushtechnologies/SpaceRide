package application.android.com.rushtechnologies.spaceride.Model;

public class User {

    private int u_id;
    private String u_name;
    private String u_passwd;
    private String u_email;
    private int u_type;
    private int u_wins;
    private int u_loses;

    public User() {
    }

    public User(int u_id, String u_name, String u_passwd, String u_email, int u_type, int u_wins, int u_loses) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_passwd = u_passwd;
        this.u_email = u_email;
        this.u_type = u_type;
        this.u_wins = u_wins;
        this.u_loses = u_loses;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public void setU_passwd(String u_passwd) {
        this.u_passwd = u_passwd;
    }

    public int getU_id() {
        return u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public String getU_passwd() {
        return u_passwd;
    }

    public String getU_email() {
        return u_email;
    }

    public int getU_type() {
        return u_type;
    }

    public int getU_wins() {
        return u_wins;
    }

    public int getU_loses() {
        return u_loses;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id=" + u_id +
                ", u_name='" + u_name + '\'' +
                ", u_email='" + u_email + '\'' +
                ", u_passwd='" + u_passwd + '\'' +
                ", u_type=" + u_type +
                ", u_wins=" + u_wins +
                ", u_loses=" + u_loses +
                '}';
    }

}
