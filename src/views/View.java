package views;

import user.User;

import java.util.Scanner;

public abstract class View {

    protected Scanner scanner;
    public static ViewState state;
    public static User user;

    static {
        state = ViewState.Non_Logged_In;
    }

    public View() {
        this.scanner = new Scanner(System.in);
    }

    protected void exit() {
        System.out.println("프로그램을 종료합니다.");
        state = ViewState.Exit;
    }

    public abstract void printPage();
    protected abstract void handleRequest(final Integer command);

    public enum ViewState {
        Logged_In, Non_Logged_In, Exit
    }
}
