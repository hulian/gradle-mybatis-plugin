package pub.techfun.mybatis.plugin.common.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.mybatis.plugin.common.constants.Constants;
import pub.techfun.mybatis.plugin.common.util.FileResourcesUtils;
import pub.techfun.mybatis.plugin.common.util.LogUtil;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author henry
 */
public class CreateConfigFileTask extends DefaultTask {

	public static final String TASK_NAME = "createConfigFile";
	private final String from;

	public CreateConfigFileTask(){
		from = getProject().getProjectDir().getPath() + Constants.CONFIG_FOLDER;
		dependsOn(getProject().getTasks().getByName(CreateConfigFolderTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
	}

	@TaskAction
	protected void copy() {
		var file = Paths.get(from);
		if(!Files.exists(file)){
			LogUtil.logLifeCycle(getLogger(),"未配置Config目录,从classpath复制:"+from);
			FileResourcesUtils.copy(getLogger(), Constants.CONFIG_FOLDER,
				getProject().getProjectDir().getPath() + "/" + Constants.CONFIG_FOLDER
			);
		}
	}
}
