package com.ysh.projectY.exception;

import org.springframework.security.core.AuthenticationException;

public class MobilePhoneBadCredentialsException extends AuthenticationException {

    public MobilePhoneBadCredentialsException(String msg) {
        super(msg);
    }

    public MobilePhoneBadCredentialsException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
