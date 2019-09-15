package org.fragonib.wormhole.greeting;

import lombok.Data;
import org.fragonib.wormhole.context.PerRequestMetadata;

/**
 * Metadata modeled and used by greeter service
 *
 * @author fragonib
 */
@Data
public class GreetingMetadata extends PerRequestMetadata {

    private String username;

    public String getUsername() {
        if (username == null)
            throw new IllegalStateException("Username is not defined");
        return username;
    }
}
