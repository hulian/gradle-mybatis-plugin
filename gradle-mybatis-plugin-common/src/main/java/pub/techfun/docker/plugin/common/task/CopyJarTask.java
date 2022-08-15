package pub.techfun.docker.plugin.common.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.docker.plugin.common.constants.Constants;
import pub.techfun.docker.plugin.common.util.LogUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author henry
 */
public class CopyJarTask extends DefaultTask {

	public static final String TASK_NAME = "copyJar";

	public CopyJarTask() {
		dependsOn(getProject().getTasks().getByName("build"));
		setGroup(Constants.GROUP_NAME);
	}

	@TaskAction
	protected void copy() {
		LogUtil.logLifeCycle(super.getLogger(),"复制代码jar包");
		Project project = getProject();
		var src = Paths.get(project.getBuildDir().getPath() + "/libs/" +
			project.getName() + "-" +project.getVersion() + ".jar");
		var dist = Paths.get(project.getBuildDir().getPath() + Constants.DOCKER_FOLDER + "/app.jar");
		try {
			Files.copy(src, dist, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
