package pub.techfun.docker.plugin.java.task;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.docker.plugin.common.constants.Constants;
import pub.techfun.docker.plugin.common.task.CopyJarTask;
import pub.techfun.docker.plugin.common.util.LogUtil;

import java.io.IOException;

/**
 * @author henry
 */
public class GetGitVersionTask extends DefaultTask {

	public static final String TASK_NAME = "getGitVersion";
	private static String gitVersion = "";

	public GetGitVersionTask(){
		dependsOn(getProject().getTasks().getByName(CopyJarTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
	}

	@TaskAction
	protected void exec() {
		try (Git git = Git.open(getProject().getRootDir())){
			gitVersion = git.describe().setAlways(true).call();
			LogUtil.logLifeCycle(super.getLogger(),"输出Git版本号:" + gitVersion);
		} catch (IOException | GitAPIException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getGitVersion() {
		return gitVersion;
	}
}
