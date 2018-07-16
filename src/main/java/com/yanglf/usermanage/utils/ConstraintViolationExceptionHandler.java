package com.yanglf.usermanage.utils;


import com.google.common.base.Joiner;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConstraintViolationExceptionHandler {

    public static String getMessage(ConstraintViolationException e){
        List list = new ArrayList();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation constraintViolation:constraintViolations){
            list.add(constraintViolation.getMessage());
        }
        return Joiner.on(";").join(list);
    }
}
