package com.cbedoy.apprende.new_version.interfaces;

public interface IMessageRepresentationHandler {

    public void showLoading();

    public void hideLoading();

    public void hideMessage();

    public void showCode(MESSAGE_REPRESENTATION_CODE code);

    public void showCodeMessage(MESSAGE_REPRESENTATION_CODE code, String message);

    public void showCodeTitleMessage(MESSAGE_REPRESENTATION_CODE code, String title, String message);

    public void showCodeTitleMessageWithCallback(MESSAGE_REPRESENTATION_CODE code, String title, String message, IMessageRepresentationCallback callback);

    public enum MESSAGE_REPRESENTATION_CODE {
        K_INVALID_NICK,
        K_INVALID_PIN,
        K_INVALID_LOGIN,
        K_LOCKED_ACCOUNT,
        K_NOT_ENOUGH_FOUNDS,
        K_INVALID_OTP,
        K_PROCESS_ERROR,
        K_MAIL_SENT,
        K_INVALID_MAIL,
        K_INVALID_AMOUNT,
        K_INVALID_PHONE,
        K_INVALID_MESSAGE,
        K_NETWORK_ERROR,
        K_GENERAL_ERROR,
        K_SUCCESS,
        K_JUMIO_ERROR,
        K_MAX_AMOUNT,
        K_ERROR_OTP_PROC,
        K_INVALID_USER

    }

    public interface IMessageRepresentationCallback {

        public void run();

    }

}
