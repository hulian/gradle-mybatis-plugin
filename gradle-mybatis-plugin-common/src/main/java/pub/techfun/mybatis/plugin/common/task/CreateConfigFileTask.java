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
	private final String configBuildFolder;
	private final String driverBuildFolder;
	private final String defaultConfigFrom;

	public CreateConfigFileTask(){
		driverBuildFolder = getProject().getBuildDir().getPath() +"/"+ Constants.DRIVER_FOLDER;
		configBuildFolder = getProject().getBuildDir().getPath() +"/"+ Constants.CONFIG_FOLDER;
		defaultConfigFrom = getProject().getProjectDir().getPath() +"/"+ Constants.CONFIG_FOLDER;
		setGroup(Constants.GROUP_NAME);
	}

	@TaskAction
	protected void copy() throws IOException {
		// 复制驱动
		var driverBuildPath = Paths.get(driverBuildFolder);
		if (!Files.exists(driverBuildPath)) {
			LogUtil.logLifeCycle(getLogger(), "未发现driver目录,从classpath复制:" + driverBuildFolder);
			Files.createDirectories(driverBuildPath);
			FileResourcesUtils.copy(getLogger(), Constants.CONFIG_FOLDER + "-driver", driverBuildFolder);
		}
		// 复制配置文件
		var defaultConfigPath = Paths.get(defaultConfigFrom);
		var configBuildPath = Paths.get(configBuildFolder);
		if (Files.exists(defaultConfigPath)) {
			LogUtil.logLifeCycle(getLogger(), "有配置Config目录,从Config目录复制:" + defaultConfigFrom);
			if (!Files.exists(configBuildPath)) {
				Files.createDirectories(configBuildPath);
			}
			Files.walkFileTree(defaultConfigPath, new SimpleFileVisitor<>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Files.copy(
							file,
							Paths.get(configBuildFolder, file.getFileName().toString()),
							StandardCopyOption.REPLACE_EXISTING
					);
					return super.visitFile(file, attrs);
				}
			});
		} else {
			if (!Files.exists(configBuildPath)) {
				LogUtil.logLifeCycle(getLogger(), "未配置Config目录,从classpath复制");
				Files.createDirectories(configBuildPath);
				FileResourcesUtils.copy(getLogger(), Constants.CONFIG_FOLDER + "-" + TYPE, configBuildFolder);
			}
		}
	}
}
