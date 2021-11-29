package ru.goryachev.multichief.inventory.exception;
/**
 * MultiChiefEmptyListException is a custom MultiChief application exception
 * Informs about DTO/entity list is empty;
 * If a response only contained the list of certain objects (of the same type), there would be nothing to present (to show),
 * and the exception should be thrown.
 * Else the response contained the list as a nested type, and contained other properties
 * - the MultiChiefEmptyListException should not be thrown.
 * @author Lev Goryachev
 * @version 1.1
 */
public class MultiChiefEmptyListException extends RuntimeException {

    public MultiChiefEmptyListException(String entityTypeName) {
        super(String.format("Requested list is empty. No %s" + "s" + " " + "found in database", entityTypeName));
    }
}
