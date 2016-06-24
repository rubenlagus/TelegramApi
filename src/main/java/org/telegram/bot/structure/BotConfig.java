package org.telegram.bot.structure;

import java.io.Serializable;

/**
 * @author Hendrik Hofstadt
 * @version 2.0
 * @brief Config of the bot
 * @date 02.08.2014
 */
public class BotConfig implements Serializable {
    public String languageCode = "en"; ///< Language code
    public String number; ///< Telephone number
    public String hashCode; ///< HashCode of the login
    public boolean isRegistered; ///< Wether the bot was registered or not
    public String authfile = ""; ///< Authfile to store login information
}