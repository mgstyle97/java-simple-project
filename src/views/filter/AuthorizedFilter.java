package views.filter;

import views.View;
import views.filter.exceptions.AuthorizedException;

public class AuthorizedFilter extends AbstractFilter {

    public AuthorizedFilter(final View view) {
        super(view);
    }

    @Override
    protected void preHandle() {
        if (View.user == null) {
            throw new AuthorizedException();
        }
    }

    @Override
    protected void postHandle() {

    }
}
