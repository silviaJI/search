package com.im.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;

import com.base.support.PropertySupport;

/**
 * 后台新闻类文件写入工具	2014-7-13
 * @author yjh
 *
 */
public class WebPageFileController {
	private File[] tempFils;
	private String appid;
	private String module;
	private String encoding = "UTF-8";
	private String path = "";
	private String subPath = "";
	
	public static String Web_Url = PropertySupport.getProperty("web.url");
	
	//<style type='text/css'>h1,h2{font-size: 60px;}h1,h2,h3,h4,h5{text-align:center;} p{width: 100%;} * {font-family: 'Microsoft YaHei';font-size: 40px;} img {width: 450px;height:auto;margin: 0 auto;}</style>
	public static String UTF8HTML_HEADER = "<!DOCTYPE html><html><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
			"<head><meta name=”viewport” content=”width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no”/>" +
			" <meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\">" +
			"<link href=\"/web/bootstrap-3.2.0-dist/css/bootstrap.min.css\" rel=\"stylesheet\">" +
			"<link href=\"/web/css/web_show_zsnd.css\" rel=\"stylesheet\">" +
			"<body><!--editorcontentstart-->";
	public static String GBKHTML_HEADER = "<!DOCTYPE html><html><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
			"<head><meta name=”viewport” content=”width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no”/>" +
			" <meta http-equiv=\"content-type\" content=\"text/html;charset=gbk\">" +
			"<link href=\"/web/bootstrap-3.2.0-dist/css/bootstrap.min.css\" rel=\"stylesheet\">" +
			"<link href=\"/web/css/web_show_zsnd.css\" rel=\"stylesheet\">" +
			"<body><!--editorcontentstart-->";// style='word-break:break-all;word-wrap:break-word;overflow:hidden;'  bgproperties='fixed'
	public static String HTML_FOOTER = "<!--editorcontentend--><script src=\"http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js\"></script>" +
			"<script src=\"/web/bootstrap-3.2.0-dist/js/bootstrap.min.js\"></script><script src=\"/web/js/requestController.js\"></script></body></html>";
//	public static String TEXT_STYLE= "<p style='margin-top:auto;margin-bottom:auto;text-indent:32px;line-height:50px;font-size:42px;'>";
	
	/**
	 * 根据url删除可以不用输入模块和学校
	 */
	public WebPageFileController() {
		super();
	}

	/**
	 * 出入对应的appid和moudle（如baihe,baiheImg,news等等，）
	 * @param appid
	 * @param module
	 * @param encoding
	 */
	public WebPageFileController(String appid, String module, String encoding) {
		super();
		this.appid = appid;
		this.module = module;
		this.encoding = encoding;
		StringBuilder sb = new StringBuilder();
		subPath = sb.append(this.appid).append("/").append(this.module).append("/").toString();
		path = sb.insert(0, PropertySupport.getProperty("file.path")).toString();
		sb.setLength(0);
	}
	
	public void setParams(String appid, String module, String encoding) {
		this.appid = appid;
		this.module = module;
		this.encoding = encoding;
		StringBuilder sb = new StringBuilder();
		subPath = sb.append(this.appid).append("/").append(this.module).append("/").toString();
		path = sb.insert(0, PropertySupport.getProperty("file.path")).toString();
		sb.setLength(0);
	}
	
	/**
	 * 获取目录下当前的文件集
	 */
	public void saveCurrentFile() {
		this.tempFils = DeleteFile.directoryNum(path);
	}
	
	/**
	 * 删掉缓存下的文件集
	 */
	public void deleteOldFile() {
		DeleteFile.deleteDirectoryCount(this.tempFils);
	}
	
	/**
	 * 将文本内容保存成文件，并返回url字段
	 * @param content
	 * @param fileFormat
	 * @return
	 * @throws Exception 
	 */
	public String write(String content, String fileFormat) throws Exception {
		String result = null;
		StringBuilder sb = new StringBuilder();
		String url = sb.append(System.currentTimeMillis()).append('.').append(fileFormat).toString();
		sb.setLength(0);
		FileAccessor.writeAll(sb.append(this.subPath).append(url).toString(), content, this.encoding);
		sb.setLength(0);
		result = sb.append(this.subPath).append(url).toString();
		return result;
	}
	
