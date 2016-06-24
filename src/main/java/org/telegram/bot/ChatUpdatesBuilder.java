package org.telegram.bot;

import org.telegram.bot.handlers.UpdatesHandlerBase;
import org.telegram.bot.kernel.IKernelComm;
import org.telegram.bot.kernel.database.DatabaseManager;
import org.telegram.bot.kernel.differenceparameters.IDifferenceParametersService;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 12 of April of 2016
 */
public interface ChatUpdatesBuilder {
    UpdatesHandlerBase build() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
    void setKernelComm(IKernelComm kernelComm);
    void setDifferenceParametersService(IDifferenceParametersService differenceParametersService);
    DatabaseManager getDatabaseManager();
}
