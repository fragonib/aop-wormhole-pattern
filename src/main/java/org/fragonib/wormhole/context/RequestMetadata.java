package org.fragonib.wormhole.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * API to use by consumers to retrieve metadata of any desired type
 *
 * @author fragonib
 */
@Component
public class RequestMetadata {

    @Autowired
    private RequestMetadataHolder requestMetadataHolder;

    /**
     * Retrieves metadata of current request of given type
     *
     * @param contextType Type of needed metadata
     *
     * @param <T> Type of needed metadata
     *
     * @return Previously stores metadata for current request or a new one
     */
    public <T extends PerRequestMetadata> T ofType(Class<T> contextType) {
        return requestMetadataHolder.retrieveRequestContext(RequestIdGenerator.VOID_UUID, contextType);
    }

}
