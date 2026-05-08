package ru.itis.semestrwork_spring.services.impl;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.semestrwork_spring.dto.CodeDto;
import ru.itis.semestrwork_spring.services.SmsService;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class SmsServiceImpl implements SmsService {

    @Value("${sms.ru.api-key}")
    private String apiKey;

    @Value("${sms.ru.api-url}")
    private String apiUrl;

    private final OkHttpClient client = new OkHttpClient();
    private Map<String, CodeDto> codeData = new HashMap<>();

    @Override
    public boolean sendAndStoreCode(String phoneNumber) {
        System.out.println(apiUrl + "|" + apiKey);
        System.out.println("Sending: " + phoneNumber);
        String code = String.format("%04d", new Random().nextInt(100000, 999999));
        String cleanPhone = phoneNumber.replaceAll("[^0-9]", "");
        System.out.println("Code: " + code);
        System.out.println("Clean Phone: " + cleanPhone);
        if (sendVerificationCode(cleanPhone, code)) {
            codeData.put(cleanPhone, new CodeDto(code, System.currentTimeMillis()));
            return true;
        }
        return false;
    }

    @Override
    public boolean verifySms(String phoneNumber, String inputCode) {
        System.out.println("Verifying: " + phoneNumber);
        String cleanPhone = phoneNumber.replaceAll("[^0-9]", "");
        CodeDto codeDto = codeData.get(cleanPhone);
        if (codeDto == null) {
            return false;
        }
        if (System.currentTimeMillis() - codeDto.getTimestamp() > 300_000) {
            codeData.remove(cleanPhone);
            return false;
        }
        boolean valid = codeDto.getCode().equals(inputCode);
        if (valid) codeData.remove(cleanPhone);

        return valid;
    }

    @Override
    public boolean sendVerificationCode(String phoneNumber, String code) {
        System.out.println("SendVerificationCode: " + phoneNumber);
        System.out.println("URL: " + apiUrl);
        try {
            String cleanPhone = phoneNumber.replaceAll("[^0-9]", "");
            String message = URLEncoder.encode("Ваш код подтверждения: " + code, "UTF-8");

            System.out.println("apiKey value: '" + apiKey + "'");
            System.out.println("apiUrl template: '" + apiUrl + "'");
            System.out.println("cleanPhone: '" + cleanPhone + "'");
            System.out.println("message: '" + message + "'");

            String url = String.format(apiUrl,
                    apiKey,
                    cleanPhone,
                    message);

            System.out.println("Request URL: " + url); // Логируем URL

            Request request = new Request.Builder().url(url).build();

            try (Response response = client.newCall(request).execute()) {
                String body = response.body().string();
                System.out.println("SMS API Response: " + body); // Логируем ответ API

                // Проверяем статус ответа
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonResponse = mapper.readTree(body);
                String status = jsonResponse.get("status").asText();

                if ("100".equals(status)) {
                    return true;
                } else {
                    System.out.println("SMS API error status: " + status);
                    return false;
                }
            }

        } catch (Exception e) {
            System.out.println("Ошибка в sendVerificationCode: " + e.getMessage());

            e.printStackTrace();
            return false;
        }
    }
}
