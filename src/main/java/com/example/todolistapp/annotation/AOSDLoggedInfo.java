package com.example.todolistapp.annotation;

import java.lang.annotation.*;


/**
 * No Log interface
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AOSDLoggedInfo {

}
