package pub.techfun.docker.plugin.java.task;

import pub.techfun.docker.plugin.java.constants.Constants;
import pub.techfun.docker.plugin.java.ssh.SshShell;
import pub.techfun.docker.plugin.java.util.LogUtil;
import pub.techfun.docker.plugin.java.util.PropertyUtil;
import org.gradle.api.tasks.Exec;

import java.util.ArrayList;
import java.util.List;

/**
 * @author henry
 */
public class RemoveContainerTask extends Exec {

	public static final String TASK_NAME = "removeContainer";

	public RemoveContainerTask(){
		dependsOn(getProject().getTasks().getByName(StopContainerTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
		workingDir(super.getProject().getBuildDir().getPath() + Constants.DOCKER_FOLDER);
		setIgnoreExitValue(true);
	}

	@Override
	protected void exec() {
		String containerName = getProject().getName();
		var list = new ArrayList<>(List.of("docker", "rm", containerName));
		LogUtil.logLifeCycle(super.getLogger(),"删除容器,命令行:"+ list);
		if (PropertyUtil.hasDeployHost(getProject())) {
			String host = PropertyUtil.getDeployHost(getProject());
			LogUtil.logLifeCycle(super.getLogger(),"删除容器:"+containerName+" 在:"+host);
			var stdout = SshShell.executeCommand(getLogger(), host, list);
			LogUtil.logLifeCycle(super.getLogger(), stdout);
		}else {
			LogUtil.logLifeCycle(super.getLogger(),"删除容器:"+containerName);
			commandLine(list);
			super.exec();
		}
	}
}
