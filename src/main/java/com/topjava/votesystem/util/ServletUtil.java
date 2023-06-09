package com.topjava.votesystem.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class ServletUtil {
    public static Long getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Long.parseLong(paramId);
    }

    public static String getKeyword(HttpServletRequest request) {
        String param = Objects.requireNonNull(request.getParameter("keyword"));
        return param;
    }
}
