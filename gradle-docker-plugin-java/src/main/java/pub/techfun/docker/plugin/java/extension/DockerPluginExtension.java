package pub.techfun.docker.plugin.java.extension;


import groovy.lang.Closure;
import org.gradle.api.Project;

public abstract class DockerPluginExtension {

    private Project project;
    private Docker docker;

    public DockerPluginExtension(Project project){
        this.project = project;
    }

    public Docker docker(Closure<Docker> closure){
        Docker docker = new Docker();
        project.configure(docker, closure);
        this.docker = docker;
        return docker;
    }

    public Docker getDocker() {
        return docker;
    }
}
