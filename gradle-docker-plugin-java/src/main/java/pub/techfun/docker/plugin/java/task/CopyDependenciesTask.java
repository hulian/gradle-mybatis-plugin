package pub.techfun.docker.plugin.java.task;

import pub.techfun.docker.plugin.java.constants.Constants;
import pub.techfun.docker.plugin.java.util.LogUtil;
import org.gradle.api.tasks.Copy;

/**
 * @author henry
 */
public class CopyDependenciesTask extends Copy {

	public static final String TASK_NAME = "copyDependencies";

	public CopyDependenciesTask(){
		dependsOn(getProject().getTasks().getByName(CreateDockerFolderTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
		into(super.getProject().getBuildDir().getPath() + "/docker/libs");
		from(super.getProject().getConfigurations().getByName("runtimeClasspath"));
	}

	@Override
	protected void copy() {
		LogUtil.logLifeCycle(super.getLogger(),"复制依赖库");
		super.copy();
	}
}
