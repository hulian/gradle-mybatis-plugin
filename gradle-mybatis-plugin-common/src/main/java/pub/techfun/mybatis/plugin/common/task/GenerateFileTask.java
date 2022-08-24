package pub.techfun.mybatis.plugin.common.task;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.mybatis.plugin.common.constants.Constants;
import pub.techfun.mybatis.plugin.common.generator.DdlConfig;
import pub.techfun.mybatis.plugin.common.generator.MybatisGenerator;
import pub.techfun.mybatis.plugin.common.util.LogUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class GenerateFileTask extends DefaultTask {

    public static final String TASK_NAME = "generateFile";

    public GenerateFileTask() {
        dependsOn(getProject().getTasks().getByName(CreateConfigFileTask.TASK_NAME));
        setGroup(Constants.GROUP_NAME);
    }

    @TaskAction
    public void execute() throws IOException, TemplateException {
        // 生成freemark模版
        var configFolder = Path.of(getProject().getBuildDir().getPath() , Constants.CONFIG_FOLDER );
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(configFolder.getFileName().toFile());
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Map<String, String> root = new HashMap<>();
        root.put("projectDir", getProject().getProjectDir().getAbsolutePath());
        var configFile = Path.of(getProject().getBuildDir().getPath()
                ,Constants.CONFIG_FOLDER ,Constants.CONFIG_FILE+"_auto");
        Template temp = cfg.getTemplate(Constants.CONFIG_FILE);
        Writer out = new OutputStreamWriter(new FileOutputStream(configFile.toFile()));
        temp.process(root, out);
        LogUtil.logLifeCycle(getLogger(), "生成配置文件:{}", configFile);
        new MybatisGenerator(new DdlConfig(true, true))
                .run(configFile.toString());
    }
}
