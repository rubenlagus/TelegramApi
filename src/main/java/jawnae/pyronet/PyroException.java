/*
 * Created on 3 dec 2009
 */

package jawnae.pyronet;

public class PyroException extends RuntimeException
{
   public PyroException()
   {
      super();
   }

   public PyroException(String msg)
   {
      super(msg);
   }

   public PyroException(String msg, Throwable cause)
   {
      super(msg, cause);
   }
}
