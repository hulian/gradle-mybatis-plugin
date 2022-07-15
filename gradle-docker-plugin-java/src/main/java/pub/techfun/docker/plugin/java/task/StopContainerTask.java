package pub.techfun.docker.plugin.java.task;

import org.gradle.api.tasks.Exec;
import pub.techfun.docker.plugin.common.constants.Constants;
import pub.techfun.docker.plugin.common.util.LogUtil;
import pub.techfun.docker.plugin.common.util.PropertyUtil;
import pub.techfun.docker.plugin.java.ssh.SshShell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author henry
 */
public class StopContainerTask extends Exec {

	public static final String TASK_NAME = "stopContainer";

	public StopContainerTask(){
		dependsOn(getProject().getTasks().getByName(PushImageTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
		workingDir(super.getProject().getBuildDir().getPath() + Constants.DOCKER_FOLDER);
		setIgnoreExitValue(true);
	}

	@Override
	protected void exec() {
		String containerName = getProject().getName();
		var list = new ArrayList<>(List.of("docker", "stop", containerName));
		LogUtil.logLifeCycle(super.getLogger(),"停止容器,命令行:"+ list);
		if (PropertyUtil.hasDeployHost(getProject())) {
			String host = PropertyUtil.getDeployHost(getProject());
			LogUtil.logLifeCycle(super.getLogger(),"停止容器:"+containerName+" 在:"+host);
			var stdout = SshShell.executeCommand(getLogger(), host, list);
			LogUtil.logLifeCycle(super.getLogger(), stdout);
		}else {
			LogUtil.logLifeCycle(super.getLogger(),"停止容器:"+containerName);
			commandLine(list);
			super.exec();
		}
	}
}
