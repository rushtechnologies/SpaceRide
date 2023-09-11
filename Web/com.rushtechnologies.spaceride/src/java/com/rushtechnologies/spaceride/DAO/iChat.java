package com.rushtechnologies.spaceride.DAO;

import com.rushtechnologies.spaceride.Models.Chat;
import java.util.List;

/**
 *
 * @author CARLOSHG
 */
public interface iChat {

    public boolean createChat(int s_id, int u_id);

    public Chat readAllChat(int s_id, int u_id);

    public List<Chat> readSAllChats(int s_id);

    public String readChatTime(int id);

    public boolean updateChat_S_Msgs(int s_id, int u_id, String s_msgs);

    public boolean updateChat_U_Msgs(int s_id, int u_id, String u_msgs);

    public boolean updateChatTime(int id, String time);
}
