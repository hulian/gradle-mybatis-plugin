package pub.techfun.docker.plugin.cmd;

import pub.techfun.docker.plugin.java.ssh.SshShell;

import java.util.List;


public class SshTest {


    public static void main(String[] args) {
        System.out.println(SshShell.executeCommand(null, "ssh://henry@localhost:22", List.of("ls", "/")));
    }
}
