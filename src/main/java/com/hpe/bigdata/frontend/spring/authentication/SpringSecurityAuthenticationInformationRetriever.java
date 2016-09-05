package com.hpe.bigdata.frontend.spring.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

@SuppressWarnings("WeakerAccess")
public class SpringSecurityAuthenticationInformationRetriever<A extends Authentication, P extends Principal> implements AuthenticationInformationRetriever<A, P> {
    private final Class<A> authenticationClass;
    private final Class<P> principalClass;

    public SpringSecurityAuthenticationInformationRetriever(final Class<A> authenticationClass, final Class<P> principalClass) {
        this.authenticationClass = authenticationClass;
        this.principalClass = principalClass;
    }

    @Override
    public A getAuthentication() {
        return authenticationClass.cast(SecurityContextHolder.getContext().getAuthentication());
    }

    @Override
    public P getPrincipal() {
        final A authentication = getAuthentication();
        return authentication == null ? null : principalClass.cast(authentication.getPrincipal());
    }

    @Override
    public boolean isCurrentSessionAuthentication() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authenticationClass.isAssignableFrom(authentication.getClass()) && principalClass.isAssignableFrom(authentication.getPrincipal().getClass());
    }
}
