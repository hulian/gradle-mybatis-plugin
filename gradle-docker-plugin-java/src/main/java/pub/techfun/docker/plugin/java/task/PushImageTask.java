package pub.techfun.docker.plugin.java.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.docker.plugin.common.constants.Constants;
import pub.techfun.docker.plugin.common.util.ImageNameUtil;
import pub.techfun.docker.plugin.common.util.LogUtil;
import pub.techfun.docker.plugin.common.util.PropertyUtil;
import pub.techfun.docker.plugin.java.client.DockerCmdClient;

/**
 * @author henry
 */
public class PushImageTask extends DefaultTask {

	public static final String TASK_NAME = "pushImage";

	public PushImageTask(){
		dependsOn(getProject().getTasks().getByName(BuildImageTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
	}

	@TaskAction
	protected void exec() {
		if(PropertyUtil.hasRegistryHost(getProject())) {
			String imageName = ImageNameUtil.getImageName(getProject(), GetGitVersionTask.getGitVersion());
			LogUtil.logLifeCycle(super.getLogger(),"推送Docker镜像:"+imageName);
			DockerCmdClient.pushImage(getLogger(), imageName);
		}else{
			LogUtil.logLifeCycle(super.getLogger(),"没有设置远程仓库地址，不推送镜像!");
		}
	}
}
