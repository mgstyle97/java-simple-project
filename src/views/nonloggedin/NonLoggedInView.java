package views.nonloggedin;


import user.User;
import user.UserRepository;
import views.View;
import views.nonloggedin.dto.SignInDTO;
import views.nonloggedin.dto.SignUpDTO;

import java.util.Optional;

public class NonLoggedInView extends View {

    private final UserRepository userRepository;

    public NonLoggedInView() {
        this.userRepository = new UserRepository();
    }

    @Override
    public void printPage() {
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("1. 회원가입\t 2. 로그인\t 3. 종료");
        System.out.print(">> ");
        handleRequest(Integer.valueOf(scanner.nextLine()));
    }

    @Override
    public void handleRequest(final Integer command) {
        try {
            switch (command) {
                case 1: {
                    signUp();
                    break;
                }
                case 2: {
                    signIn();
                    break;
                }
                case 3:
                    exit();
                    break;
                default:
                    throw new IllegalArgumentException("잘못된 요청입니다.");
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            System.out.println();
        }
    }

    private void signUp() {
        final SignUpDTO dto = generateSignUpDTO();
        userRepository.save(
                new User(
                        dto.getId(),
                        dto.getPassword(),
                        dto.getNick()
                )
        );
    }

    private void signIn() {
        final SignInDTO dto = generateSignInDTO();
        Optional<User> optionalUser = userRepository
                .findById(dto.getId(), (id, user) -> user.getId().equals(id));

        setLoggedInState(
                validationUser(optionalUser, dto.getPassword())
        );
    }

    private SignUpDTO generateSignUpDTO() {
        System.out.print("아이디 >> ");
        final String id = scanner.nextLine();

        System.out.print("비밀번호 >> ");
        final String password = scanner.nextLine();

        System.out.print("닉네임 >> ");
        final String nick = scanner.nextLine();

        return new SignUpDTO(id, password, nick);
    }

    private SignInDTO generateSignInDTO() {
        System.out.print("아이디 >> ");
        final String id = scanner.nextLine();

        System.out.print("비밀번호 >> ");
        final String password = scanner.nextLine();

        return new SignInDTO(id, password);
    }

    private User validationUser(final Optional<User> optionalUser, final String confirmPassword) {
        final User user = optionalUser.orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 아이디입니다.")
        );
        if (!user.checkPassword(confirmPassword)) {
            throw new IllegalArgumentException("올바르지 않은 패스워드입니다.");
        }

        return user;
    }

    private void setLoggedInState(final User user) {
        View.user = user;
        View.state = ViewState.Logged_In;
    }
}
