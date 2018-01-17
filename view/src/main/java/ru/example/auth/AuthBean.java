package ru.example.auth;

import org.apache.commons.lang3.StringUtils;
import ru.example.auth.domain.ShopUser;
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

    private ShopUser.Role role;

    private String login;
    private String password;

    private String requestedPage;

    @EJB
    private AuthenticationManger authenticationManger;

    public ShopUser.Role getRole() {
        return role;
    }

    public void setRole(ShopUser.Role role) {
        this.role = role;
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

    public String getRequestedPage() {
        return requestedPage;
    }

    public void setRequestedPage(String requestedPage) {
        this.requestedPage = requestedPage;
    }

    public void doLogin() {
        if ((StringUtils.isEmpty(login) || (StringUtils.isEmpty(password)))) {
            role = null;
            return;
        }

        role = authenticationManger.login(login, password);

//      Производим редирект на страницу
        if (role != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(requestedPage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
