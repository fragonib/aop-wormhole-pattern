package org.fragonib.wormhole.context;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

/**
 * Hold per request UUID metadata
 *
 * @author fragonib
 */
@Component
public class RequestMetadataHolder {

    private ConcurrentHashMap<UUID, PerRequestMetadata> perRequestContexts = new ConcurrentHashMap<>();

    /**
     * Retrieve metadata stored so far for given <i>request UUID</i> that it's modeled into <i>contextType</i>,
     * generates a new fresh and empty one if there is no metadata stored so far for given
     *
     * @param requestUuid UUID of the request
     * @param contextType Needed metadata type
     *
     * @param <T> Type of metadata
     *
     * @return Previous per request metadata or a new one.
     */
    @SuppressWarnings("unchecked")
    public <T extends PerRequestMetadata> T retrieveRequestContext(UUID requestUuid, Class<T> contextType) {

        if (requestUuid == RequestIdGenerator.VOID_UUID)
            throw new IllegalStateException("There is no valid request ID defined");

        return (T) perRequestContexts.computeIfAbsent(requestUuid, (uuid) -> {
            try {
                return contextType.newInstance();
            } catch (Exception e) {
                return null;
            }
        });
    }

}
