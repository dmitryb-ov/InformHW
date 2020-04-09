package ru.kpfu.formsvalidation.config;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.Locale;

public class CustomInternalViewResolver extends UrlBasedViewResolver {
    private static final String URL_ERROR = "error:";

    @Override
    protected View createView(String viewName, Locale locale) throws Exception {
        String forwardUrl;
        if(viewName.startsWith(URL_ERROR)){
            forwardUrl = viewName.substring(URL_ERROR.length());
            InternalResourceView view = new InternalResourceView(forwardUrl);
            return applyLifecycleMethods(URL_ERROR, view);
//            RedirectView view = new RedirectView(forwardUrl,this.isRedirectContextRelative(),this.isRedirectHttp10Compatible());
//            String[] hosts = this.getRedirectHosts();
//            if(hosts != null){
//                view.setHosts(hosts);
//            }
//            return this.applyLifecycleMethods(URL_ERROR,view);
        }
        return super.createView(viewName, locale);
    }
}
