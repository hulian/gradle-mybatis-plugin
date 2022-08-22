/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package pub.techfun.mybatis.def;


import org.gradle.api.Plugin;
import org.gradle.api.Project;
import pub.techfun.mybatis.def.task.GenerateFileTask;
import pub.techfun.mybatis.plugin.common.task.CreateConfigFileTask;
import pub.techfun.mybatis.plugin.common.task.CreateDefaultConfigTask;
import pub.techfun.mybatis.plugin.common.util.LogUtil;

/**
 * @author henry
 */
public class MybatisPluginDefault implements Plugin<Project> {
    public void apply(Project project) {
        // Register a task
		LogUtil.setPluginName("gradle-mybatis-plugin-def");
		CreateDefaultConfigTask.TYPE=CreateConfigFileTask.TYPE="default";
		project.getTasks().register(CreateDefaultConfigTask.TASK_NAME, CreateDefaultConfigTask.class);
		project.getTasks().register( CreateConfigFileTask.TASK_NAME, CreateConfigFileTask.class);
		project.getTasks().register(GenerateFileTask.TASK_NAME, GenerateFileTask.class);
    }
}
