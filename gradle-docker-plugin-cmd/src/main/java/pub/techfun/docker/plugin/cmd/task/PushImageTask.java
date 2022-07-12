package pub.techfun.docker.plugin.cmd.task;

import pub.techfun.docker.plugin.cmd.constants.Constants;
import pub.techfun.docker.plugin.cmd.util.ImageNameUtil;
import pub.techfun.docker.plugin.cmd.util.LogUtil;
import pub.techfun.docker.plugin.cmd.util.PropertyUtil;
import org.gradle.api.tasks.Exec;

/**
 * @author henry
 */
public class PushImageTask extends Exec {

	public static final String TASK_NAME = "pushImage";

	public PushImageTask(){
		dependsOn(getProject().getTasks().getByName(BuildImageTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
		workingDir(super.getProject().getBuildDir().getPath() + Constants.DOCKER_FOLDER);
	}

	@Override
	protected void exec() {
		if(PropertyUtil.hasRegistryHost(getProject())) {
			String imageName = ImageNameUtil.getImageName(getProject());
			LogUtil.logLifeCycle(super.getLogger(),"推送Docker镜像:"+imageName);
			commandLine("docker", "push", imageName);
		}else{
			commandLine("echo", "'没有设置远程仓库地址，不推送镜像！'");
		}
		super.exec();
	}
}
