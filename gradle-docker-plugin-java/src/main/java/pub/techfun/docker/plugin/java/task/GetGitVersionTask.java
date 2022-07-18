package pub.techfun.docker.plugin.java.task;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.docker.plugin.common.constants.Constants;
import pub.techfun.docker.plugin.common.task.CopyJarTask;
import pub.techfun.docker.plugin.common.util.LogUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author henry
 */
public class GetGitVersionTask extends DefaultTask {

	public static final String TASK_NAME = "getGitVersion";

	public GetGitVersionTask(){
		dependsOn(getProject().getTasks().getByName(CopyJarTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
	}

	@TaskAction
	protected void exec() {
		try {
			String gitFile = getProject().getBuildDir().getPath() + Constants.DOCKER_FOLDER + "/GitVersion";
			Git git = Git.open(getProject().getRootDir());
			String desc = git.describe().setAlways(true).call();
			LogUtil.logLifeCycle(super.getLogger(),"输出Git版本号:" + desc);
			Files.writeString(Path.of(gitFile), desc);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (GitAPIException e) {
			throw new RuntimeException(e);
		}

	}
}
