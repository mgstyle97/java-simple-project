package views;

import views.filter.AuthorizedFilter;
import views.loggedin.LoggedInView;
import views.nonloggedin.NonLoggedInView;

public class ViewFactory {

    private static final View LOGGED_IN_VIEW;
    private static final View NON_LOGGED_IN_VIEW;
    private static final View AUTHORIZED_FILTERED_VIEW;

    static {
        LOGGED_IN_VIEW = new LoggedInView();
        NON_LOGGED_IN_VIEW = new NonLoggedInView();
        AUTHORIZED_FILTERED_VIEW = new AuthorizedFilter(LOGGED_IN_VIEW);
    }

    public static View generateView(final View.ViewState state) {
        switch (state) {
            case Logged_In:
                return AUTHORIZED_FILTERED_VIEW;
            case Non_Logged_In:
                return NON_LOGGED_IN_VIEW;
            default:
                throw new IllegalArgumentException("잘못된 접근입니다.");
        }
    }

}
