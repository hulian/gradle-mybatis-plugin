/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package pub.techfun.mybatis.plugin.easydao;


import org.gradle.api.Plugin;
import org.gradle.api.Project;
import pub.techfun.docker.plugin.common.task.CreateConfigFileTask;
import pub.techfun.docker.plugin.common.util.LogUtil;

/**
 * @author henry
 */
public class DockerPluginCmd implements Plugin<Project> {
    public void apply(Project project) {
        // Register a task
		LogUtil.setPluginName("gradle-mybatis-plugin-easydao");
		var createConfigFile = project.getTasks()
			.register( CreateConfigFileTask.TASK_NAME, CreateConfigFileTask.class)
			.get();

    }
}
