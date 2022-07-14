package pub.techfun.docker.plugin.java.util;

import org.gradle.api.Project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author henry
 */
public class ImageNameUtil {
	public static String getImageName(Project project){
		String imageName;
		String gitVersion = "";
		try {
			gitVersion = Files.readString(Paths.get(project.getBuildDir().getPath(),"/docker/GitVersion"));
		} catch (IOException ignore) {
		}
		if(gitVersion!=null){
			gitVersion =  ":" + gitVersion.replace("\n","");
		}
		if(PropertyUtil.hasRegistryHost(project)){
			imageName = PropertyUtil.getRegistryHost(project) + "/" + project.getName() + gitVersion;
		}else{
			imageName = project.getName() + gitVersion;
		}
		return imageName;
	}

}
