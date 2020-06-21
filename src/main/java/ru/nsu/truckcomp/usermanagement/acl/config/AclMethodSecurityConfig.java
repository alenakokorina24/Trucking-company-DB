package ru.nsu.truckcomp.usermanagement.acl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.*;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AclMethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    @Autowired
    MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return defaultMethodSecurityExpressionHandler;
    }

}
