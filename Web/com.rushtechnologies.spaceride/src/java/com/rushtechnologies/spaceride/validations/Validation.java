package com.rushtechnologies.spaceride.validations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Carlos HG
 */
public class Validation {
    public boolean isStringValid(String string) {
        return string != null && !string.equals("");
    }
    
    public boolean isPasswdValid(String passwd) {
        return passwd != null && !passwd.equals("") && passwd.length() >= 8;
    }
    
    public boolean isIdValidFromSession(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch(NumberFormatException e) {
            System.out.println("Error at isIdValidFromSession: " + e.getMessage());
            return false;
        }
    }
    
    public boolean isCaptchaValid(String response) {
        String secretKey = "6LdM9pEUAAAAAKeBxFReNsZqjLypCcU5clN9pWlD";
        try {
            String url = "https://www.google.com/recaptcha/api/siteverify?"
                    + "secret=" + secretKey
                    + "&response=" + response;
            String jsonText;
            try (InputStream res = new URL(url).openStream()) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(res, Charset.forName("UTF-8")));
                StringBuilder sb = new StringBuilder();
                int cp;
                while ((cp = rd.read()) != -1) {
                    sb.append((char) cp);
                }
                jsonText = sb.toString();
            }
            JSONObject json = new JSONObject(jsonText);
            return json.getBoolean("success");
        } catch (IOException | JSONException e) {
            return false;
        }
    }
    
}
