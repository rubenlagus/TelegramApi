/*
 * Created on 26 apr 2010
 */

package jawnae.pyronet;

import org.telegram.bot.services.BotLogger;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PyroClientAdapter implements PyroClientListener {
   private static final String LOGTAG = "PYROCLIENTADAPTER";

   public void connectedClient(PyroClient client)
   {
      //
   }

   @Override
   public void unconnectableClient(PyroClient client, Exception cause) {
      //
   }

   public void unconnectableClient(PyroClient client)
   {
      BotLogger.debug(LOGTAG, "unconnectable");
   }

   public void droppedClient(PyroClient client, IOException cause)
   {
      if (cause != null)
      {
         BotLogger.debug(LOGTAG, this.getClass().getSimpleName() + ".droppedClient() caught exception: " + cause);
      }
   }

   public void disconnectedClient(PyroClient client)
   {
      //
   }

   //

   public void receivedData(PyroClient client, ByteBuffer data)
   {
      //
   }

   public void sentData(PyroClient client, int bytes)
   {
      //
   }
}