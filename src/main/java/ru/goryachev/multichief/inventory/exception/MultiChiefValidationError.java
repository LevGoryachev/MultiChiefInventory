package ru.goryachev.multichief.inventory.exception;

import org.springframework.validation.FieldError;
/**
 * MultiChiefValidationError uses certain properties of FieldError class (org.springframework.validation)
 * @author Lev Goryachev
 * @version 1.1
 */
public class MultiChiefValidationError {

    private String defaultMessage;
    private String objectName;
    private String field;
    private Object rejectedValue;

    public MultiChiefValidationError(FieldError fieldError) {
        this.defaultMessage = fieldError.getDefaultMessage();
        this.objectName = fieldError.getObjectName();
        this.field = fieldError.getField();
        this.rejectedValue = fieldError.getRejectedValue();
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }
}
