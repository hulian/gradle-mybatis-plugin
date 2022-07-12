package pub.techfun.docker.plugin.cmd.task;

import pub.techfun.docker.plugin.cmd.constants.Constants;
import pub.techfun.docker.plugin.cmd.util.LogUtil;
import org.gradle.api.tasks.Copy;

/**
 * @author henry
 */
public class CopyDockerFileTask extends Copy {

	public static final String TASK_NAME = "copyDockerFile";
	private final String from;

	public CopyDockerFileTask(){
		from = getProject().getProjectDir().getPath() + Constants.DOCKER_FOLDER;
		dependsOn(getProject().getTasks().getByName(CopyJarTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
		into(getProject().getBuildDir().getPath() + Constants.DOCKER_FOLDER);
		from(from);
	}

	@Override
	protected void copy() {
		LogUtil.logLifeCycle(super.getLogger(),"复制Docker配置目录:"+from);
		super.copy();
	}
}
