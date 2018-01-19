/*
 * jPOS Project [http://jpos.org]
 * Copyright (C) 2000-2017 jPOS Software SRL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.jpos.control;

import org.jpos.core.*;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.ContextConstants;
import org.jpos.transaction.TransactionParticipant;
import java.io.Serializable;

/**
 *
 * @author terrence takunda munyunguma
 */

public class SelectDestination implements TransactionParticipant, Configurable {

	Configuration cfg;

	@Override
	public int prepare(long id, Serializable context) {

		Context ctx = (Context) context;
		ISOMsg m = (ISOMsg) ctx.get(ContextConstants.REQUEST.toString());

		if (m != null && (m.hasField(2) || m.hasField(35))) {

			try {
				Card card = Card.builder().isomsg(m).build();
				String s = cfg.get("bin." + card.getBin(), null);

				if (s != null) {

					ctx.put(ContextConstants.DESTINATION.toString(), s);
				}
			}

			catch (InvalidCardException ignore) {
			// use default destination
			}
		}

	return PREPARED | NO_JOIN | READONLY;
	}

	@Override
	public void setConfiguration (Configuration cfg) {

		this.cfg = cfg;
	}
}
