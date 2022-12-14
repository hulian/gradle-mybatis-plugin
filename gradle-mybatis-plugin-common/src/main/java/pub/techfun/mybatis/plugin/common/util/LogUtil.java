package pub.techfun.mybatis.plugin.common.util;

import org.gradle.api.logging.Logger;

/**
 * @author henry
 */
public class LogUtil {

	private static String pluginName;
	public static void logLifeCycle(Logger logger, Object ... args){
		logger.lifecycle("["+pluginName+"]{}",args);
	}

	public static void setPluginName(String name){
		pluginName = name;
	}
}
