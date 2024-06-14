package vn.esoft.platform.icustomer.services;

import vn.esoft.platform.icustomer.controllers.request.AuthentRequest;
import vn.esoft.platform.icustomer.controllers.request.RegisterRequest;
import vn.esoft.platform.icustomer.controllers.response.AuthenResponse;
import vn.esoft.platform.icustomer.controllers.response.RegisterResponse;

public interface IAuthentService {
    AuthenResponse authenticate(AuthentRequest request);

    AuthenResponse auth(AuthentRequest request);

    RegisterResponse signup(RegisterRequest request);
}
