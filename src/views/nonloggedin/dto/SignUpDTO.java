package views.nonloggedin.dto;

public class SignUpDTO {

    private String id;
    private String password;
    private String nick;

    public SignUpDTO(final String id, final String password, final String nick) {
        this.id = id;
        this.password = password;
        this.nick = nick;
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNick() {
        return this.nick;
    }

}
