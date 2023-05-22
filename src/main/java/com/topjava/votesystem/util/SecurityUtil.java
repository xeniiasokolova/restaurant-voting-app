package com.topjava.votesystem.util;

import com.topjava.votesystem.model.Role;
import com.topjava.votesystem.model.User;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public class SecurityUtil {
    private static long userId = User.START_SEQ_USER;
    private static Set<Role> roles = Collections.singleton(Role.USER);

    public SecurityUtil() {
    }

    public static long authUserId() {
        return userId;
    }

    public static void setAuthUserId(long id) {
        SecurityUtil.userId = id;
    }

    public static Set<Role> authUserRole() {
        return roles;
    }

    public static void setAuthUserRole(Set<Role> roles) {
        SecurityUtil.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }



}
