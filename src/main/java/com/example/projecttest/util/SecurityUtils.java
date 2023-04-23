package com.example.projecttest.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtils {
    public String username() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
