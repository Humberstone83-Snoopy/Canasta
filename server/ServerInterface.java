package server;

import java.io.IOException;

public interface ServerInterface {
  void acceptClients() throws IOException;
  
  void log(String paramString);
  
  void stop() throws IOException;
}


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\server\ServerInterface.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */