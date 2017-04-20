package com.im.util;
import java.util.List;
import java.util.Properties;
 
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.im.util.SimpleMail;
import com.im.util.EmailCheckUtil;
import com.im.util.SimpleMailSender;
/**
 * 简单邮件发送器，可单发，群发。
 *
 * @author MZULE
 *
 */
public class SimpleMailSender {
 
    /**
     * 发送邮件的props文件
     */
    private final transient Properties props = System.getProperties();
    
    /**
     * 邮件服务器登录验证
     */
    private transient MailAuthenticator authenticator;
 
    /**
     * 邮箱session
     */
    private transient Session session;
 
    /**
     * 初始化邮件发送器
     * @param smtpHostName
     *                SMTP邮件服务器地址
     * @param username
     *                发送邮件的用户名(地址)
     * @param password
     *                发送邮件的密码
     */
    public SimpleMailSender(final String smtpHostName, final String username,
        final String password) {
    init(username, password, smtpHostName);
    }
 
    /**
     * 初始化邮件发送器
     *
     * @param username
     *                发送邮件的用户名(地址)，并以此解析SMTP服务器地址
     * @param password
     *                发送邮件的密码
     */
    public SimpleMailSender(final String username, final String password) {
    //通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
    final String smtpHostName = "smtp." + username.split("@")[1];
    init(username, password, smtpHostName);
 
    }
 
	/**
	 * 初始化
	 * @param username        发送邮件的用户名(地址)
	 * @param password        密码
	 * @param smtpHostName   SMTP主机地址
	 */
    private void init(String username, String password, String smtpHostName) {
		// 初始化props
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpHostName);
		// 验证
		authenticator = new MailAuthenticator(username, password);
		// 创建session
		session = Session.getInstance(props, authenticator);
    }
    
    //带附件的邮件
    public boolean send(String recipient, String subject, Object content, String filePath, String fileName){
		try{
			// 创建mime类型邮件
			final MimeMessage message = new MimeMessage(session);
			// 设置发信人
			message.setFrom(new InternetAddress(authenticator.getUsername()));
			// 设置收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
			// 设置主题
			message.setSubject(subject);
			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            //设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(content.toString(), "text/html;charset=utf-8");
            multipart.addBodyPart(contentPart);
          //添加附件
            BodyPart messageBodyPart= new MimeBodyPart();
            DataSource source = new FileDataSource(filePath);
            //添加附件的内容
            messageBodyPart.setDataHandler(new DataHandler(source));
            //添加附件的标题
            //这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
//            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            String filename= new String(MimeUtility.encodeText(fileName,"utf-8",null));
            messageBodyPart.setFileName(filename);
//            messageBodyPart.setFileName("=?GBK?B?"+enc.encode("测试附件".getBytes())+"?=");
            multipart.addBodyPart(messageBodyPart);
            //将multipart对象放到message中
            message.setContent(multipart);
			// 发送
          
			Transport.send(message);
		}catch(AddressException e){
			e.printStackTrace();
			return false;
		}catch(MessagingException e){
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
    }
    
    //带附件的邮件
    public boolean send(List<String> recipients, String subject, Object content, String filePath, String fileName){
		try{
			// 创建mime类型邮件
			final MimeMessage message = new MimeMessage(session);
			// 设置发信人
			message.setFrom(new InternetAddress(authenticator.getUsername()));
			// 设置收件人们
    	    final int num = recipients.size();
    	    InternetAddress[] addresses = new InternetAddress[num];
    	    for (int i = 0; i < num; i++) {
    	        addresses[i] = new InternetAddress(recipients.get(i));
    	    }
    	    message.setRecipients(RecipientType.TO, addresses);
			// 设置主题
			message.setSubject(subject);
			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            //设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(content.toString(), "text/html;charset=utf-8");
            multipart.addBodyPart(contentPart);
          //添加附件
            BodyPart messageBodyPart= new MimeBodyPart();
            DataSource source = new FileDataSource(filePath);
            //添加附件的内容
            messageBodyPart.setDataHandler(new DataHandler(source));
            //添加附件的标题
            //这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
//            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            String filename= new String(MimeUtility.encodeText(fileName,"utf-8",null));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            //将multipart对象放到message中
            message.setContent(multipart);
			// 发送
			Transport.send(message);
		}catch(AddressException e){
			e.printStackTrace();
			return false;
		}catch(MessagingException e){
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
    }
 
    
    /**
     * 发送邮件
     *
     * @param recipient
     *                收件人邮箱地址
     * @param subject
     *                邮件主题
     * @param content
     *                邮件内容
     * @throws AddressException
     * @throws MessagingException
     */
    public boolean send(String recipient, String subject, Object content){
		try{
			// 创建mime类型邮件
			final MimeMessage message = new MimeMessage(session);
			// 设置发信人
			message.setFrom(new InternetAddress(authenticator.getUsername()));
			// 设置收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
			// 设置主题
			message.setSubject(subject);
			// 设置邮件内容
			message.setContent(content.toString(), "text/html;charset=utf-8");
			// 发送
			Transport.send(message);
		}catch(AddressException e){
			e.printStackTrace();
			return false;
		}catch(MessagingException e){
			e.printStackTrace();
			return false;
		}
		return true;
    }
 
    /**
     * 群发邮件
     *
     * @param recipients
     *                收件人们
     * @param subject   主题
     * @param content 内容
     * @throws AddressException
     * @throws MessagingException
     */
    public boolean send(List<String> recipients, String subject, Object content)
        throws AddressException, MessagingException {
    	try{
    		 // 创建mime类型邮件
    	    final MimeMessage message = new MimeMessage(session);
    	    // 设置发信人
    	    message.setFrom(new InternetAddress(authenticator.getUsername()));
    	    // 设置收件人们
    	    final int num = recipients.size();
    	    InternetAddress[] addresses = new InternetAddress[num];
    	    for (int i = 0; i < num; i++) {
    	        addresses[i] = new InternetAddress(recipients.get(i));
    	    }
    	    message.setRecipients(RecipientType.TO, addresses);
    	    // 设置主题
    	    message.setSubject(subject);
    	    // 设置邮件内容
    	    message.setContent(content.toString(), "text/html;charset=utf-8");
    	    // 发送
    	    Transport.send(message);
    	}catch(AddressException e){
			e.printStackTrace();
			return false;
		}catch(MessagingException e){
			e.printStackTrace();
			return false;
		}
		return true;
   
    }
 
    /**
     * 发送邮件
     *
     * @param recipient
     *                收件人邮箱地址
     * @param mail
     *                邮件对象
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(String recipient, SimpleMail mail)
        throws AddressException, MessagingException {
        send(recipient, mail.getSubject(), mail.getContent());
    }
 
    /**
     * 群发邮件
     *
     * @param recipients
     *                收件人们
     * @param mail
     *                邮件对象
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(List<String> recipients, SimpleMail mail)
        throws AddressException, MessagingException {
        send(recipients, mail.getSubject(), mail.getContent());
    }
    
    //创建一个sender实例
    public static SimpleMailSender create(){
       SimpleMailSender sender = new SimpleMailSender(ConstantResources.MAILUSERNAME, ConstantResources.MAILPASSWORD);
       return sender;
    }
  //这个版本需要根据邮件类型选择发送邮件的邮箱
    public static SimpleMailSender create(String email){
    	String[] mailInfo = EmailCheckUtil.getEmailInfo(email);
        SimpleMailSender sender = new SimpleMailSender(mailInfo[0], mailInfo[1]);
        return sender;
     }
 
}
