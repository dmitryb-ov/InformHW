package com.example.demo.security;

import inet.ipaddr.IPAddressString;
import inet.ipaddr.ipv4.IPv4Address;
import inet.ipaddr.ipv6.IPv6Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CustomAuthProvider implements AuthenticationProvider {
    @Autowired
    private HttpServletRequest request;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if ("root".equalsIgnoreCase(username) && "password".equalsIgnoreCase(password)) {
            WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
            String ipv6Addr = details.getRemoteAddress();
            IPAddressString ipStr = new IPAddressString(ipv6Addr);
            System.out.println(ipStr);
            DadataIpInfo dadataIpInfo = new DadataIpInfo();
//            String isoCode = dadataIpInfo.getCountryIsoCode("84.39.247.118");
            String isoCode = dadataIpInfo.getCountryIsoCode("77.111.247.248");
            if(isoCode.equalsIgnoreCase("ru")){
                return new UsernamePasswordAuthenticationToken(username,password);
            } else {
                throw new BadCredentialsException("You Russian");
            }
        } else {
            throw new BadCredentialsException("Failed");
        }
//        throw new BadCredentialsException("");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
