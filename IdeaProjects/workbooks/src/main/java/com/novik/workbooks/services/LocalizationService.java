package com.novik.workbooks.services;

import com.novik.workbooks.localization.BundleFactory;
import com.novik.workbooks.localization.locale.BundleResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

@Component
public class LocalizationService {

    private final BundleFactory factory;
    private final SessionService sessionService;

    @Autowired
    public LocalizationService(BundleFactory factory, SessionService sessionService) {
        this.factory = factory;
        this.sessionService = sessionService;
    }

    public void setLocalization(HttpServletRequest request) {
        String locale = sessionService.getLocaleBySessionId(request.getSession().getId());
        request.setAttribute("localization", chooseLocalization(locale));
    }

    public ResourceBundle getCurrentBundleForUser(String sessionId) {
        String locale = sessionService.getLocaleBySessionId(sessionId);
        return chooseLocalization(locale);
    }

    public void changeLanguage(HttpServletRequest request) {
        String inputState = request.getParameter("inputLanguage");
        if (inputState != null) {
            if (inputState.equals("русский")) {
                sessionService.addLocalBySessionId(request.getSession().getId(), "RU");
            }
            if (inputState.equals("english")) {
                if (sessionService.getLocaleBySessionId(request.getSession().getId()) != null) {
                    sessionService.deleteSessionById(request.getSession().getId());
                }
            }
        }
    }

    private ResourceBundle chooseLocalization(String locale) {
        BundleResource resource;
        ResourceBundle localization;
        if (locale != null) {
            resource = factory.createBundle(locale);
            localization = resource.getLocalization();
        } else {
            resource = factory.createBundle("default");
            localization = resource.getLocalization();
        }
        return localization;
    }
}