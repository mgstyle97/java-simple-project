package views;

import product.ProductRepository;
import user.User;
import user.UserRepository;

import java.util.Scanner;

public abstract class View {

    public static ViewState state;
    public static User user;

    protected Scanner scanner;
    protected static final UserRepository userRepository;
    protected static final ProductRepository productRepository;

    static {
        state = ViewState.Non_Logged_In;
        userRepository = new UserRepository();
        productRepository = new ProductRepository();
    }

    public View() {
        this.scanner = new Scanner(System.in);
    }

    protected void exit() {
        System.out.println("프로그램을 종료합니다.");
        state = ViewState.Exit;
    }

    protected Integer inputCommand() {
        return Integer.valueOf(scanner.nextLine());
    }

    public abstract void printPage();
    protected abstract void handleRequest(final Integer command);

    public enum ViewState {
        Logged_In,
        Non_Logged_In,

        Lookup_Product_List,
        Register_Product,

        Manage_Register_Product,
        Manage_Purchased_Product,
        Manage_Cart,

        Exit
    }
}
