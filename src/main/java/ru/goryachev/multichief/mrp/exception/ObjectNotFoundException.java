package ru.goryachev.multichief.mrp.exception;

public class ObjectNotFoundException extends Exception {

    public ObjectNotFoundException(String entityTypeName, Long id) {
        super(String.format("%s with ID %d not found in database", entityTypeName, id));
    }
}
