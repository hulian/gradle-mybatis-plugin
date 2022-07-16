package pub.techfun.docker.plugin.java.task;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.model.BuildResponseItem;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Exec;
import org.gradle.api.tasks.TaskAction;
import pub.techfun.docker.plugin.common.constants.Constants;
import pub.techfun.docker.plugin.common.task.CopyDockerFileTask;
import pub.techfun.docker.plugin.common.task.CopyJarTask;
import pub.techfun.docker.plugin.common.task.CreateDockerFileTask;
import pub.techfun.docker.plugin.common.task.GetGitVersionTask;
import pub.techfun.docker.plugin.common.util.ImageNameUtil;
import pub.techfun.docker.plugin.common.util.LogUtil;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;

/**
 * @author henry
 */
public class BuildImageTask extends DefaultTask {

	public static final String TASK_NAME = "buildImage";

	public BuildImageTask(){
		dependsOn(getProject().getTasks().getByName(CopyJarTask.TASK_NAME));
		dependsOn(getProject().getTasks().getByName(CopyDockerFileTask.TASK_NAME));
		dependsOn(getProject().getTasks().getByName(CreateDockerFileTask.TASK_NAME));
		dependsOn(getProject().getTasks().getByName(GetGitVersionTask.TASK_NAME));
		setGroup(Constants.GROUP_NAME);
	}

	@TaskAction
	protected void exec() {
		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
				.dockerHost(config.getDockerHost())
				.sslConfig(config.getSSLConfig())
				.maxConnections(100)
				.connectionTimeout(Duration.ofSeconds(30))
				.responseTimeout(Duration.ofSeconds(45))
				.build();
		DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);
		File file = new File(super.getProject().getBuildDir().getPath() + Constants.DOCKER_FOLDER);
		CountDownLatch latch = new CountDownLatch(1);
		dockerClient.buildImageCmd(file)
				.exec(new ResultCallback<BuildResponseItem>() {
			@Override
			public void onStart(Closeable closeable) {
				LogUtil.logLifeCycle(getLogger(), "build start");
			}

			@Override
			public void onNext(BuildResponseItem object) {
				System.out.print(object.getStream());
			}

			@Override
			public void onError(Throwable throwable) {
				LogUtil.logLifeCycle(getLogger(), String.valueOf(throwable));
				latch.countDown();
			}

			@Override
			public void onComplete() {
				LogUtil.logLifeCycle(getLogger(), "build complete");
				latch.countDown();
			}

			@Override
			public void close() throws IOException {
				LogUtil.logLifeCycle(getLogger(), "build close");
				latch.countDown();
			}
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
