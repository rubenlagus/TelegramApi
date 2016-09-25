package org.telegram.bot.structure;

import java.io.Serializable;

/**
 * @author Hendrik Hofstadt
 * @version 2.0
 * @brief Config of the bot
 * @date 02.08.2014
 */
public abstract class BotConfig implements Serializable {
    private boolean isRegistered; ///< Whether the bot was registered or not
    private String hashCode;
    private String authfile = ""; ///< Authfile to store login information


    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public String getAuthfile() {
        return authfile;
    }

    public void setAuthfile(String authfile) {
        this.authfile = authfile;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public abstract String getPhoneNumber();
    public abstract String getBotToken();
    public abstract boolean isBot();
}