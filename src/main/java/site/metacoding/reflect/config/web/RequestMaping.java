package site.metacoding.reflect.config.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
//JVM이 인식하게 해주는 코드?
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMaping {
	String value();
}
