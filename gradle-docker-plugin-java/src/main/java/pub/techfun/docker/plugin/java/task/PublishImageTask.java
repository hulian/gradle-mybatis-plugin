package pub.techfun.docker.plugin.java.task;

import pub.techfun.docker.plugin.java.constants.Constants;
import pub.techfun.docker.plugin.java.util.ImageNameUtil;
import pub.techfun.docker.plugin.java.util.LogUtil;
import pub.techfun.docker.plugin.java.util.PropertyUtil;
import org.gradle.api.tasks.Exec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author henry
 */
public class PublishImageTask extends Exec {

	public static final String TASK_NAME = "publishImage";

	public PublishImageTask(){
		dependsOn(getProject().getTasks().getByName(RemoveContainerTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
		workingDir(super.getProject().getBuildDir().getPath() + Constants.DOCKER_FOLDER);
	}

	@Override
	protected void exec() {
		String imageName = ImageNameUtil.getImageName(getProject());
		var list = new ArrayList<String>();
		if(PropertyUtil.hasDeployHost(getProject())) {
			String host = PropertyUtil.getDeployHost(getProject());
			LogUtil.logLifeCycle(super.getLogger(),"发布Docker镜像:"+imageName+" 到:"+host);
			assert host != null;
			list.addAll(List.of("ssh", host));
		}else{
			LogUtil.logLifeCycle(super.getLogger(),"发布Docker镜像:"+imageName);
		}
		list.addAll(List.of(
			"docker", "run",
			"--name", getProject().getName(),
			"--network", "host",
			"--restart", "always",
			"-v", "/var/logs:/var/logs",
			"-d", imageName));
		if(PropertyUtil.hasArgs(getProject())){
			list.add(Objects.requireNonNull(PropertyUtil.getArgs(getProject())));
		}
		LogUtil.logLifeCycle(super.getLogger(),"发布Docker镜像,命令行:"+ list);
		commandLine(list);
		super.exec();
	}
}
