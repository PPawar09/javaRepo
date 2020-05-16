package com.pp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServ
 */
public class DemoServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DemoServ() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 request.setAttribute("feature1", MyFeatures.FEATURE_ONE.isActive());
		 
		 request.setAttribute("feature2", MyFeatures.FEATURE_TWO.isActive());
		
		if( MyFeatures.FEATURE_ONE.isActive() ) {
			System.out.println("********FEATURE_ONE is Active********");
		}else{
			System.out.println("********FEATURE_ONE is Not Active********");
		}

		if( MyFeatures.FEATURE_TWO.isActive() ) {
			System.out.println("********FEATURE_TWO is Active********");
		}else{
			System.out.println("********FEATURE_TWO is Not Active********");
		}
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);		
	}
}
