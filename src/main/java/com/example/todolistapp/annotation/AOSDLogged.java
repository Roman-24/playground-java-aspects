package com.example.todolistapp.annotation;

import java.lang.annotation.*;

/**
 * AOSD logged interface
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AOSDLogged {

  /**
   * No Log interface
   */
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @Inherited
  public @interface NoLog {

  }
}
