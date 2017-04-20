package com.im.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.base.CoreConstants;
import com.im.util.ConstantResources;

public class LoginFilter implements Filter {

	public static final String SESSION_USER_KEY = "sessionUser";
	public static final String SESSION_USER_KEYM = "sessionUserM";
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rsp;
		response.setHeader("Access-Control-Allow-Origin", "*"); //管理太设置跨域访问
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String url = request.getServletPath();
		String[] urlStr = CoreConstants.PAGE_UNCHECK.split(",");
		for (String str : urlStr) {
			if (url.indexOf(str) != -1) {
				chain.doFilter(request, response);
				return;
			}
		}
		HttpSession session = request.getSession();
		if (session == null) {
			handleRedirect(basePath, null, response, request);
			return;
		} else {
			Integer isM = (Integer)session.getAttribute("isM");
			if (isM == null) {	
				handleRedirect(basePath, null, response, request);
				return;
			} else {
				//根据isM的情况来分别判断
				if(isM == 0){
					Object obj = session.getAttribute(SESSION_USER_KEY);
					if(obj == null){
						handleRedirect(basePath, null, response, request);
					}else{
						chain.doFilter(request, response);
					}
				}else{
					Object obj = session.getAttribute(SESSION_USER_KEYM);
					if(obj == null){
						handleRedirect(basePath, "isMC", response, request);
					}else{
						chain.doFilter(request, response);
					}
				}
			}
		}
	}
	
	public void handleRedirect(String basePath, String isMC, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
		if(StringUtils.isBlank(isMC))
			request.getRequestDispatcher(CoreConstants.PAGE_OUTPUT).forward(request, response);
		else {
//			PrintWriter out = response.getWriter();
//			out.println(JSONRepUtils.serialize(new ActionException(MErrorEnum.LOGIN_FIRST), 1));
			response.sendRedirect(basePath+"/" + ConstantResources.PAGEM_OUTPUT);
		}
		return;
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}