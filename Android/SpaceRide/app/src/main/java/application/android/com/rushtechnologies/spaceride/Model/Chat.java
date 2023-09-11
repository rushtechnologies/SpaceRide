package application.android.com.rushtechnologies.spaceride.Model;

import java.sql.Timestamp;

public class Chat {

    private int Chat_Id;
    private int Chat_S_Id;
    private int Chat_U_Id;
    private String Chat_U_Msgs;
    private String Chat_S_Msgs;
    private String Chat_Time;
    private Timestamp Chat_Timestamp;

    public Chat() {
    }

    public Chat(int Chat_Id, int Chat_S_Id, int Chat_U_Id, String Chat_U_Msgs, String Chat_S_Msgs, String Chat_Time, Timestamp Chat_Timestamp) {
        this.Chat_Id = Chat_Id;
        this.Chat_S_Id = Chat_S_Id;
        this.Chat_U_Id = Chat_U_Id;
        this.Chat_U_Msgs = Chat_U_Msgs;
        this.Chat_S_Msgs = Chat_S_Msgs;
        this.Chat_Time = Chat_Time;
        this.Chat_Timestamp = Chat_Timestamp;
    }

    public int getChat_Id() {
        return Chat_Id;
    }

    public void setChat_Id(int Chat_Id) {
        this.Chat_Id = Chat_Id;
    }

    public int getChat_S_Id() {
        return Chat_S_Id;
    }

    public void setChat_S_Id(int Chat_S_Id) {
        this.Chat_S_Id = Chat_S_Id;
    }

    public int getChat_U_Id() {
        return Chat_U_Id;
    }

    public void setChat_U_Id(int Chat_U_Id) {
        this.Chat_U_Id = Chat_U_Id;
    }

    public String getChat_U_Msgs() {
        return Chat_U_Msgs;
    }

    public void setChat_U_Msgs(String Chat_U_Msgs) {
        this.Chat_U_Msgs = Chat_U_Msgs;
    }

    public String getChat_S_Msgs() {
        return Chat_S_Msgs;
    }

    public void setChat_S_Msgs(String Chat_S_Msgs) {
        this.Chat_S_Msgs = Chat_S_Msgs;
    }

    public String getChat_Time() {
        return Chat_Time;
    }

    public void setChat_Time(String Chat_Time) {
        this.Chat_Time = Chat_Time;
    }

    public Timestamp getChat_Timestamp() {
        return Chat_Timestamp;
    }

    public void setChat_Timestamp(Timestamp Chat_Timestamp) {
        this.Chat_Timestamp = Chat_Timestamp;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "Chat_Id=" + Chat_Id +
                ", Chat_S_Id=" + Chat_S_Id +
                ", Chat_U_Id=" + Chat_U_Id +
                ", Chat_U_Msgs=" + Chat_U_Msgs +
                ", Chat_S_Msgs=" + Chat_S_Msgs +
                ", Chat_Time=" + Chat_Time +
                ", Chat_Timestamp=" + Chat_Timestamp +
                '}';
    }

}
