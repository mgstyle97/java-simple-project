package views.filter.exceptions;

public class AuthorizedException extends RuntimeException {

    public AuthorizedException() {
        super("로그인이 필요한 서비스입니다.");
    }

}
