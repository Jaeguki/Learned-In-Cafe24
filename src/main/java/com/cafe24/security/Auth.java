package com.cafe24.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( {ElementType.TYPE, ElementType.METHOD }) // 어디에 붙여 쓰겠냐... 클래스냐 메소드냐 등
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	public enum Role {USER, ADMIN}

	public Role role() default Role.USER;

}