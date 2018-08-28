package com.mycreazy.springbootweb.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.File;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件辅助类
 */
@Component
public class EmailUtil {
    /**
     * 邮件发送协议
     */
    private final static String PROTOCOL = "smtp";

    /**
     * SMTP邮件服务器
     */
    private final static String HOST = "smtp.sina.cn";

    /**
     * SMTP邮件服务器默认端口
     */
    private final static String PORT = "25";

    /**
     * 是否要求身份认证
     */
    private final static String IS_AUTH = "true";

    /**
     * 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
     */
    private final static String IS_ENABLED_DEBUG_MOD = "false";

    /**
     * 初始化连接邮件服务器的会话信息
     */
    private static Properties props = null;

    /**
     * 参数对象
     */
    @Autowired
   private ParamManager paramUtil;

    /**
     * 静态构造函数初始化参数
     */
    static {
        props = new Properties();
        props.setProperty("mail.transport.protocol", PROTOCOL);
        props.setProperty("mail.smtp.host", HOST);
        props.setProperty("mail.smtp.port", PORT);
        props.setProperty("mail.smtp.auth", IS_AUTH);
        props.setProperty("mail.debug", IS_ENABLED_DEBUG_MOD);
    }

    /**
     * 发送邮件
     *
     * @param emailTopic  邮件主题
     * @param sendContent 发送内容
     * @param receiveStr  接收内容
     * @param attchPath   附件地址
     * @param attchName   附件名称
     * @return
     */
    public boolean sendMail(String emailTopic, String sendContent, String receiveStr, String attchPath, String attchName) {
        try {
            // 创建Session实例对象
            Session session1 = Session.getDefaultInstance(props);
            // 创建MimeMessage实例对象
            MimeMessage message = new MimeMessage(session1);
            // 设置发件人
            message.setFrom(new InternetAddress(paramUtil.getSendPerson()));
            // 设置邮件主题
            message.setSubject(emailTopic);
            String[] tempreceivearray = receiveStr.split("\\,");
            int templength = tempreceivearray.length;
            InternetAddress[] toall = new InternetAddress[templength];
            for (int i = 0; i < templength; i++) {
                toall[i] = new InternetAddress(tempreceivearray[i]);
            }

            //整封邮件的MINE消息体
            //混合的组合关系
            MimeMultipart msgMultipart = new MimeMultipart("mixed");
            //设置邮件的MINE消息体
            message.setContent(msgMultipart);
            MimeBodyPart attch1 = null;
            if (!"".equals(attchPath)) {
                //说明需要发送附件
                //附件1
                attch1 = new MimeBodyPart();
                //数据源
                DataSource ds1 = new FileDataSource(new File(attchPath));
                //数据处理器
                DataHandler dh1 = new DataHandler(ds1);
                //设置第一个附件的数据
                attch1.setDataHandler(dh1);
                //设置第一个附件的文件名
                attch1.setFileName(attchName);
                attch1.setFileName(MimeUtility.encodeWord(attchName));
                msgMultipart.addBodyPart(attch1);
            }


            // 设置收件人
            message.setRecipients(Message.RecipientType.TO, toall);
            // 设置发送时间
            message.setSentDate(new Date());
            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendContent, "text/html;charset=UTF-8");
            msgMultipart.addBodyPart(contentPart);
            message.setContent(msgMultipart);
            // 保存并生成最终的邮件内容
            message.saveChanges();

            // 获得Transport实例对象
            Transport transport = session1.getTransport();
            // 打开连接
            transport.connect("","");
            // 将message对象传递给transport对象，将邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            // 关闭连接
            transport.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 发送邮件
     *
     * @param sendContent
     */
    public boolean sendMail(String emailTopic, String sendContent,String emailReceiver) {
        try {
            // 创建Session实例对象
            Session session1 = Session.getDefaultInstance(props);
            // 创建MimeMessage实例对象
            MimeMessage message = new MimeMessage(session1);
            // 设置发件人
            message.setFrom(new InternetAddress(paramUtil.getSendPerson()));
            // 设置邮件主题
            message.setSubject(emailTopic);
            String resceiveStr="";
            if("".equals(emailReceiver)||emailReceiver==null)
            {
                resceiveStr = paramUtil.getMailReceiver();
            }
            else
            {
                resceiveStr=emailReceiver;
            }

            String[] tempreceivearray = resceiveStr.split("\\,");
            int templength = tempreceivearray.length;
            InternetAddress[] toall = new InternetAddress[templength];
            for (int i = 0; i < templength; i++) {
                toall[i] = new InternetAddress(tempreceivearray[i]);
            }

            // 设置收件人
            message.setRecipients(Message.RecipientType.TO, toall);
            // 设置发送时间
            message.setSentDate(new Date());
            // 设置纯文本内容为邮件正文
            message.setText(sendContent);
            // 保存并生成最终的邮件内容
            message.saveChanges();

            // 获得Transport实例对象
            Transport transport = session1.getTransport();
            // 打开连接
            transport.connect(paramUtil.getSendmailUsername(), paramUtil.getSendmailPassword());
            // 将message对象传递给transport对象，将邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            // 关闭连接
            transport.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
