package br.com.gubee.hexagonalarch;

public class WhatsAppFactory implements NotificationFactory {
    @Override
    public PresenterNotification createPresenterNotification() {
        return (message) -> System.out.printf("whatApp %s", message);
    }
}
