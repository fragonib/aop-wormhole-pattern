package org.fragonib.wormhole.context;

import java.util.UUID;

import org.springframework.stereotype.Component;

/**
 * UUID generator to bind to a request
 *
 * @author fragonib
 */
@Component
class RequestIdGenerator {

    /**
     * UUID to use, when is no still generated a new one for a specific request
     */
    static UUID VOID_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    UUID generateRequestId() {
        return UUID.randomUUID();
    }

}
