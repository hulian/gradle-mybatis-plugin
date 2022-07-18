package pub.techfun.docker.plugin.cmd.task;

import org.gradle.api.tasks.Exec;
import pub.techfun.docker.plugin.common.constants.Constants;
import pub.techfun.docker.plugin.common.task.CopyJarTask;
import pub.techfun.docker.plugin.common.util.LogUtil;

/**
 * @author henry
 */
public class GetGitVersionTask extends Exec {

	public static final String TASK_NAME = "getGitVersion";

	public GetGitVersionTask(){
		dependsOn(getProject().getTasks().getByName(CopyJarTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
		workingDir(getProject().getBuildDir().getPath() + Constants.DOCKER_FOLDER);
		commandLine("bash", "-c", "git describe --always --dirty > GitVersion");
	}

	@Override
	protected void exec() {
		LogUtil.logLifeCycle(super.getLogger(),"输出Git版本号");
		super.exec();
	}
}
