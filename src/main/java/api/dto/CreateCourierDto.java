package api.dto;


public class CreateCourierDto {
    private String login;
    private int password;
    private String firstName;

    public CreateCourierDto(String login, int password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CreateCourierDto(int password, String firstName) {
        this.password = password;
        this.firstName = firstName;
    }

    public CreateCourierDto(String login, String firstName) {
        this.login = login;
        this.firstName = firstName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
