package pub.techfun.docker.plugin.cmd.task;

import pub.techfun.docker.plugin.cmd.constants.Constants;
import pub.techfun.docker.plugin.cmd.util.LogUtil;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author henry
 */
public class CreateDockerFolderTask extends DefaultTask {

	public static final String TASK_NAME = "createDockerFolder";

	@TaskAction
	protected void exec() {
		LogUtil.logLifeCycle(super.getLogger(),"创建Docker目录");
		var path = Paths.get(super.getProject().getBuildDir().getPath() + Constants.DOCKER_FOLDER);
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
