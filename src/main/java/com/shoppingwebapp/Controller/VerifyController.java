package com.shoppingwebapp.Controller;

import com.shoppingwebapp.Model.VerifyCode;
import com.shoppingwebapp.Service.RedisService;

import jakarta.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:5173", allowedHeaders = "http://localhost:5173")
@RequestMapping("/code")
public class VerifyController {
    
    private final VerifyCode verifyCode;
    private String storedCode = "";

    // Inject RedisService into VerifyCode constructor
    @Autowired
    public VerifyController(RedisService redisService) {
        this.verifyCode = new VerifyCode(redisService);
    }

    @GetMapping("/verify")
    public void Verify(HttpServletResponse response) throws IOException {
        BufferedImage image = verifyCode.createImage();
        storedCode = verifyCode.getText();
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    @GetMapping("/checkVerify")
    public String checkVerify(String code) {
        storedCode = verifyCode.getText();
        if(storedCode == null) {
            return"toolate";
        }
        
        if (code == null || code.isEmpty()) {
            return "no code";
        }

        if (storedCode.equals(code)) {
            return "success";
        } else {
            return "fail";
        }
    }
}
