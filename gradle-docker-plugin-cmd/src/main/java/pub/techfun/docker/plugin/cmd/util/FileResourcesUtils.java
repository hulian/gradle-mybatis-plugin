package pub.techfun.docker.plugin.cmd.util;

import org.gradle.api.logging.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class FileResourcesUtils {

	public static void copy(Logger logger, String src, String dist) {
		FileResourcesUtils app = new FileResourcesUtils();
		try {
			List<Path> result = app.getPathsFromResourceJAR(logger, src);
			for (Path path : result) {
				LogUtil.logLifeCycle(logger,"文件路径:" + path);
				String filePathInJAR = path.toString();
				if (filePathInJAR.startsWith("/")) {
					filePathInJAR = filePathInJAR.substring(1);
				}
				LogUtil.logLifeCycle(logger,"JAR里的的文件路径:" + filePathInJAR);
				InputStream is = app.getFileFromResourceAsStream(filePathInJAR);
				Files.copy(is, Paths.get(dist, String.valueOf(path.getFileName())), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (URISyntaxException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private InputStream getFileFromResourceAsStream(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		if (inputStream == null) {
			throw new IllegalArgumentException("文件不存在:" + fileName);
		} else {
			return inputStream;
		}
	}

	private List<Path> getPathsFromResourceJAR(Logger logger, String folder)
		throws URISyntaxException, IOException {
		List<Path> result;
		String jarPath = getClass().getProtectionDomain()
			.getCodeSource()
			.getLocation()
			.toURI()
			.getPath();
		LogUtil.logLifeCycle(logger,"JAR文件路径:" + jarPath);
		URI uri = URI.create("jar:file:" + jarPath);
		try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
			result = Files.walk(fs.getPath(folder))
				.filter(Files::isRegularFile)
				.collect(Collectors.toList());
		}
		return result;
	}

}

