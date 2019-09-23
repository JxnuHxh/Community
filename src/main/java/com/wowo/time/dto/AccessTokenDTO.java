package com.wowo.time.dto;

public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private  String code;
    private String rediret_uri;
    private String state;

    public AccessTokenDTO(String client_id, String client_secret, String code, String rediret_uri, String state) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.code = code;
        this.rediret_uri = rediret_uri;
        this.state = state;
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
                "client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                ", code='" + code + '\'' +
                ", rediret_uri='" + rediret_uri + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public AccessTokenDTO() {

    }

    public String getClient_id(){
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRediret_uri() {
        return rediret_uri;
    }

    public void setRediret_uri(String rediret_uri) {
        this.rediret_uri = rediret_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
