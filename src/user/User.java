package user;

import global.data.Entity;
import global.properties.Money;
import product.Product;

public class User extends Entity {

    private String id;
    private String password;
    private String nick;
    private Money balance;

    public User(final String id, final String password, final String nick) {
        this.id = id;
        this.password = password;
        this.nick = nick;
        this.balance = Money.of(100_000);
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setNick(final String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return this.nick;
    }

    public Money getBalance() {
        return this.balance;
    }

    public boolean checkPassword(final String confirmPassword) {
        return this.password.equals(confirmPassword);
    }

    public void addBalance(final Money price) {
        this.balance = this.balance.add(price);
    }

    public void minusBalance(final Money price) {
        this.balance = this.balance.minus(price);
    }

}
