package pub.techfun.mybatis.plugin.common.plugin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Properties;

/**
 * @author henry
 */
@Slf4j
public class BaseEntityPlugin extends PluginAdapter {

	private String entitySuperClass;

	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
		entitySuperClass = properties.getProperty("entitySuperClass");
		log.info("实体类父类:{}", entitySuperClass);
	}

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
												 IntrospectedTable introspectedTable) {
		generateToString(introspectedTable, topLevelClass);
		return true;
	}

	@Override
	public boolean modelRecordWithBLOBsClassGenerated(
		TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		generateToString(introspectedTable, topLevelClass);
		return true;
	}

	@Override
	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
												 IntrospectedTable introspectedTable) {
		generateToString(introspectedTable, topLevelClass);
		return true;
	}


	private void generateToString(IntrospectedTable introspectedTable, TopLevelClass topLevelClass) {
		if(entitySuperClass!=null) {
			var superClass = new FullyQualifiedJavaType(entitySuperClass);
			topLevelClass.addImportedType(superClass);
			topLevelClass.setSuperClass(superClass.getShortName());
		}
	}

	@Override
	public boolean dynamicSqlSupportGenerated(TopLevelClass supportClass, IntrospectedTable introspectedTable) {
		var entityName = supportClass.getInnerClasses().get(0).getType().getShortName();
		supportClass.addImportedType("pub.techfun.easydao.core.customizer.impl.EasydaoCustomizerHolder");
		var body = supportClass.getInnerClasses().get(0).getMethods().get(0).getBodyLines();
		body.clear();
		body.add( "super(() ->  EasydaoCustomizerHolder.getCustomizer().customizeTableName(\""
				+introspectedTable.getFullyQualifiedTable().getIntrospectedTableName()+"\"), "+ entityName +"::new);");
		return super.dynamicSqlSupportGenerated(supportClass, introspectedTable);
	}
}
