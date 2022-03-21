package views;

import views.filter.AuthorizedFilter;
import views.loggedin.LoggedInView;
import views.purchasableview.managecart.ManageCartView;
import views.managepurchasedProduct.ManagePurchasedProductView;
import views.nonloggedin.NonLoggedInView;
import views.manageregisterproduct.ManageRegisterProductView;
import views.purchasableview.productlist.ProductListView;
import views.registerproduct.RegisterProductView;

public class ViewFactory {

    private static final View LOGGED_IN_VIEW;
    private static final View NON_LOGGED_IN_VIEW;
    private static final View PRODUCT_LIST_VIEW;
    private static final View REGISTER_PRODUCT_VIEW;
    private static final View MANAGE_REGISTER_PRODUCT_VIEW;
    private static final View MANAGE_PURCHASED_PRODUCT_VIEW;
    private static final View MANAGE_CART_VIEW;

    private static final View AUTHORIZED_LOGGED_IN_VIEW;
    private static final View AUTHORIZED_PRODUCT_LIST_VIEW;
    private static final View AUTHORIZED_REGISTER_PRODUCT_VIEW;
    private static final View AUTHORIZED_MANAGE_REGISTER_PRODUCT_VIEW;
    private static final View AUTHORIZED_MANAGE_PURCHASED_PRODUCT_VIEW;
    private static final View AUTHORIZED_MANAGE_CART_VIEW;

    static {
        LOGGED_IN_VIEW = new LoggedInView();
        NON_LOGGED_IN_VIEW = new NonLoggedInView();
        PRODUCT_LIST_VIEW = new ProductListView();
        REGISTER_PRODUCT_VIEW = new RegisterProductView();
        MANAGE_REGISTER_PRODUCT_VIEW = new ManageRegisterProductView();
        MANAGE_PURCHASED_PRODUCT_VIEW = new ManagePurchasedProductView();
        MANAGE_CART_VIEW = new ManageCartView();

        AUTHORIZED_LOGGED_IN_VIEW = new AuthorizedFilter(LOGGED_IN_VIEW);
        AUTHORIZED_PRODUCT_LIST_VIEW = new AuthorizedFilter(PRODUCT_LIST_VIEW);
        AUTHORIZED_REGISTER_PRODUCT_VIEW = new AuthorizedFilter(REGISTER_PRODUCT_VIEW);
        AUTHORIZED_MANAGE_REGISTER_PRODUCT_VIEW = new AuthorizedFilter(MANAGE_REGISTER_PRODUCT_VIEW);
        AUTHORIZED_MANAGE_PURCHASED_PRODUCT_VIEW = new AuthorizedFilter(MANAGE_PURCHASED_PRODUCT_VIEW);
        AUTHORIZED_MANAGE_CART_VIEW = new AuthorizedFilter(MANAGE_CART_VIEW);

    }

    public static View generateView(final View.ViewState state) {
        switch (state) {
            case Logged_In:
                return AUTHORIZED_LOGGED_IN_VIEW;
            case Non_Logged_In:
                return NON_LOGGED_IN_VIEW;
            case Lookup_Product_List:
                return AUTHORIZED_PRODUCT_LIST_VIEW;
            case Register_Product:
                return AUTHORIZED_REGISTER_PRODUCT_VIEW;
            case Manage_Register_Product:
                return AUTHORIZED_MANAGE_REGISTER_PRODUCT_VIEW;
            case Manage_Purchased_Product:
                return AUTHORIZED_MANAGE_PURCHASED_PRODUCT_VIEW;
            case Manage_Cart:
                return AUTHORIZED_MANAGE_CART_VIEW;
            default:
                throw new IllegalArgumentException("잘못된 접근입니다.");
        }
    }

}
