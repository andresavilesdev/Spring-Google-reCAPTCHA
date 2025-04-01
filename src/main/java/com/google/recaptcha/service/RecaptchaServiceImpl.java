package com.google.recaptcha.service;

import com.google.recaptcha.service.response.RecaptchaResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RecaptchaServiceImpl implements RecaptchaService {

    private static String GOOGLE_RECAPTCHA_ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";
    private static String SECRET_KEY = "6LduxAUrAAAAAJTvu2_9CUmbeIJXTt-lqEPMl24S";

    @Override
    public boolean validateRecaptcha(String captcha) {

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("secret", SECRET_KEY);
        request.add("response", captcha);

        RecaptchaResponse apiResponse = restTemplate.postForObject(GOOGLE_RECAPTCHA_ENDPOINT, request,RecaptchaResponse.class);

        if (apiResponse == null) {
            return false;
        }

        return Boolean.TRUE.equals(apiResponse.getSuccess());
    }
}
