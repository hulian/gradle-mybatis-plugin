package pub.techfun.mybatis.plugin.common.task;

import org.gradle.api.tasks.Copy;
import pub.techfun.mybatis.plugin.common.constants.Constants;
import pub.techfun.mybatis.plugin.common.util.LogUtil;

/**
 * @author henry
 */
public class CopyConfigFileTask extends Copy {

	public static final String TASK_NAME = "copyDockerFile";
	private final String from;

	public CopyConfigFileTask(){
		from = getProject().getProjectDir().getPath() + Constants.CONFIG_FOLDER;
		dependsOn(getProject().getTasks().getByName(CreateConfigFolderTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
		into(getProject().getBuildDir().getPath() + Constants.CONFIG_FOLDER);
		from(from);
	}

	@Override
	protected void copy() {
		LogUtil.logLifeCycle(super.getLogger(),"复制MYBATIS配置目录:"+from);
		super.copy();
	}
}
