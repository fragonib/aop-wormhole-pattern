package org.fragonib.wormhole.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import org.fragonib.wormhole.context.RequestMetadata;

/**
 * Service that produces greets for connected user
 *
 * @author fragonib
 */
@Slf4j
@Component
public class GreetingService {

    @Autowired
    private RequestMetadata requestMetadata;

    public String sayHello() {
        String username = requestMetadata.ofType(GreetingMetadata.class).getUsername();
        log.debug("Read username [{}] from context", username);
        return String.format("Hello %s", username);
    }

}
