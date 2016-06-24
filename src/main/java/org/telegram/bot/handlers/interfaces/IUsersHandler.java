package org.telegram.bot.handlers.interfaces;

import org.telegram.api.user.TLAbsUser;

import java.util.List;

/**
 * @author Ruben Bermudez
 * @version 2.0
 * @brief Interface for handle received users
 * @date 22 of May of 2015
 */
public interface IUsersHandler {
    void onUsers(List<TLAbsUser> users);
}
