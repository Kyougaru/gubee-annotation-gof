package br.com.gubee.hexagonalarch;

public interface UseCaseNotification {
    @Transaction
    void notifyEveryHour(String customerId, PresenterNotification presenter);
}