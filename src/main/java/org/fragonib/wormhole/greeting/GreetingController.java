package org.fragonib.wormhole.greeting;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import org.fragonib.wormhole.context.RequestMetadata;

/**
 * Entry point of this greeter microservice
 *
 * @author fragonib
 */
@Slf4j
@Controller
public class GreetingController {

    @Autowired
    private RequestMetadata requestMetadata;

    @Autowired
    private GreetingService greetingService;

    private final ExecutorService aExecutorService = Executors.newSingleThreadExecutor();

    @GetMapping("/greeting")
    public String greets() {
        this.putInContextLastVisitUserName();
        final String greeting = this.runInOtherThread(() -> this.greetingService.sayHello());
        log.info("Returned greeting was [{}]", greeting);
        return greeting;
    }

    private <T> T runInOtherThread(final Callable<T> aCallable) {
        try {
            final Future<T> future = this.aExecutorService.submit(aCallable);
            return future.get();
        } catch (final Exception e) {
            return null;
        }
    }

    private void putInContextLastVisitUserName() {
        final String lastVisitUserName = this.retrieveLastVisitedUserName();
        this.requestMetadata.ofType(GreetingMetadata.class).setUsername(lastVisitUserName);
        log.debug("Stored username [{}] into context", lastVisitUserName);
    }

    private String retrieveLastVisitedUserName() {
        return "Fran";
    }

}
