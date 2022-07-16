package pub.techfun.docker.plugin.java.client;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.exception.ConflictException;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.exception.NotModifiedException;
import com.github.dockerjava.api.model.BuildResponseItem;
import com.github.dockerjava.api.model.PushResponseItem;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.gradle.api.logging.Logger;
import pub.techfun.docker.plugin.common.util.LogUtil;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author henry
 */
public class DockerCmdClient {

    private static DockerClient client;


    public static void buildImage(Logger logger, File folder){
        DockerClient client = getClient();
        CountDownLatch latch = new CountDownLatch(1);
        ResultCallback<BuildResponseItem> handler = new DefaultCallBackHandler<>(
                logger, latch
        );
        client.buildImageCmd(folder).exec(handler);
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void pushImage(Logger logger, String name){
        DockerClient client = getClient();
        CountDownLatch latch = new CountDownLatch(1);
        ResultCallback<PushResponseItem> handler = new DefaultCallBackHandler<>(
                logger, latch
        );
        client.pushImageCmd(name).exec(handler);
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stopContainer(Logger logger, String name){
        DockerClient client = getClient();
        try {
            client.stopContainerCmd(name).exec();
        } catch (NotFoundException e) {
            LogUtil.logLifeCycle(logger, "容器不存在:" + name);
        } catch (NotModifiedException e) {
            LogUtil.logLifeCycle(logger, "容器已停止:" + name);
        }
    }

    public static void removeContainer(Logger logger, String name){
        DockerClient client = getClient();
        try {
            client.removeContainerCmd(name).exec();
        } catch (NotFoundException e) {
            LogUtil.logLifeCycle(logger, "容器不存在:" + name);
        }
    }

    public static void publishImage(
            Logger logger, String imageName, String containerName, List<String> cmd
    ){
        DockerClient client = getClient();
        try {
            client.createContainerCmd(imageName)
                    .withAliases(containerName)
                    .withCmd(cmd)
                    .exec();
        } catch (NotFoundException e) {
            LogUtil.logLifeCycle(logger, "容器不存在:" + containerName);
        } catch (ConflictException e) {
            LogUtil.logLifeCycle(logger, "容器已存在:" + containerName);
        }
    }

    private static DockerClient getClient() {
        if(client!=null){
            return client;
        }
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();
        client = DockerClientImpl.getInstance(config, httpClient);
        return client;
    }
}
