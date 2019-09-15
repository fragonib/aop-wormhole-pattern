package org.fragonib.wormhole.greeting;

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

    @GetMapping("/greeting")
    public String greets() {
        putInContextLastVisitUserName();
        String greeting = greetingService.sayHello();
        log.info("Returned greeting was [{}]", greeting);
        return greeting;
    }

    private void putInContextLastVisitUserName() {
        String lastVisitUserName = retrieveLastVisitedUserName();
        requestMetadata.ofType(GreetingMetadata.class).setUsername(lastVisitUserName);
        log.debug("Stored username [{}] into context", lastVisitUserName);
    }

    private String retrieveLastVisitedUserName() {
        return "Fran";
    }

}
