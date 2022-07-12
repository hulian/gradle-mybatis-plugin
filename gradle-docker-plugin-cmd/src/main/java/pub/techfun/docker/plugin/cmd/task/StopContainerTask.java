package pub.techfun.docker.plugin.cmd.task;

import pub.techfun.docker.plugin.cmd.util.LogUtil;
import pub.techfun.docker.plugin.cmd.util.PropertyUtil;
import pub.techfun.docker.plugin.cmd.constants.Constants;
import org.gradle.api.tasks.Exec;

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
		var list = new ArrayList<String>();
		if(PropertyUtil.hasDeployHost(getProject())) {
			String host = PropertyUtil.getDeployHost(getProject());
			LogUtil.logLifeCycle(super.getLogger(),"停止容器:"+containerName+" 在:"+host);
			assert host != null;
			list.addAll(List.of("ssh", host));
		}else{
			LogUtil.logLifeCycle(super.getLogger(),"停止容器:"+containerName);
		}
		list.addAll(List.of("docker", "stop", containerName));
		LogUtil.logLifeCycle(super.getLogger(),"停止容器,命令行:"+ list);
		commandLine(list);
		super.exec();
	}
}
