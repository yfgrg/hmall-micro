package com.hmall.common.interceptors;

import cn.hutool.core.util.StrUtil;
import com.hmall.common.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 *
 * @Author mr.wu
 * @Date 2025-6-16 21:49
 */
public class UserInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1. 获取请求头中的userId
        String userId = request.getHeader("user-info");

        //2. 如果非空则设置到ThreadLocal中
        if(StrUtil.isNotBlank(userId)) {
            UserContext.setUser(Long.valueOf(userId));
        }

        //3. 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.removeUser();//释放用完的用户ID，避免内存泄漏
    }
}