package pub.techfun.mybatis.def.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Properties;

/**
 * @author henry
 */
public class LombokPlugin extends PluginAdapter {

	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
	}

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		generateToString(introspectedTable, topLevelClass);
		return true;
	}

	@Override
	public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		generateToString(introspectedTable, topLevelClass);
		return true;
	}

	@Override
	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		generateToString(introspectedTable, topLevelClass);
		return true;
	}

	private void generateToString(IntrospectedTable introspectedTable, TopLevelClass topLevelClass) {
		topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.Data"));
		topLevelClass.addAnnotation("@Data");
//		topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.EqualsAndHashCode"));
//		topLevelClass.addAnnotation("@EqualsAndHashCode(callSuper = true)");
		topLevelClass.getMethods().clear();
	}

}
