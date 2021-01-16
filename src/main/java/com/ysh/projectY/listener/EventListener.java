package com.ysh.projectY.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.event.*;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.security.web.session.HttpSessionIdChangedEvent;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.ServletRequestHandledEvent;

public class EventListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ServletRequestHandledEvent) {
            return;
        }
        System.out.println("==========================EVENT==========================");
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (requestAttributes != null) {
//            System.out.println("1==> " + requestAttributes.getRequest().getRequestURI());
//        } else {
//            System.out.println("2==> requestAttributes is Null");
//        }
        System.out.println("3==> " + event.getClass());
        System.out.println("4==> " + event.getSource().getClass());
        if (event instanceof AbstractAuthenticationEvent) {
            System.out.println("8==> " + ((AbstractAuthenticationEvent) event).getAuthentication());
            System.out.println("9==> " + ((AbstractAuthenticationEvent) event).getAuthentication().getClass());
            System.out.println("0==> " + ((AbstractAuthenticationEvent) event).getAuthentication().getDetails());
//            if (event instanceof InteractiveAuthenticationSuccessEvent || event instanceof AuthenticationSuccessEvent) {
//                // rememberMe登陆时也会触发事件
//                requestAttributes.getRequest().getSession().setAttribute("userName", ((AbstractAuthenticationToken) event.getSource()).getName());
//            } else if (event instanceof AbstractAuthenticationFailureEvent || event instanceof LogoutSuccessEvent) {
//                requestAttributes.getRequest().getSession().removeAttribute("userName");
//            }
        }
        System.out.println("=========================================================");
    }
}
