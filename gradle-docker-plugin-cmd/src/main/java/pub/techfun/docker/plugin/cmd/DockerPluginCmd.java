/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package pub.techfun.docker.plugin.cmd;

import pub.techfun.docker.plugin.cmd.constants.Constants;
import carbon.docker.plugin.cmd.task.*;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import pub.techfun.docker.plugin.cmd.task.*;

/**
 * @author henry
 */
public class DockerPluginCmd implements Plugin<Project> {
    public void apply(Project project) {
        // Register a task

		var createDockerFolder = project.getTasks()
			.register( CreateDockerFolderTask.TASK_NAME, CreateDockerFolderTask.class)
			.get();
		createDockerFolder.setGroup(Constants.GROUP_NAME);

		var copyTask = project.getTasks()
			.register( CopyDependenciesTask.TASK_NAME, CopyDependenciesTask.class).get();
		project.getTasks().getByName("build").dependsOn(copyTask);

		project.getTasks().register( CopyJarTask.TASK_NAME, CopyJarTask.class);

		project.getTasks().register( CopyDockerFileTask.TASK_NAME, CopyDockerFileTask.class);

		project.getTasks().register( CreateDockerFileTask.TASK_NAME, CreateDockerFileTask.class);

		project.getTasks().register( GetGitVersionTask.TASK_NAME, GetGitVersionTask.class);

		project.getTasks().register( BuildImageTask.TASK_NAME, BuildImageTask.class);

		project.getTasks().register( PushImageTask.TASK_NAME, PushImageTask.class);

		project.getTasks().register( StopContainerTask.TASK_NAME, StopContainerTask.class);

		project.getTasks().register( RemoveContainerTask.TASK_NAME, RemoveContainerTask.class);

		project.getTasks().register( PublishImageTask.TASK_NAME, PublishImageTask.class);

    }
}
