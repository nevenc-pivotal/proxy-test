package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

@Component
@Profile("proxy")
public class ProxyConfigurator {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProxyConfigurator(
       @Value("${spring.my.proxy.host:}") String host,
       @Value("${spring.my.proxy.port:}") String port,
       @Value("${spring.my.proxy.user:}") String user,
       @Value("${spring.my.proxy.password:}") String password,
       @Value("${spring.my.proxy.skip:}") String skip
    ) {

        log.trace("Setting http.proxyHost: {}", host);
        System.setProperty("http.proxyHost", host);
        log.trace("Setting http.proxyPort: {}", port);
        System.setProperty("http.proxyPort", port);
        log.trace("Setting http.nonProxyHosts: {}", skip);
        System.setProperty("http.nonProxyHosts", skip);

        log.trace("Setting https.proxyHost: {}", host);
        System.setProperty("https.proxyHost", host);
        log.trace("Setting https.proxyPort: {}", port);
        System.setProperty("https.proxyPort", port);

        log.trace("Setting proxy authentication: {}/********", user);
        Authenticator.setDefault(new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password.toCharArray());
            }
        });
        log.trace("Configured Proxy.");

    }

}
