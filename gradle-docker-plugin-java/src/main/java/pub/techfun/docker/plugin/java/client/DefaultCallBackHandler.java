package pub.techfun.docker.plugin.java.client;

import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.model.BuildResponseItem;
import com.github.dockerjava.api.model.ResponseItem;
import org.gradle.api.logging.Logger;
import pub.techfun.docker.plugin.common.util.LogUtil;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author henry
 */
public class DefaultCallBackHandler<T extends ResponseItem> implements ResultCallback<T> {

    private Logger logger;
    private CountDownLatch latch;

    public DefaultCallBackHandler(Logger logger, CountDownLatch latch){
        this.logger = logger;
        this.latch = latch;
    }

    @Override
    public void onStart(Closeable closeable) {
        LogUtil.logLifeCycle(logger, "command start");
    }


    @Override
    public void onNext(T object) {
        System.out.print(object.getStream());
    }

    @Override
    public void onError(Throwable throwable) {
        LogUtil.logLifeCycle(logger, String.valueOf(throwable));
        latch.countDown();
    }

    @Override
    public void onComplete() {
        LogUtil.logLifeCycle(logger, "command complete");
        latch.countDown();
    }

    @Override
    public void close() throws IOException {
        LogUtil.logLifeCycle(logger, "command close");
        latch.countDown();
    }
}
