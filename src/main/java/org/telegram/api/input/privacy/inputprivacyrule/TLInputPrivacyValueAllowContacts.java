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

package org.telegram.api.input.privacy.inputprivacyrule;

/**
 * The type TL input privacy value allow contacts.
 */
public class TLInputPrivacyValueAllowContacts extends TLAbsInputPrivacyRule {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd09e07b;

    /**
     * Instantiates a new TL input privacy value allow contacts.
     */
    public TLInputPrivacyValueAllowContacts() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputPrivacyValueAllowContacts#d09e07b";
    }
}