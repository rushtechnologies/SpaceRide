package application.android.com.rushtechnologies.spaceride.DAO;

import application.android.com.rushtechnologies.spaceride.Model.User;

public interface AccessUser {

    boolean createUser(User user);

    int[] loginUser(String u_nombre, String u_contra);

    User readUser(int u_id);

    boolean updateUserData(User usuario);

    boolean updateUserPsd(int u_id, String u_contra);
}
