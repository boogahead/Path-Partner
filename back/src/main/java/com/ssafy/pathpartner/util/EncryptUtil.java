package com.ssafy.pathpartner.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import org.springframework.stereotype.Component;

@Component
public class EncryptUtil {
    public String createSalt() {
        // Salt는 비밀번호를 암호화할 때 사용하는 임의 문자열
        // 같은 비밀번호가 들어와도 다른 값으로 암호화할 수 있는 역할

        SecureRandom r = new SecureRandom(); // 난수 생성기
        byte[] salt = new byte[15];

        r.nextBytes(salt); // 난수생성기로 난수 생성

        StringBuffer sb = new StringBuffer();
        for (byte b : salt) {
            // 생성한 난수를 10진수의 문자열로 변경
            sb.append(String.format("%02x", b));
        }

        return sb.toString().substring(0, 15); // 15자리의 Salt 사용을 위해 15까지만 반환
    }

    public String getEncrypt(String password, String salt) {
        String result = "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // SHA-256 해시 함수

            md.update((password + salt).getBytes()); // 입력된 Password와 Salt를 통해서 SHA-256 해시 수행
            byte[] pwdSalt = md.digest(); // 해시된 값 저장

            StringBuffer sb = new StringBuffer();
            for(byte b : pwdSalt) {
                // 암호화된 값을 10진수의 문자열로 변경
                sb.append(String.format("%02x", b));
            }

            result = sb.toString();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return result.substring(0, 15); // 15자리의 암호화된 비밀번호 반환
    }
}
