package br.com.gubee.hexagonalarch;

import java.util.Random;

public class PoolingUseCaseNotification implements UseCaseNotification {
    @Override
    @Transaction
    public void notifyEveryHour(String customerId, PresenterNotification presenter) {
        System.out.println("processando regra de negocio");
        presenter.notification(String.format("mensagem a ser enviada para %s: %s\n", customerId, new Random().nextInt()));
    }
}
