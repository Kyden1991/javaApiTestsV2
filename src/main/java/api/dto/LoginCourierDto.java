package api.dto;

public class LoginCourierDto {
    private String login;
    private int password;

    public LoginCourierDto(String login, int password) {
        this.login = login;
        this.password = password;
    }

    public LoginCourierDto(int password) {
        this.password = password;
    }

    public LoginCourierDto(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