	/**
	 * 将文本内容保存成文件，指定文件名，并返回url字段
	 * @param content
	 * @param fileFormat
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public String write(String content, String fileFormat, String name) throws Exception {
		String result = null;
		StringBuilder sb = new StringBuilder();
		String url = sb.append(name).append('.').append(fileFormat).toString();
		sb.setLength(0);
		FileAccessor.writeAll(sb.append(this.subPath).append(url).toString(), content, this.encoding);
		sb.setLength(0);
		result = sb.append(this.subPath).append(url).toString();
		sb.setLength(0);
		return result;
	}
	
	/**
	 * 返回以/开头
	 * @param fileFormat
	 * @param name
	 * @return
	 */
	public String getUrl(String fileFormat, String name) {
		String result = null;
		StringBuilder sb = new StringBuilder();
		String url = sb.append(name).append('.').append(fileFormat).toString();
		sb.setLength(0);
		result = sb.append('/').append(this.subPath).append(url).toString();
		sb.setLength(0);
		return result;
	}
	
	/**
	 * 覆盖原有文件
	 * @param content
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public String writeByUrl(String content, String url) throws Exception {
		FileAccessor.writeAll(url, content, this.encoding);
		return url;
	}
	
	/**
	 * 写入图片
	 * @param content
	 * @param fileFormat
	 * @return
	 * @throws IOException
	 */
	public String write(BufferedImage content, String fileFormat) throws IOException {
		String result = null;
		StringBuilder sb = new StringBuilder();
		String url = sb.append(System.currentTimeMillis()).append(".").append(fileFormat).toString();
		sb.setLength(0);
		File fileimg = new File(sb.append(this.path).append(url).toString());
		sb.setLength(0);
        fileimg.getParentFile().mkdirs();
        if(ImageIO.write(content, fileFormat, fileimg)) {
        	result = sb.append("/").append(this.subPath).append(url).toString();
        }
		return result;
	}
	
	/**
	 * 以指定文件名写入文件		2014-7-13
	 * @param content
	 * @param fileFormat
	 * @return
	 * @throws IOException
	 */
	public String write(BufferedImage content, String fileFormat, String name) throws IOException {
		String result = null;
		StringBuilder sb = new StringBuilder();
		String url = sb.append(name).append(".").append(fileFormat).toString();
		File fileimg = new File(sb.append(this.path).append(url).toString());
		sb.setLength(0);
        fileimg.getParentFile().mkdirs();
        if(ImageIO.write(content, fileFormat, fileimg)) {
        	result = sb.append("/").append(this.subPath).append(url).toString();
        }
		return result;
	}
	
	/**
	 * 删除单个旧文件
	 * @param url
	 */
	public void deleteFile(String url) {
		//FileAccessor.deleteFile(url);
	}
	
	/**
	 * 读取文件内容
	 * @param url
	 * @throws Exception 
	 */
	public String read(String url, String encoding) throws Exception {		
		return FileAccessor.readAll(url, encoding);
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
//	private static String editorWrapper = "<div class=\"editor-wrapper\">(.*?)</div>";
	public static Pattern imgP = Pattern.compile("(<img.+?src=\")(.+?)(\".*?/>)");
	public String getHtmlContentUTF8(String content) throws Exception {
//		content = content.replaceAll("(<img.*?)style=\"\"(>)", "$1/$2");
//		//过滤img元素，微信平台的图片地址，进行转存
//		Matcher imgM = imgP.matcher(content);
//		StringBuffer sbuf = new StringBuffer();
//		System.out.println(content);
//		while(imgM.find()) {
//			String imgUrl = imgM.group(2);
//
//			if(imgUrl.toLowerCase().startsWith("http://") && imgUrl.startsWith("http://mmbiz")) {
//				String key = FileAccessor.getNetFile(imgUrl);
//				System.out.println(Web_Url + key);
//				imgM.appendReplacement(sbuf, imgM.group(1) + Web_Url + key + imgM.group(3));
//			}
//		}
//		imgM.appendTail(sbuf);
//		content = sbuf.toString();
		content = content.replace("?tp=webp", "");
		StringBuilder sb = new StringBuilder(UTF8HTML_HEADER);
		sb.append(content).append(HTML_FOOTER);
		return sb.toString();
	}
	
	/**
	 * 添加返回按钮
	 */
	private static String backButton = "<div class=\"back-button\"><a href=\"%url%\">返回上级</a></div><!--editorcontentstart-->";
	public String getHtmlContentUTF8(String content, String source) {
		StringBuilder sb = new StringBuilder(UTF8HTML_HEADER.replace("<!--editorcontentstart-->", ""));
		
		if(StringUtils.isNotBlank(source)) {
			sb.append(backButton.replace("%url%", source));
		} else {
			sb.append("<!--editorcontentstart-->");
		}
		
		sb.append(content).append(HTML_FOOTER);
		
		return sb.toString();
	}
}
