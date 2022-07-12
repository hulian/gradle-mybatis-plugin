package pub.techfun.docker.plugin.cmd.util;

import org.gradle.api.logging.Logger;

/**
 * @author henry
 */
public class LogUtil {
	public static void logLifeCycle(Logger logger, String args){
		logger.lifecycle("[carbon]{}",args);
	}
}
