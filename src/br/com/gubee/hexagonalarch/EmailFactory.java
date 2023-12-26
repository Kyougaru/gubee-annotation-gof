package br.com.gubee.hexagonalarch;

public class EmailFactory implements NotificationFactory {
    @Override
    public PresenterNotification createPresenterNotification() {
        return (message) -> System.out.printf("email %s", message);
    }
}
