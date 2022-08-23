package pub.techfun.mybatis.def.task;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.mybatis.def.MybatisGenerator;
import pub.techfun.mybatis.def.config.DdlConfig;
import pub.techfun.mybatis.plugin.common.constants.Constants;
import pub.techfun.mybatis.plugin.common.task.CreateConfigFileTask;
import pub.techfun.mybatis.plugin.common.util.LogUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GenerateFileTask  extends DefaultTask {

    public static final String TASK_NAME = "generateFile";

    public GenerateFileTask() {
        dependsOn(getProject().getTasks().getByName(CreateConfigFileTask.TASK_NAME));
        setGroup(Constants.GROUP_NAME);
    }

    @TaskAction
    public void execute() throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File(getProject().getBuildDir().getPath()
                + "/" + Constants.CONFIG_FOLDER ));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Map<String, String> root = new HashMap<>(10);
        root.put("projectDir", getProject().getProjectDir().getAbsolutePath());
        String configFile = getProject().getBuildDir().getPath()
                + "/" + Constants.CONFIG_FOLDER + "/" + Constants.CONFIG_FILE+"_auto";
        Template temp = cfg.getTemplate(Constants.CONFIG_FILE);
        Writer out = new OutputStreamWriter(new FileOutputStream(configFile));
        temp.process(root, out);
        LogUtil.logLifeCycle(getLogger(), "生成配置文件:{}", configFile);
        new MybatisGenerator(new DdlConfig(true, true))
                .run(configFile);
    }
}
