/*
 * Created on 26 apr 2010
 */

package jawnae.pyronet;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface PyroClientListener
{
   void connectedClient(PyroClient client);

   void unconnectableClient(PyroClient client, Exception cause);

   void droppedClient(PyroClient client, IOException cause);

   void disconnectedClient(PyroClient client);

   void receivedData(PyroClient client, ByteBuffer data);

   void sentData(PyroClient client, int bytes);
}