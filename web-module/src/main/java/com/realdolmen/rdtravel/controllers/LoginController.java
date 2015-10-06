package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.domain.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;


@Named
@RequestScoped
public class LoginController implements Serializable {
    private boolean loginFailed;
    private User user;

    @PostConstruct
    public void init() {
        user = new User();
        loginFailed = false;
    }

    public String logout() throws ServletException {
        ExternalContext context = getContext();
        context.invalidateSession();
        HttpServletRequest origRequest = (HttpServletRequest) context.getRequest();
        origRequest.logout();
        return "/index.faces";
    }

    public String login() throws IOException {
        setLoginFailed(false);
        HttpServletRequest request = (HttpServletRequest) getContext().getRequest();
        try {
            request.login(user.getUserName(), user.getPassword());
        } catch (ServletException e) {
            setLoginFailed(true);
        } finally {
            user.setPassword(null);
        }
        return "index.faces";
    }

    private ExternalContext getContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public boolean isLoginFailed() {
        return loginFailed;
    }

    public void setLoginFailed(boolean loginFailed) {
        this.loginFailed = loginFailed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
