package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@ResponseBody
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/", produces = "text/plain")
    public String testHttpConnection(@RequestParam(value = "url", required = false, defaultValue = "${test.url}") String requestedUrl) {

        log.trace("Starting testHttpConnection().");

        try {
            log.trace("Testing URL: {}", requestedUrl);
            URL url = new URL(requestedUrl);
            log.trace("Opening URL: {}", requestedUrl);
            try (InputStream in = url.openStream()) {
                log.trace("Successfully opened URL: {}", requestedUrl);
                String success = String.format("Successfully tested: %s\n\n  host=%s\n  port=%s\n  protocol=%s\n  query=%s\n  userinfo=%s", requestedUrl, url.getHost(), url.getPort(), url.getProtocol(), url.getQuery(), url.getUserInfo());
                return success;
            } catch (IOException ioe) {
                String error = "IO Exception: " + ioe.getLocalizedMessage();
                log.error(error);
                ioe.printStackTrace();
                return error;
            }
        } catch (MalformedURLException mue) {
            String error = "URL Exception: " + mue.getLocalizedMessage();
            log.error(error);
            mue.printStackTrace();
            return error;
        }

    }

}
