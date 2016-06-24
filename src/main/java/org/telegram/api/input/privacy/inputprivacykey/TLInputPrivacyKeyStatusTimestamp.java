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

/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 21/11/14.
 */
package org.telegram.api.input.privacy.inputprivacykey;

/**
 * The type TL input privacy key status timestamp.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLInputPrivacyKeyStatusTimestamp
 * @date 21 /11/14
 */
public class TLInputPrivacyKeyStatusTimestamp extends TLAbsInputPrivacyKey {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4f96cb18;

    /**
     * Instantiates a new TL input privacy key status timestamp.
     */
    public TLInputPrivacyKeyStatusTimestamp() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPrivacyKeyStatusTimestamp#4f96cb18";
    }
}
