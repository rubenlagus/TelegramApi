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
package org.telegram.bot.structure;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 28 of February of 2016
 */
public enum LoginStatus {
    CODESENT,
    ALREADYLOGGED,
    ERRORSENDINGCODE,
    UNEXPECTEDERROR,
    INVALIDPHONENUMBER,
    BOTLOGIN,
    BOTLOGINERROR
}
