package com.zhj.web;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

import java.util.Calendar;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @version V1.0
 * @Description: 发送邮件的工具类
 * @Modified By:Ming Created in  16:51 2017/2/6
 */
public class Sendemail {
    private final static String smtpHost = "SMTP.qq.com";//配置Email session对象
    private final static String messageType = "text/html;charset=UTF-8";//相应内容类型，编码类型
    private final static String subject = "订单信息";//主题
    private final static String fromEmail = "972418605@qq.com";//发送邮件的邮箱
    private final static String password = "####";//密码

    @SuppressWarnings("static-access")
    public static boolean sendMail(String to, String msg) {
        try {
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";//SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            //配置javax.mail.Session对象
            Properties props = new Properties();   // 创建Properties 类用于记录邮箱的一些属性
            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.put("mail.smtp.host", smtpHost);  //SMTP服务器
            props.put("mail.smtp.starttls.enable", "true");//使用 STARTTLS安全连接
            props.put("mail.smtp.port", "465");             //google使用465或587端口
            props.put("mail.smtp.auth", "true");       // 表示SMTP发送邮件，必须进行身份验证
            props.put("mail.debug", "true");      //开启调试模式
            props.put("mail.transport.protocol", "SMTP");     // 发送邮件协议名称
            props.setProperty("mail.smtp.socketFactory.port", "465");
            Session mailSession = Session.getInstance(props,new MyAuthenticator(fromEmail, password));//的账号和口令(16位口令)
            props.put("mail.smtp.ssl.socketFactory", sf);
            //第二步：编写消息
            InternetAddress fromAddress = new InternetAddress(fromEmail);// 设置发件人的邮箱
            InternetAddress toAddress = new InternetAddress(to); // 设置收件人的邮箱
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(fromAddress);
            message.addRecipient(RecipientType.TO, toAddress);
            message.setSentDate(Calendar.getInstance().getTime());
            message.setSubject(subject);   // 设置邮件标题
            message.setContent(msg, messageType);// 设置邮件的内容体

            // 第三步：发送消息
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(smtpHost, fromEmail, password);
            transport.send(message, message.getRecipients(RecipientType.TO)); // 发送邮件啦

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static class MyAuthenticator extends Authenticator {
        private String userName = "";
        private String password = "";

        public MyAuthenticator(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(userName, password);
        }
    }
}

