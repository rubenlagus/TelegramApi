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

package org.telegram.api.channel.participants.filters;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel participants filters bots
 * @date 24 of September of 2015
 */
public class TLChannelParticipantsFilterBots extends TLAbsChannelParticipantsFilter {
    public static final int CLASS_ID = 0xb0d1865b;

    public TLChannelParticipantsFilterBots() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "channel.participants.filters.TLChannelParticipantsBots#b0d1865b";
    }
}
