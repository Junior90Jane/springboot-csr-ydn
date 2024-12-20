package com.ydn.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.kaptcha.Producer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.UUID;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CaptchaController {

    private final Producer captchaProducer;
    
    // 使用 ConcurrentHashMap 來存儲驗證碼
    private static final Map<String, CaptchaInfo> captchaStorage = new ConcurrentHashMap<>();

    @Autowired
    public CaptchaController(Producer captchaProducer) {
        this.captchaProducer = captchaProducer;
    }

    // 內部類用於存儲驗證碼信息
    private static class CaptchaInfo {
        String code;
        LocalDateTime expireTime;

        CaptchaInfo(String code, LocalDateTime expireTime) {
            this.code = code;
            this.expireTime = expireTime;
        }
    }

    @GetMapping("/captcha")
    public ResponseEntity<?> generateCaptcha() {
        try {
            // 生成驗證碼文字
            String captchaText = captchaProducer.createText();
            
            // 生成驗證碼圖片
            BufferedImage image = captchaProducer.createImage(captchaText);
            
            // 將圖片轉換為 Base64
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());

            // 生成唯一ID
            String captchaId = UUID.randomUUID().toString();
            
            // 儲存驗證碼信息（5分鐘後過期）
            captchaStorage.put(captchaId, new CaptchaInfo(
                captchaText,
                LocalDateTime.now().plusMinutes(5)
            ));

            // 清理過期的驗證碼
            cleanExpiredCaptchas();

            return ResponseEntity.ok(Map.of(
                "captchaId", captchaId,
                "captchaImage", "data:image/png;base64," + base64Image
            ));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(Map.of("message", "生成驗證碼失敗"));
        }
    }

    @PostMapping("/verify-captcha")
    public ResponseEntity<?> verifyCaptcha(@RequestBody Map<String, String> request) {
        String captchaId = request.get("captchaId");
        String captchaInput = request.get("captchaInput");

        CaptchaInfo storedCaptcha = captchaStorage.get(captchaId);
        
        if (storedCaptcha == null) {
            return ResponseEntity.ok(Map.of(
                "valid", false,
                "message", "驗證碼已過期"
            ));
        }

        if (LocalDateTime.now().isAfter(storedCaptcha.expireTime)) {
            captchaStorage.remove(captchaId);
            return ResponseEntity.ok(Map.of(
                "valid", false,
                "message", "驗證碼已過期"
            ));
        }

        boolean isValid = storedCaptcha.code.equalsIgnoreCase(captchaInput);
        
        // 驗證後立即刪除驗證碼，防止重複使用
        captchaStorage.remove(captchaId);

        return ResponseEntity.ok(Map.of(
            "valid", isValid,
            "message", isValid ? "驗證成功" : "驗證碼錯誤"
        ));
    }

    private void cleanExpiredCaptchas() {
        LocalDateTime now = LocalDateTime.now();
        captchaStorage.entrySet().removeIf(entry -> 
            entry.getValue().expireTime.isBefore(now)
        );
    }
}
