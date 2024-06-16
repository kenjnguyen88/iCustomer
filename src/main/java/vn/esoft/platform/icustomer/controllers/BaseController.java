package vn.esoft.platform.icustomer.controllers;

import lombok.RequiredArgsConstructor;
import vn.esoft.platform.icustomer.entities.vo.ProviderEnum;
import vn.esoft.platform.icustomer.services.IAuthService;
import vn.esoft.platform.icustomer.services.auth.EsoftAuthService;
import vn.esoft.platform.icustomer.services.auth.FacebookAuthService;
import vn.esoft.platform.icustomer.services.auth.GoogleAuthService;
import vn.esoft.platform.icustomer.services.user.UserService;

@RequiredArgsConstructor
public abstract class BaseController {

    protected final UserService userService;
    protected final EsoftAuthService esoftAuthService;
    protected final GoogleAuthService googleAuthService;
    protected final FacebookAuthService facebookAuthService;

    protected IAuthService authService(final String provider) {

        IAuthService authService = null;
        switch (ProviderEnum.valueOf(provider)) {
            case Esoft:
                authService = esoftAuthService;
                break;
            case Google:
                authService = googleAuthService;
                break;
            case Facebook:
                authService = facebookAuthService;
                break;
            default:
                throw new RuntimeException("Provider not correct!");
        }
        return authService;
    }
}
