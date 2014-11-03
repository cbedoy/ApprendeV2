package com.cbedoy.apprende.business.timeout.interfaces;

public interface ITimeOutTransactionDelegate {

    public void onPause();

    public void onResume();

    public void startTimer();

    public void cancelTimer();

    public boolean hasStarted();

}
