/**
 * This file is part of Support Bot.
 *
 *     Foobar is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.telegram.bot.kernel.differenceparameters;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Differences parameters interfaces
 * @date 14 of February of 2016
 */
public interface IDifferenceParametersService {
    int getPts(int chatId);
    int getDate(int chatId);
    int getSeq(int chatId);
    boolean mustGetDifferences(int chatId, int pts, @Nullable Integer ptsCount, int seq, @Nullable Integer seqStart);
    void setNewUpdateParams(int chatId, @Nullable Integer newPts, @Nullable Integer newSeq, @NotNull Integer newDate);
}
