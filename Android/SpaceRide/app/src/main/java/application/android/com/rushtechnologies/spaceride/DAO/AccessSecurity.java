package application.android.com.rushtechnologies.spaceride.DAO;

public interface AccessSecurity {

    String encrypt (String toEncrypt);

    String encrypt (int toEncrypt);

    String decrypt (String encrypted);

}
