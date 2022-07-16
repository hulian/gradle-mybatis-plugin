package pub.techfun.docker.plugin.java.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.docker.plugin.common.constants.Constants;
import pub.techfun.docker.plugin.common.task.CopyDockerFileTask;
import pub.techfun.docker.plugin.common.task.CopyJarTask;
import pub.techfun.docker.plugin.common.task.CreateDockerFileTask;
import pub.techfun.docker.plugin.common.task.GetGitVersionTask;
import pub.techfun.docker.plugin.java.client.DockerCmdClient;

import java.io.File;

/**
 * @author henry
 */
public class BuildImageTask extends DefaultTask {

	public static final String TASK_NAME = "buildImage";

	public BuildImageTask(){
		dependsOn(getProject().getTasks().getByName(CopyJarTask.TASK_NAME));
		dependsOn(getProject().getTasks().getByName(CopyDockerFileTask.TASK_NAME));
		dependsOn(getProject().getTasks().getByName(CreateDockerFileTask.TASK_NAME));
		dependsOn(getProject().getTasks().getByName(GetGitVersionTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
	}

	@TaskAction
	protected void exec() {
		File file = new File(super.getProject().getBuildDir().getPath() + Constants.DOCKER_FOLDER);
		DockerCmdClient.buildImage(getLogger(), file);
	}
}
