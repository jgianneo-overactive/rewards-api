package com.awards.common.exception;

public class ResourceNotFound extends RuntimeException {

    private static final long serialVersionUID = -5201785825707199588L;

    public ResourceNotFound(String message) {
        super(message);
    }
}
