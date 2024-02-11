package org.rpl.Final_Project_Rpl.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidationService {

    @Autowired
    private Validator validator;

    public void validate(Object request) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);
        if (constraintViolations != null && !constraintViolations.isEmpty()) {
            for (ConstraintViolation<Object> violation : constraintViolations) {
                System.out.println("Validation Error: " + violation.getPropertyPath() + " " + violation.getMessage());
            }
            throw new ConstraintViolationException(constraintViolations);
        }
    }

}
