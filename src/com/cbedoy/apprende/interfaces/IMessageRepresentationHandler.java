package com.cbedoy.apprende.interfaces;

public interface IMessageRepresentationHandler {

    public void showLoading();

    public void hideLoading();

    public void hideMessage();

    public void showCode(NOTIFICATION_CODE code);

    public void showCodeMessage(NOTIFICATION_CODE code, String message);

    public void showCodeTitleMessage(NOTIFICATION_CODE code, String title, String message);

    public void showCodeTitleMessageWithCallback(NOTIFICATION_CODE code, String title, String message, IMessageRepresentationCallback callback);

    public enum NOTIFICATION_CODE {
        K_INVALID_LOGIN,
    }

    public interface IMessageRepresentationCallback {

        public void run();

    }

}
