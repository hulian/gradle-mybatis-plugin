package pub.techfun.docker.plugin.cmd.task;

import org.gradle.api.tasks.Exec;
import pub.techfun.docker.plugin.common.constants.Constants;
import pub.techfun.docker.plugin.common.util.LogUtil;
import pub.techfun.docker.plugin.common.util.PropertyUtil;

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
		var list = new ArrayList<String>();
		if(PropertyUtil.hasDeployHost(getProject())) {
			String host = PropertyUtil.getDeployHost(getProject());
			LogUtil.logLifeCycle(super.getLogger(),"删除容器:"+containerName+" 在:"+host);
			assert host != null;
			list.addAll(List.of("ssh", "-y", host));
		}else{
			LogUtil.logLifeCycle(super.getLogger(),"删除容器:"+containerName);
		}
		list.addAll(List.of("docker", "rm", containerName));
		LogUtil.logLifeCycle(super.getLogger(),"删除容器,命令行:"+ list);
		commandLine(list);
		super.exec();
	}
}
