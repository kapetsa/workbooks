package com.novik.workbooks.services;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class SessionService {

    private static ConcurrentHashMap<String, String> sessionMap;
    public SessionService() {
        if (sessionMap == null) {
            sessionMap = new ConcurrentHashMap<>();
        }
    }

    void addLocalBySessionId(String sessionId, String locale) {
        sessionMap.put(sessionId, locale);
    }

    String getLocaleBySessionId(String sessionId) {
        return sessionMap.get(sessionId);
    }

    void deleteSessionById(String sessionId) {
        sessionMap.remove(sessionId);
    }
}