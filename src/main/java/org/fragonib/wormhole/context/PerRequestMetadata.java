package org.fragonib.wormhole.context;

import java.util.Map;

/**
 * Parent class for any metadata to be kept for a given request
 *
 * @author fragonib
 */
public abstract class PerRequestMetadata<T> {

    private Map<String, T> genericMetadata;

    public T get(String data) {
        return genericMetadata.get(data);
    }

    public T put(String name, T value) {
        return genericMetadata.put(name, value);
    }

}
