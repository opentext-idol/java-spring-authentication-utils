/*
 * Copyright 2015 Open Text.
 *
 * Licensed under the MIT License (the "License"); you may not use this file
 * except in compliance with the License.
 *
 * The only warranties for products and services of Open Text and its affiliates
 * and licensors ("Open Text") are as may be set forth in the express warranty
 * statements accompanying such products and services. Nothing herein should be
 * construed as constituting an additional warranty. Open Text shall not be
 * liable for technical or editorial errors or omissions contained herein. The
 * information contained herein is subject to change without notice.
 */

package com.hpe.bigdata.frontend.spring.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

/**
 * Utility class wrapping access to {@link SecurityContextHolder#getContext()} for ease of loose coupling
 *
 * @param <A> expected session authentication implementation type
 * @param <P> expected session principal implementation type
 */
@SuppressWarnings("WeakerAccess")
public interface AuthenticationInformationRetriever<A extends Authentication, P extends Principal> {
    /**
     * The authentication information stored in the Spring session
     *
     * @return the authentication information stored in the Spring session
     */
    A getAuthentication();

    /**
     * The authentication principal information stored in the Spring session
     *
     * @return the authentication principal information stored in the Spring session
     */
    P getPrincipal();

    /**
     * Whether the authentication and principal currently stored in the Spring session confirm to the types specified for this retriever class
     *
     * @return whether the authentication and principal currently stored in the Spring session confirm to the types specified for this retriever class
     */
    boolean isCurrentSessionAuthentication();

}
