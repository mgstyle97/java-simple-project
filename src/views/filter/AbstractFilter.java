package views.filter;

import views.View;

public abstract class AbstractFilter extends View {

    protected final View view;

    public AbstractFilter(final View view) {
        this.view = view;
    }

    @Override
    public void printPage() {
        preHandle();
        view.printPage();
        postHandle();
    }

    @Override
    protected void handleRequest(Integer command) {
    }

    protected abstract void preHandle();
    protected abstract void postHandle();
}
