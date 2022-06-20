package site.metacoding.reflect.config.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 범위를 지정해주는 코드?
@Target({ElementType.METHOD})
// JVM이 인식하게 해주는 코드?
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	// 디폴트 값이 없다면 사용할 때마다 붙여줘야 한다.
	String value();
}
