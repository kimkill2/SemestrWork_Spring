package ru.itis.semestrwork_spring.services;

public interface SmsService {
    boolean sendAndStoreCode(String phoneNumber);
    boolean verifySms(String phoneNumber, String inputCode);
    boolean sendVerificationCode(String phoneNumber, String code);
}
