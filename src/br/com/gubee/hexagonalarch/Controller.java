package br.com.gubee.hexagonalarch;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {
    public static void main(String[] args) {
        ScheduledExecutorService controller = Executors.newSingleThreadScheduledExecutor();
        var notificationUseCase = new PoolingUseCaseNotification();
        var notificationUseCaseProxy = TransactionHandler.createProxy(notificationUseCase);

        NotificationFactory emailPresenter = new EmailFactory();
        NotificationFactory whatsAppPresenter = new WhatsAppFactory();
        NotificationFactory smsPresenter = new SmsFactory();

        PresenterNotification[] notifications = {emailPresenter.createPresenterNotification(),
                whatsAppPresenter.createPresenterNotification(),
                smsPresenter.createPresenterNotification()};

        controller.scheduleAtFixedRate(() -> {
            var nextPos = Math.abs(new Random().nextInt()) % 3;

            notificationUseCaseProxy.notifyEveryHour(UUID.randomUUID().toString(), notifications[nextPos]);
            System.out.println();
        }, 1, 1, TimeUnit.SECONDS);
    }
}
