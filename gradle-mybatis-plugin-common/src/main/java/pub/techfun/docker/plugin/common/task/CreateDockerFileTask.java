package pub.techfun.docker.plugin.common.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.docker.plugin.common.constants.Constants;
import pub.techfun.docker.plugin.common.util.FileResourcesUtils;
import pub.techfun.docker.plugin.common.util.LogUtil;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author henry
 */
public class CreateDockerFileTask extends DefaultTask {

	public static final String TASK_NAME = "createDockerFile";
	private final String from;

	public CreateDockerFileTask(){
		from = getProject().getProjectDir().getPath() + Constants.DOCKER_FOLDER;
		dependsOn(getProject().getTasks().getByName(CopyJarTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
	}

	@TaskAction
	protected void copy() {
		var file = Paths.get(from);
		if(!Files.exists(file)){
			LogUtil.logLifeCycle(getLogger(),"未配置Docker目录,从classpath复制:"+from);
			FileResourcesUtils.copy(getLogger(), Constants.DOCKER_FOLDER,
				getProject().getBuildDir().getPath() + Constants.DOCKER_FOLDER
			);
		}
	}
}
