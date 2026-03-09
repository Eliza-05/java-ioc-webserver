package edu.eci.tdse.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Binds a method parameter to an HTTP query parameter.
 * If the query parameter is absent, defaultValue is used.
 *
 * Example:
 *   @GetMapping("/greeting")
 *   public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
 *       return "Hola " + name;
 *   }
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RequestParam {
    String value();
    String defaultValue() default "";
}
