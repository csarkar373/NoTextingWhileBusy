package com.westhillcs.notextingwhilebusy;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Chandan on 8/7/2016.
 */
public class TextMessage {
    private String mMessageBody;
    private String mPhoneNumber;
    private String mTimeStamp;

    public TextMessage(String messageBody, String phoneNumber, String timeStamp) {
        this.mMessageBody = messageBody;
        this.mPhoneNumber = phoneNumber;
        this.mTimeStamp = timeStamp;
    }

    public TextMessage(String messageBody, String phoneNumber) {
        this.mMessageBody = messageBody;
        this.mPhoneNumber = phoneNumber;

        Calendar _c = Calendar.getInstance();
        SimpleDateFormat _df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String _formattedDate = _df.format(_c.getTime());
        // formattedDate have current date/time
        mTimeStamp = _formattedDate;


    }



    public String getMessageBody() {
        return mMessageBody;
    }

    public void setMessageBody(String mMessageBody) {
        this.mMessageBody = mMessageBody;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(String mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }

    public String toString() {
        return "\nFrom: " + mPhoneNumber + "\nMsg: " + mMessageBody + "\nAt: " + mTimeStamp;
    }
}
