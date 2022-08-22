package pub.techfun.mybatis.plugin.common.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.mybatis.plugin.common.constants.Constants;
import pub.techfun.mybatis.plugin.common.util.FileResourcesUtils;
import pub.techfun.mybatis.plugin.common.util.LogUtil;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author henry
 */
public class CreateConfigFileTask extends DefaultTask {

	public static final String TASK_NAME = "createConfigFile";
	public static String TYPE;
	private final String configFrom;
	private final String driverFrom;

	public CreateConfigFileTask(){
		configFrom = getProject().getProjectDir().getPath() +"/"+ Constants.CONFIG_FOLDER;
		driverFrom = getProject().getProjectDir().getPath() +"/"+ Constants.DRIVER_FOLDER;
		dependsOn(getProject().getTasks().getByName(CopyConfigFileTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
	}

	@TaskAction
	protected void copy() throws IOException {
		var file = Paths.get(driverFrom);
		if(!Files.exists(file)) {
			LogUtil.logLifeCycle(getLogger(),"未发现driver目录,从classpath复制:"+driverFrom);
			FileResourcesUtils.copy(getLogger(), Constants.CONFIG_FOLDER + "-driver",
					getProject().getBuildDir().getPath() + "/" + Constants.DRIVER_FOLDER
			);
		}
		file = Paths.get(configFrom);
		if(!Files.exists(file)){
			LogUtil.logLifeCycle(getLogger(),"未配置Config目录,从classpath复制");
			FileResourcesUtils.copy(getLogger(), Constants.CONFIG_FOLDER+"-"+TYPE,
					getProject().getBuildDir().getPath() + "/" + Constants.CONFIG_FOLDER
			);
		}else{
			LogUtil.logLifeCycle(getLogger(),"有配置Config目录,从Config目录:"+configFrom);
			Files.walkFileTree(file,new SimpleFileVisitor<>(){
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Files.copy(
							file,
							Paths.get(getProject().getBuildDir().getPath() , Constants.CONFIG_FOLDER ,
									file.getFileName().toString()),
							StandardCopyOption.REPLACE_EXISTING
					);
					return super.visitFile(file, attrs);
				}
			});
		}
	}
}
