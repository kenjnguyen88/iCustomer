package vn.esoft.platform.icustomer.responses;

import java.util.Map;

public class AuthentResponse {
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
    private Map<String, Object> userInfo;

    public String getRefreshToken() {
        return refreshToken;
    }

    public AuthentResponse setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public AuthentResponse setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public AuthentResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public Map<String, Object> getUserInfo() {
        return userInfo;
    }

    public AuthentResponse setUserInfo(Map<String, Object> userInfo) {
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