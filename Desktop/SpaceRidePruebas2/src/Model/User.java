package Model;

/**
 *
 * @author CARLOSHG
 */
public class User {
    
    private int userid;
    private String area;
    private String difficulty;

    public User(int userid, String area, String difficulty) {
        this.userid = userid;
        this.area = area;
        this.difficulty = difficulty;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int user1id) {
        this.userid = user1id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Game{" + "userid=" + userid + ", area=" + area + ", difficulty=" + difficulty + '}';
    }
    
}