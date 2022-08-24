package pub.techfun.mybatis.plugin.common.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MybatisGenerator {

    private final DdlConfig ddlConfig;

    public MybatisGenerator(DdlConfig ddlConfig) {
        this.ddlConfig = ddlConfig;
    }

    public void run(String configFileName){
        if(!ddlConfig.getEnabled()){
            return;
        }
        try {
            List<String> warnings = new ArrayList<>();
            File configFile = new File(configFileName);
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(ddlConfig.getOverwrite());
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
