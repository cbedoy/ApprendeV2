package com.cbedoy.apprende.business.timeout;

import android.os.CountDownTimer;

import com.cbedoy.apprende.business.timeout.interfaces.ITimeOutTransactionDelegate;
import com.cbedoy.apprende.business.timeout.interfaces.ITimeOutTransactionHandler;


public class TimeOutBusinessController implements ITimeOutTransactionDelegate {

    private static long mMilli = 1;
    private static long mSecond = mMilli * 1000;
    private static long mMinute = mSecond * 60;
    private static long mSessionExpirationTime = 5 * mMinute;
    private static long mTimerStartedMillis = 0;
    private CountDownTimer mCountDownTimer;
    private ITimeOutTransactionHandler mTransactionHandler;

    public void setTransactionHandler(ITimeOutTransactionHandler transactionHandler) {
        mTransactionHandler = transactionHandler;
    }

    @Override
    public void onPause() {
        if (hasStarted()) {
            cancelTimer();
            mTimerStartedMillis = System.currentTimeMillis();
        }
    }

    @Override
    public void onResume() {
        if (hasStarted() && mCountDownTimer == null) {
            long remainingTime = mSessionExpirationTime - (System.currentTimeMillis() - mTimerStartedMillis);
            if (remainingTime < 1) {
                mTransactionHandler.timerFinished();
            } else {
                cancelTimer();
                scheduleTimer(remainingTime);
            }
        }
    }

    @Override
    public void startTimer() {
        cancelTimer();
        scheduleTimer(mSessionExpirationTime);
    }

    private void scheduleTimer(long secondsRemaining) {
        if (hasStarted()) {
            return;
        }

        if (mCountDownTimer == null) {
            mCountDownTimer = new CountDownTimer(secondsRemaining, mSessionExpirationTime) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    mTransactionHandler.timerFinished();
                }
            };
        }

        mCountDownTimer.start();
    }

    @Override
    public void cancelTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        mCountDownTimer = null;
        mTimerStartedMillis = 0;
    }

    @Override
    public boolean hasStarted() {
        return mCountDownTimer != null || mTimerStartedMillis != 0;
    }

}
