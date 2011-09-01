package uk.org.whoami.authme.settings;

import java.io.File;
import java.util.HashMap;
import org.bukkit.util.config.Configuration;

public class Messages extends Configuration {
    
    private static Messages singleton = null;
    private HashMap<String, String> map;

    private Messages() {
        super(new File(Settings.MESSAGE_FILE));
        map = new HashMap<String, String>();
        loadDefaults();
        loadFile();
    }
    
    private void loadDefaults() {
        map.put("Already logged in!", "Already logged in!");
        map.put("Not logged in!", "Not logged in!");
        map.put("Registration is disabled","Registration is disabled");
        map.put("Username already registered", "Username already registered");
        map.put("Usage: /register password", "Usage: /register password");
        map.put("Usage: /login password", "Usage: /login password");
        map.put("Username not registered", "Username not registered");
        map.put("Password changed", "Password changed");
        map.put("Wrong old password", "Wrong old password");
        map.put("Registered players only! Please visit http://example.com to register","Registered players only! Please visit http://example.com to register");
        map.put("Valid session detected: AutoLogin", "Valid session detected: AutoLogin");
        map.put("Please login with \"/login password\"", "Please login with \"/login password\"");
        map.put("Please register with \"/register password\"", "Please register with \"/register password\"");
        map.put("Login Timeout", "Login Timeout");
    }

    private void loadFile() {
        this.load();
        for(String key : map.keySet()) {
            if(this.getString(key) == null) {
                this.setProperty(key, map.get(key));
            } else {
                map.put(key, this.getString(key));
            }
        }
        this.save();
    }
    
    public String _(String msg) {
        String loc = map.get(msg);
        if(loc != null) {
            return loc;
        }
        return msg;
    }
    
    public static Messages getInstance() {
        if(singleton == null) {
            singleton = new Messages();
        }
        return singleton;
    }
}
