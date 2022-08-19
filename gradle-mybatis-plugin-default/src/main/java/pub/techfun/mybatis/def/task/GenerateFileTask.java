package pub.techfun.mybatis.def.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.mybatis.def.MybatisGenerator;
import pub.techfun.mybatis.def.config.DdlConfig;
import pub.techfun.mybatis.plugin.common.constants.Constants;

public class GenerateFileTask  extends DefaultTask {

    public static final String TASK_NAME = "generateFile";

    public GenerateFileTask(){
        setGroup(Constants.GROUP_NAME);
    }

    @TaskAction
    public void execute(){
        new MybatisGenerator(new DdlConfig(true, true))
                .run(getProject().getBuildDir().getPath()
                        + "/" + Constants.CONFIG_FOLDER + "/" + Constants.CONFIG_FILE);
    }
}
