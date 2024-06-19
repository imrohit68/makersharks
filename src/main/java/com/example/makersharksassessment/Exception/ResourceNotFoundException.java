package com.example.makersharksassessment.Exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceName, String field, String fieldValue) {
        super(String.format("%s not found with %s : %s",resourceName,field,fieldValue));
    }
}
