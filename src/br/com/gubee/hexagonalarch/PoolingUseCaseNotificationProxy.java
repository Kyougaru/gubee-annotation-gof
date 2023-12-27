package br.com.gubee.hexagonalarch;

import java.lang.reflect.Method;

public class PoolingUseCaseNotificationProxy implements UseCaseNotification {
    private final PoolingUseCaseNotification target;

    public PoolingUseCaseNotificationProxy(PoolingUseCaseNotification target) {
        this.target = target;
    }

    public void notifyEveryHour(String customerId, PresenterNotification presenter) {
        try {
            Method method = target.getClass().getMethod("notifyEveryHour", String.class, PresenterNotification.class);
            if (method.isAnnotationPresent(Transaction.class)) {
                    System.out.println("Iniciando execução do método " + method.getName());
                    try {
                        target.notifyEveryHour(customerId, presenter);
                        System.out.println("Finalizando execução do método " + method.getName() + " com sucesso");
                    } catch (Exception e) {
                        System.out.println("Finalizando execução do método " + method.getName() + " com erro");
                        throw e;
                    }
            } else {
                target.notifyEveryHour(customerId, presenter);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
