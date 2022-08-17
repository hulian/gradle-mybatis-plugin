package pub.techfun.mybatis.plugin.easydao.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.mybatis.plugin.common.constants.Constants;
import pub.techfun.mybatis.plugin.easydao.generator.DdlConfig;
import pub.techfun.mybatis.plugin.easydao.generator.MybatisGenerator;

public class GenerateFileTask  extends DefaultTask {

    public GenerateFileTask(){
        setGroup(Constants.GROUP_NAME);
    }


    @TaskAction
    public void execute(){
        new MybatisGenerator(new DdlConfig(true, true))
                .run(getProject().getProjectDir().getPath()
                        + "/" + Constants.CONFIG_FOLDER + "/" + Constants.CONFIG_FILE);
    }
}
