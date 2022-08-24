/*
 *    Copyright 2006-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package pub.techfun.mybatis.plugin.common.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.kotlin.KotlinFile;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.Properties;
import java.util.Set;

/**
 * @author henry
 */
public class EmptyCommentGenerator implements org.mybatis.generator.api.CommentGenerator {

	/**
	 * Adds a suitable comment to warn users that the element was generated, and
	 * when it was generated.
	 *
	 * @param xmlElement the xml element
	 */
	@Override
	public void addComment(XmlElement xmlElement) {
	}

	@Override
	public void addConfigurationProperties(Properties props) {
	}


	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addJavaDocLine("/**\n" +
			" * @author henry\n" +
			" */");
	}

	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
								IntrospectedColumn introspectedColumn) {
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
	}

	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
								 IntrospectedColumn introspectedColumn) {
	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
								 IntrospectedColumn introspectedColumn) {
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
										   Set<FullyQualifiedJavaType> imports) {
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
										   IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
								   Set<FullyQualifiedJavaType> imports) {
	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
								   IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
	}

	@Override
	public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable,
								   Set<FullyQualifiedJavaType> imports) {
	}

	@Override
	public void addFileComment(KotlinFile kotlinFile) {
	}
}
