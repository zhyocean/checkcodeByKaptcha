package com.zhy.Servlet;

import com.google.code.kaptcha.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.EmptyStackException;

/**
 * @author: zhangocean
 * @Date: Created in 14:48 2018/1/14
 * Describe:
 */
public class SubmitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        String word = req.getParameter("yzm");
        word = word.toUpperCase();
        String k = (String) req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        k = k.toUpperCase();
        PrintWriter pw = resp.getWriter();
        if(word.equals(k)){
            pw.print("验证码正确");
        } else {
            pw.print("验证码错误");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doGet(req, resp);
    }
}
