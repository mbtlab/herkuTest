package com.vanke.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import com.datacenter.common.SpringContextHolder;
import com.datacenter.handler.AbsCommandHandler;

/**
 * 系统统一接口处理servlet
 * Date: 2017-04-22
 * @author Leo
 *
 */
public class DataServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static Logger log = Logger.getLogger(DataServlet.class);
	
	/**
	 * Constructor of the object.
	 */
	public DataServlet() {		
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("=================接口获取请求开始=======================");
		AbsCommandHandler cmdHandler = (AbsCommandHandler) SpringContextHolder.getBean("herokuHandler");
		String strResponse = cmdHandler.handler(request);
		response.setContentType("application/json;charset=utf-8");
		response.setContentLength(strResponse.getBytes("UTF-8").length);
		log.info("================响应报文=======================" + strResponse);
		PrintWriter out = response.getWriter();
		out.print(strResponse);
		out.flush();
		out.close();
		log.info("=================接口请求处理完毕======================");
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	

}
