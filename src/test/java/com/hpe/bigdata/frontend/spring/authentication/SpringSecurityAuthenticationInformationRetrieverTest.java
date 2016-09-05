/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hpe.bigdata.frontend.spring.authentication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpringSecurityAuthenticationInformationRetrieverTest {
    @Mock
    private SecurityContext securityContext;

    @Mock
    private MockAuthentication1 authentication;

    @Mock
    private MockAuthentication2 otherAuthentication;

    @Mock
    private MockPrincipal1 principal;

    @Mock
    private MockPrincipal2 otherPrincipal;

    private SecurityContext existingSecurityContext;
    private AuthenticationInformationRetriever<MockAuthentication1, MockPrincipal1> informationRetriever;

    @Before
    public void initialise() {
        existingSecurityContext = SecurityContextHolder.getContext();

        when(authentication.getPrincipal()).thenReturn(principal);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        informationRetriever = new SpringSecurityAuthenticationInformationRetriever<>(MockAuthentication1.class, MockPrincipal1.class);
    }

    @After
    public void destroy() {
        SecurityContextHolder.setContext(existingSecurityContext);
    }

    @Test
    public void getAuthentication() {
        assertThat(informationRetriever.getAuthentication(), is(authentication));
    }

    @Test
    public void getPrincipal() {
        assertNotNull(informationRetriever.getPrincipal());
    }

    @Test
    public void isCurrentAuthentication() {
        assertTrue(informationRetriever.isCurrentSessionAuthentication());
        assertFalse(new SpringSecurityAuthenticationInformationRetriever<>(MockAuthentication2.class, MockPrincipal1.class).isCurrentSessionAuthentication());
        assertFalse(new SpringSecurityAuthenticationInformationRetriever<>(MockAuthentication1.class, MockPrincipal2.class).isCurrentSessionAuthentication());
    }

    private interface MockAuthentication1 extends Authentication {
    }

    private interface MockAuthentication2 extends Authentication {
    }

    private interface MockPrincipal1 extends Principal {
    }

    private interface MockPrincipal2 extends Principal {
    }
}
