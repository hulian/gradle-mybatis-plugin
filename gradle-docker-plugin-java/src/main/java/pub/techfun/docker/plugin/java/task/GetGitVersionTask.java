package pub.techfun.docker.plugin.java.task;

import pub.techfun.docker.plugin.java.constants.Constants;
import pub.techfun.docker.plugin.java.util.LogUtil;
import org.gradle.api.tasks.Exec;

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
