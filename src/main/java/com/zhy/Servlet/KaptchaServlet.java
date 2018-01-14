package com.zhy.Servlet;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author: zhangocean
 * @Date: Created in 15:29 2018/1/14
 * Describe: 重写KaptchaServlet来实现自己的验证码图片
 */
public class KaptchaServlet extends HttpServlet implements Servlet {
    private Properties props = new Properties();
    private Producer kaptchaProducer = null;
    private String sessionKeyValue = null;

    public KaptchaServlet() {
    }

    @Override
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ImageIO.setUseCache(false);
        Enumeration initParams = conf.getInitParameterNames();

        while(initParams.hasMoreElements()) {
            String key = (String)initParams.nextElement();
            String value = conf.getInitParameter(key);
            this.props.put(key, value);
        }

        Config config = new Config(this.props);
        this.kaptchaProducer = config.getProducerImpl();
        this.sessionKeyValue = config.getSessionKey();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setDateHeader("Expires", 0L);
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
        resp.setHeader("Pragma", "no-cache");
        resp.setContentType("image/jpeg");
        String capText = this.kaptchaProducer.createText();
        int first = Integer.parseInt(capText.substring(0,1));
        int end = Integer.parseInt(capText.substring(1,2));
        String pic = first + "+" + end + "=" + "?";
        String result = String.valueOf(first+end);
//        将结果值放入session中
        req.getSession().setAttribute(this.sessionKeyValue, result);
//        绘制图片
        BufferedImage bi = this.kaptchaProducer.createImage(pic);
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(bi, "jpg", out);

        try {
            out.flush();
        } finally {
            out.close();
        }

    }
}

