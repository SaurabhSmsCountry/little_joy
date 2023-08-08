package com.littlejoyindia.littlejoyindia.receiver;


public interface OtpReceivedInterface {
  void onOtpReceived(String otp);
  void onOtpTimeout();
}
