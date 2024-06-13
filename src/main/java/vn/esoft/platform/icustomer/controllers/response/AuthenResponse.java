package vn.esoft.platform.icustomer.controllers.response;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -1174901763648417586L;
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
    private Map<String, Object> userInfo;

    public String getRefreshToken() {
        return refreshToken;
    }

    public AuthenResponse setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public AuthenResponse setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public AuthenResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public Map<String, Object> getUserInfo() {
        return userInfo;
    }

    public AuthenResponse setUserInfo(Map<String, Object> userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}