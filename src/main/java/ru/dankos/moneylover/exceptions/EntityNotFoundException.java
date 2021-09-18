package ru.dankos.moneylover.exceptions;

public class EntityNotFoundException extends ServiceException {
    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
