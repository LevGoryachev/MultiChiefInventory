package ru.goryachev.multichief.inventory.exception;

public class EmptyListException extends RuntimeException {

    public EmptyListException(String entityTypeName) {
        super(String.format("Requested list is empty. No %s" + "s" + " " + "found in database", entityTypeName));
    }
}
