package com.ruoyi.framework.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public class OpenIdAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    public OpenIdAuthenticationToken(String openId) {
        super(null);
        this.principal = openId;
        setAuthenticated(false);
    }

    public OpenIdAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); // 必须调用父类的setAuthenticated方法
    }

    @Override
    public Object getCredentials() {
        return null; // 微信登录无需密码
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
