package pub.techfun.mybatis.plugin.common.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;

import java.util.List;

/**
 * @author henry
 */
public class MapperInterfacePlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
		// 添加父类接口
		var rm1 = new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper");
		var rm2 = new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper");
		var rm3 = new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper");
		var rm4 = new FullyQualifiedJavaType("org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper");
		String entity = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
		var privateKeyColumns = introspectedTable.getPrimaryKeyColumns();
		if(privateKeyColumns.size() == 0){
			throw new RuntimeException("primary key not fund!");
		}
		String id = privateKeyColumns.get(0).getFullyQualifiedJavaType().getShortName();
		var superInterface = new FullyQualifiedJavaType("pub.techfun.easydao.mybatis.mapper.BaseMapper<"+entity+","+id+">");
		interfaze.getImportedTypes().remove(rm1);
		interfaze.getImportedTypes().remove(rm2);
		interfaze.getImportedTypes().remove(rm3);
		interfaze.getImportedTypes().remove(rm4);
		interfaze.addImportedType(superInterface);
		interfaze.getSuperInterfaceTypes().clear();
		interfaze.addSuperInterface(superInterface);
		// 添加字段Get方法
		var getColumn = new Method("getColumns");
		getColumn.setDefault(true);
		getColumn.setReturnType(new FullyQualifiedJavaType("BasicColumn[]"));
		getColumn.addBodyLine("return selectList;");
		interfaze.addMethod(getColumn);
		interfaze.addImportedType(new FullyQualifiedJavaType("org.mybatis.dynamic.sql.SqlTable"));
		// 添加SqlTable Get方法
		var getSqlTable = new Method("getSqlTable");
		getSqlTable.setDefault(true);
		char[] chars = entity.toCharArray();
		chars[0] = Character.toLowerCase(chars[0]);
		getSqlTable.addBodyLine("return "+new String(chars)+";");
		getSqlTable.setReturnType(new FullyQualifiedJavaType("SqlTable"));
		interfaze.addMethod(getSqlTable);
		return true;
	}

	@Override
	public boolean clientUpdateAllColumnsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setDefault(true);
		method.setStatic(false);
		interfaze.setStatic(false);
		return super.clientUpdateAllColumnsMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientUpdateSelectiveColumnsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setDefault(true);
		method.setStatic(false);
		interfaze.setStatic(false);
		return super.clientUpdateSelectiveColumnsMethodGenerated(method, interfaze, introspectedTable);
	}
}
