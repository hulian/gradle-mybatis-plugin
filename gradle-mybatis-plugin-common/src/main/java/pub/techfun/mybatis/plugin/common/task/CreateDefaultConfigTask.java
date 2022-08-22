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
public class CreateDefaultConfigTask extends DefaultTask {

	public static final String TASK_NAME = "createDefaultConfig";
	public static String TYPE;
	private final String configFrom;

	public CreateDefaultConfigTask(){
		configFrom = getProject().getProjectDir().getPath() +"/"+ Constants.CONFIG_FOLDER;
		dependsOn(getProject().getTasks().getByName(CopyConfigFileTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
	}

	@TaskAction
	protected void copy() {
		var file = Paths.get(configFrom);
		if(!Files.exists(file)){
			LogUtil.logLifeCycle(getLogger(),"生成默认配置,从classpath复制:"+configFrom);
			FileResourcesUtils.copy(getLogger(), Constants.CONFIG_FOLDER+"-"+TYPE,
					getProject().getProjectDir().getPath() + "/" + Constants.CONFIG_FOLDER
			);
		}
	}
}
