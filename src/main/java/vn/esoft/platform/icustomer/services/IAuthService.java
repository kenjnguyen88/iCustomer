package vn.esoft.platform.icustomer.services;

import vn.esoft.platform.icustomer.controllers.dto.request.AuthentRequest;
import vn.esoft.platform.icustomer.controllers.dto.response.AuthenResponse;

public interface IAuthService {
    AuthenResponse authenticate(AuthentRequest request);

//    RegisterResponse signup(RegisterRequest request);
}
