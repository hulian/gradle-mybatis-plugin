package pub.techfun.docker.plugin.cmd.task;

import org.gradle.api.tasks.Exec;
import pub.techfun.docker.plugin.common.constants.Constants;
import pub.techfun.docker.plugin.common.task.CopyDockerFileTask;
import pub.techfun.docker.plugin.common.task.CopyJarTask;
import pub.techfun.docker.plugin.common.task.CreateDockerFileTask;
import pub.techfun.docker.plugin.common.util.ImageNameUtil;
import pub.techfun.docker.plugin.common.util.LogUtil;

/**
 * @author henry
 */
public class BuildImageTask extends Exec {

	public static final String TASK_NAME = "buildImage";

	public BuildImageTask(){
		dependsOn(getProject().getTasks().getByName(CopyJarTask.TASK_NAME));
		dependsOn(getProject().getTasks().getByName(CopyDockerFileTask.TASK_NAME));
		dependsOn(getProject().getTasks().getByName(CreateDockerFileTask.TASK_NAME));
		dependsOn(getProject().getTasks().getByName(GetGitVersionTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
		workingDir(super.getProject().getBuildDir().getPath() + Constants.DOCKER_FOLDER);
	}

	@Override
	protected void exec() {
		String imageName = ImageNameUtil.getImageName(getProject());
		LogUtil.logLifeCycle(super.getLogger(),"制作Docker镜像:"+imageName);
		commandLine("docker", "build", "./", "-t", imageName);
		super.exec();
	}
}
