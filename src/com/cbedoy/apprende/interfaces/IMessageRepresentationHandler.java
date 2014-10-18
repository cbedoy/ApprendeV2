package com.cbedoy.apprende.interfaces;

public interface IMessageRepresentationHandler {

    public void showLoading();

    public void hideLoading();

    public void hideMessage();

    public void showCode(MESSAGE_REPRESENTATION_CODE code);

    public void showCodeMessage(MESSAGE_REPRESENTATION_CODE code, String message);

    public void showCodeTitleMessage(MESSAGE_REPRESENTATION_CODE code, String title, String message);

    public void showCodeTitleMessageWithCallback(MESSAGE_REPRESENTATION_CODE code, String title, String message, IMessageRepresentationCallback callback);

    public enum MESSAGE_REPRESENTATION_CODE {
        K_INVALID_LOGIN,
    }

    public interface IMessageRepresentationCallback {

        public void run();

    }

}
