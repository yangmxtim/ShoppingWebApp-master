
package com.shoppingwebapp.Controller;

import com.shoppingwebapp.Model.VerifyCode;

import jakarta.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;





// @Api(tags = "驗證碼")
@RestController
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:5173", allowedHeaders = "http://localhost:5173")
@RequestMapping("/code")

public class VerifyController {
    
    
    // private RedisTemplate redisTemplate;
    // @Autowired
    private String storedCode = "aaaa";
    @GetMapping("/verify")
    public void Verify( HttpServletResponse response) throws IOException{
        VerifyCode code = new VerifyCode();
        BufferedImage image = code.createImage();

        //驗證碼
        System.err.println(code.getText());
        storedCode = code.getText();
        //存入session
        // session.setAttribute("code", code.getText());
        // request.getSession().setAttribute("code", code.getText());

        //驗證圖片格式
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    @GetMapping("/checkVerify")
    public String checkVerify(@RequestParam String code) {
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

