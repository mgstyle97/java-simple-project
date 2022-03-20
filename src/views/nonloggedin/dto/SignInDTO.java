package views.nonloggedin.dto;

public class SignInDTO {

    private String id;
    private String password;

    public SignInDTO(final String id, final String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

}
