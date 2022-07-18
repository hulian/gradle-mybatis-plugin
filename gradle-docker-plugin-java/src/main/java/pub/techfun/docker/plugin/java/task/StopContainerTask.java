package pub.techfun.docker.plugin.java.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.docker.plugin.common.constants.Constants;
import pub.techfun.docker.plugin.common.util.LogUtil;
import pub.techfun.docker.plugin.common.util.PropertyUtil;
import pub.techfun.docker.plugin.java.client.DockerCmdClient;
import pub.techfun.docker.plugin.java.ssh.SshShell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author henry
 */
public class StopContainerTask extends DefaultTask {

	public static final String TASK_NAME = "stopContainer";

	public StopContainerTask(){
		dependsOn(getProject().getTasks().getByName(PushImageTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
	}

	@TaskAction
	protected void exec() {
		String containerName = getProject().getName();
		if (PropertyUtil.hasDeployHost(getProject())) {
			var list = new ArrayList<>(List.of("docker", "stop", containerName));
			LogUtil.logLifeCycle(super.getLogger(),"停止容器,命令行:"+ list);
			String host = PropertyUtil.getDeployHost(getProject());
			LogUtil.logLifeCycle(super.getLogger(),"停止容器:"+containerName+" 在:"+host);
			var stdout = SshShell.executeCommand(getLogger(), host, list);
			LogUtil.logLifeCycle(super.getLogger(), stdout);
		}else {
			LogUtil.logLifeCycle(super.getLogger(),"停止容器:"+containerName);
			DockerCmdClient.stopContainer(super.getLogger(), containerName);
		}
	}
}
