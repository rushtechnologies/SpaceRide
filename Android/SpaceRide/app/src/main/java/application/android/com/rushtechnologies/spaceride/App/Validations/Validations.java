package application.android.com.rushtechnologies.spaceride.App.Validations;

import android.text.TextUtils;
import android.util.Patterns;

public class Validations {

    public Validations() {
    }

    public boolean isValidEntry(String entry) {
        return !TextUtils.isEmpty(entry);
    }

    public boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isValidPasswd(String passwd) {
        return passwd.length() >= 8;
    }

    public boolean entriesMatch(String firstEntry, String secondEntry) {
        return firstEntry.equals(secondEntry);
    }

}
