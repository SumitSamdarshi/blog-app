package com.samda.blog_app.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourcesNotFoundException extends RuntimeException {

    private String resourcesName;
    private String fieldName;
    private long fieldValue;

    public ResourcesNotFoundException(String resourcesName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : %s", resourcesName, fieldName, fieldValue));
        this.resourcesName = resourcesName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
