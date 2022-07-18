package pub.techfun.docker.plugin.java.extension;

import lombok.Data;

@Data
public class Docker {
    private String url;
    private String username;
    private String password;
    private String workDir;
}