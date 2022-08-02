package cn.night.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping("captcha")
public class CaptchaController {
    private char[] codeArr = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @RequestMapping("code")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        int width = 80;
        int height = 37;
        Random random = new Random();

        // 设置response头信息
        // 禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        // 产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        // Graphics类的样式
        // 设定背景色
        g.setColor(this.getColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Times New Roman", 0, 28));
        // 绘制干扰线 -- 随机生成 -- 使图像中的验证码不易被其它程序检测到
        for (int i = 0; i < 40; i++) {
            g.setColor(this.getColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }
        // 绘制字符
        StringBuilder strCode = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(codeArr[random.nextInt(codeArr.length)]);
            strCode.append(rand);
            // 将验证码显示到图像中
            g.setColor(new Color(random.nextInt(110) + 20,
                    random.nextInt(110) + 20,
                    random.nextInt(110) + 20));
            g.drawString(rand, 13 * i + 6, 28);
        }
        session.setAttribute("captcha", strCode.toString().toLowerCase()); //大小写都可
        g.dispose();
        // 输出图像到页面
        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
    }

    private Color getColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
