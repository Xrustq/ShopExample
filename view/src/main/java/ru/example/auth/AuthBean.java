package ru.example.auth;

import org.apache.commons.lang3.StringUtils;
import ru.example.auth.ejb.AuthenticationManger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class AuthBean implements Serializable {
    private boolean loggedIn;

    private String login;
    private String password;

    private String reqestedPage;

    @EJB
    private AuthenticationManger authenticationManger;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReqestedPage() {
        return reqestedPage;
    }

    public void setReqestedPage(String reqestedPage) {
        this.reqestedPage = reqestedPage;
    }

    public void doLogin() {
        if ((StringUtils.isEmpty(login) || (StringUtils.isEmpty(password)))) {
            loggedIn = false;
            return;
        }

        loggedIn = authenticationManger.loginAsUser(login, password);

//      Производим редирект на страницу
        if (loggedIn) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(reqestedPage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
