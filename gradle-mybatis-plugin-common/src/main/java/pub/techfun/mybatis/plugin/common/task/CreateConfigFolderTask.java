package pub.techfun.mybatis.plugin.common.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.mybatis.plugin.common.constants.Constants;
import pub.techfun.mybatis.plugin.common.util.LogUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author henry
 */
public class CreateConfigFolderTask extends DefaultTask {

	public static final String TASK_NAME = "createConfigFolder";

	public CreateConfigFolderTask(){
		setGroup(Constants.GROUP_NAME);
	}

	@TaskAction
	protected void exec() {
		LogUtil.logLifeCycle(super.getLogger(),"创建Config目录");
		var path = Paths.get(super.getProject().getProjectDir().getPath()
				+ "/" +Constants.CONFIG_FOLDER);
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
