package pub.techfun.docker.plugin.java.ssh;

import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.session.ClientSession;
import org.gradle.api.logging.Logger;

import java.rmi.RemoteException;
import java.util.List;

public class SshShell {

    public static String executeCommand(Logger logger,String url, List<String> commands) {
        try (SshClient client = SshClient.setUpDefaultClient()){
            client.start();
            String result;
            try (ClientSession session = client.connect(url).verify(5000).getSession()) {
                session.auth().verify();
                var commandLine = String.join(" ", commands);
                try {
                    result = session.executeRemoteCommand(commandLine);
                } catch (RemoteException e) {
                    result = e.getMessage();
                }
            }
            return "console:" + result;
        }catch (Exception e){
            logger.error("java ssh shell error:", e);
            throw new RuntimeException(e);
        }
    }

}
