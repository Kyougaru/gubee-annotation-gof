package br.com.gubee.hexagonalarch;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionHandler implements InvocationHandler {
    private final UseCaseNotification target;

    public TransactionHandler(UseCaseNotification target) {
        this.target = target;
    }

    public static UseCaseNotification createProxy(UseCaseNotification target) {
        return (UseCaseNotification) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new TransactionHandler(target));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Transaction.class)) {
            System.out.println("Iniciando execução do método " + method.getName());

            try {
                Object result = method.invoke(target, args);
                System.out.println("Finalizando execução do método " + method.getName() + " com sucesso");
                return result;
            } catch (Exception e) {
                System.out.println("Finalizando execução do método " + method.getName() + " com erro");
                throw e;
            }
        } else {
            return method.invoke(target, args);
        }
    }
}
