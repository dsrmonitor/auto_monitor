package com.sys.frotainteligente.DTO;

public class UserDTO {

    private Long id_user;
    private String tx_nome_user;
    private String pw_user;

    public UserDTO(Long id_user, String tx_nome_user, String pw_user) {
        this.id_user = id_user;
        this.tx_nome_user = tx_nome_user;
        this.pw_user = pw_user;
    }

    public UserDTO(){

    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getTx_nome_user() {
        return tx_nome_user;
    }

    public void setTx_nome_user(String tx_nome_user) {
        this.tx_nome_user = tx_nome_user;
    }

    public String getPw_user() {
        return pw_user;
    }

    public void setPw_user(String pw_user) {
        this.pw_user = pw_user;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id_user=" + id_user +
                ", tx_nome_user='" + tx_nome_user + '\'' +
                ", pw_user='" + pw_user + '\'' +
                '}';
    }
}
