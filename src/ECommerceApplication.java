import views.View;
import views.ViewFactory;
import views.filter.exceptions.AuthorizedException;

public final class ECommerceApplication {

    public static void main(final String... args) {

        do {

            try {
                final View view = ViewFactory.generateView(View.state);
                view.printPage();
            } catch (IllegalArgumentException e) {
                System.err.println("접근이 잘못되었습니다.");
                System.err.println("프로그램을 종료합니다.");
                break;
            } catch (AuthorizedException e) {
                System.err.println(e.getMessage());
            }
        } while (!isExit(View.state));

    }

    private static boolean isExit(View.ViewState state) {
        return View.ViewState.Exit.equals(state);
    }

}
