package pub.techfun.docker.plugin.java.util;

import org.gradle.api.logging.Logger;

/**
 * @author henry
 */
public class LogUtil {
	public static void logLifeCycle(Logger logger, String args){
		logger.lifecycle("[gradle-docker-plugin-cmd]{}",args);
	}
}