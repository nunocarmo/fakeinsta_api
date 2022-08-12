package com.insta.api.insta.persistence.model.loginViewModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginViewModel {
    private String email;
    private String password;

    public String getUsername() {
        return this.email;
    }
}
