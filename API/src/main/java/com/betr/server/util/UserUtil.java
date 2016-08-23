package com.betr.server.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.betr.server.domain.User;

public class UserUtil {

	public static User getUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
