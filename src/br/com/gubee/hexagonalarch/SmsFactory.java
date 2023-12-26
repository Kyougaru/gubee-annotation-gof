package br.com.gubee.hexagonalarch;

public class SmsFactory implements NotificationFactory {
    @Override
    public PresenterNotification createPresenterNotification() {
        return (message) -> System.out.printf("sms %s", message);
    }
}
