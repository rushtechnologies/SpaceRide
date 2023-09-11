package com.rushtechnologies.spaceride.ImpDAO;

import com.rushtechnologies.spaceride.DAO.iChat;
import com.rushtechnologies.spaceride.Database.Connect;
import com.rushtechnologies.spaceride.Models.Chat;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CARLOSHG
 */
public class ImpChat implements iChat {

    private final Connection connect;
    private CallableStatement call;
    private ResultSet result;
    private final String querycreateChat = "call createChat(?,?)";
    private final String queryreadAllChat = "call readAllChat(?,?)";
    private final String queryreadSAllChats = "call readSAllChats(?)";
    private final String queryreadChatTime = "call readChatTime(?)";
    private final String queryupdateChat_S_Msgs = "call updateChat_S_Msgs(?,?,?)";
    private final String queryupdateChat_U_Msgs = "call updateChat_U_Msgs(?,?,?)";
    private final String queryupdateChatTime = "call updateChatTime(?,?)";

    public ImpChat() {
        Connect connection = new Connect();
        this.connect = connection.getConnect();
    }

    @Override
    public boolean createChat(int s_id, int u_id) {

        boolean create = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(querycreateChat);
            call.setInt(1, s_id);
            call.setInt(2, u_id);
            result = call.executeQuery();
            if (result.next()) {
                create = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at createChat: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at createChat: " + e.getMessage());
                } finally {
                }
            }
        }

        return create;

    }

    @Override
    public Chat readAllChat(int s_id, int u_id) {

        Chat chat = null;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryreadAllChat);
            call.setInt(1, s_id);
            call.setInt(2, u_id);
            result = call.executeQuery();
            if (result.next()) {
                chat = new Chat();
                chat.setChat_Id(result.getInt(1));
                chat.setChat_S_Id(result.getInt(2));
                chat.setChat_U_Id(result.getInt(3));
                chat.setChat_U_Msgs(result.getString(4));
                chat.setChat_S_Msgs(result.getString(5));
                chat.setChat_Time(result.getString(6));
                chat.setChat_Timestamp(result.getTimestamp(7));
            }
        } catch (SQLException e) {
            System.out.println("Error at readAllChat: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at readAllChat: " + e.getMessage());
                } finally {
                }
            }
        }
        
        return chat;

    }

    @Override
    public List<Chat> readSAllChats(int s_id) {

        List<Chat> chats = new ArrayList<>();
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryreadSAllChats);
            call.setInt(1, s_id);
            result = call.executeQuery();
            while (result.next()) {
                Chat chat = new Chat();
                chat.setChat_Id(result.getInt(1));
                chat.setChat_S_Id(result.getInt(2));
                chat.setChat_U_Id(result.getInt(3));
                chat.setChat_U_Msgs(result.getString(4));
                chat.setChat_S_Msgs(result.getString(5));
                chat.setChat_Time(result.getString(6));
                chat.setChat_Timestamp(result.getTimestamp(7));
                chats.add(chat);
            }
        } catch (SQLException e) {
            System.out.println("Error at readSAllChats: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at readSAllChats: " + e.getMessage());
                } finally {
                }
            }
        }
        
        return chats;

    }

    @Override
    public String readChatTime(int id) {

        String time = null;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryreadChatTime);
            call.setInt(1, id);
            result = call.executeQuery();
            if (result.next()) {
                time = result.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at readChatTime: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at readChatTime: " + e.getMessage());
                } finally {
                }
            }
        }
        return time;

    }

    @Override
    public boolean updateChat_S_Msgs(int s_id, int u_id, String s_msgs) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateChat_S_Msgs);
            call.setInt(1, s_id);
            call.setInt(2, u_id);
            call.setString(3, s_msgs);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateChat_S_Msgs: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateChat_S_Msgs: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateChat_U_Msgs(int s_id, int u_id, String u_msgs) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateChat_U_Msgs);
            call.setInt(1, s_id);
            call.setInt(2, u_id);
            call.setString(3, u_msgs);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateChat_U_Msgs: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateChat_U_Msgs: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateChatTime(int id, String time) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateChatTime);
            call.setInt(1, id);
            call.setString(2, time);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateChatTime: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateChatTime: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

}
