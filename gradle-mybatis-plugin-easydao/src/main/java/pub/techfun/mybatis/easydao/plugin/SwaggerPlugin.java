package pub.techfun.mybatis.easydao.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Properties;

/**
 * @author henry
 */
public class SwaggerPlugin extends PluginAdapter {

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
		topLevelClass.addAnnotation(getSchemaAnnotation(introspectedTable.getRemarks()));
	}

	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {

		topLevelClass.addImportedType(new FullyQualifiedJavaType("io.swagger.v3.oas.annotations.media.Schema"));
		field.addAnnotation(getSchemaAnnotation(introspectedColumn.getRemarks()));
		return true;
	}

	private String getSchemaAnnotation(String comment) {
		return "@Schema(description= \"" + comment + "\" )";
	}

}
