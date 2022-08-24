package pub.techfun.mybatis.plugin.common.plugin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Properties;

/**
 * @author henry
 */
@Slf4j
public class EntityFilterPlugin extends PluginAdapter {

	private String ignoredFields;

	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
		ignoredFields = properties.getProperty("ignoredFields");
		log.info("跳过字段:{}", ignoredFields);
	}

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		return true;
	}

	@Override
	public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		return true;
	}

	@Override
	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		return true;
	}

	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		var ignores = List.of(ignoredFields.split(","));
		return !ignores.contains(field.getName());
	}


}
