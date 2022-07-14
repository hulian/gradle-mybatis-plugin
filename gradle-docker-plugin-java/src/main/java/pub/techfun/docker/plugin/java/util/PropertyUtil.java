package pub.techfun.docker.plugin.java.util;

import pub.techfun.docker.plugin.java.constants.Constants;
import org.gradle.api.Project;

/**
 * @author henry
 */
public class PropertyUtil {

	public static boolean hasRegistryHost(Project project) {
		return project.hasProperty(Constants.PROPERTY_REGISTRY_HOST);
	}

	public static String getRegistryHost(Project project){
		if(hasRegistryHost(project)){
			return (String) project.getProperties().get(Constants.PROPERTY_REGISTRY_HOST);
		}else{
			return null;
		}
	}

	public static boolean hasDeployHost(Project project) {
		return project.hasProperty(Constants.PROPERTY_DEPLOY_HOST);
	}

	public static String getDeployHost(Project project){
		if(hasDeployHost(project)){
			return (String) project.getProperties().get(Constants.PROPERTY_DEPLOY_HOST);
		}else{
			return null;
		}
	}

	public static boolean hasArgs(Project project) {
		return project.hasProperty(Constants.PROPERTY_ARGS);
	}

	public static String getArgs(Project project){
		if(hasDeployHost(project)){
			return (String) project.getProperties().get(Constants.PROPERTY_ARGS);
		}else{
			return null;
		}
	}
}
