package com.hpe.bigdata.frontend.spring.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

@SuppressWarnings("WeakerAccess")
public class SpringSecurityAuthenticationInformationRetriever<A extends Authentication, P extends Principal> implements AuthenticationInformationRetriever<A, P> {
    @Override
    public A getAuthentication() {
        @SuppressWarnings("unchecked") final A authentication = (A) SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    @Override
    public P getPrincipal() {
        final A authentication = getAuthentication();
        @SuppressWarnings("unchecked") final P principal = authentication == null ? null : (P) authentication.getPrincipal();
        return principal;
    }
}
