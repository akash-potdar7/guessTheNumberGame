package com.akash.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LeaderBoardServlet
 */
public class LeaderBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaderBoardServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the context and get the points map and display!
		PrintWriter pw = response.getWriter();
		ServletContext sc = getServletContext();
		if(sc.getAttribute("pointsMap")!=null) {
			Map<String,Integer> pointsMap = (Map<String, Integer>) sc.getAttribute("pointsMap");
			pw.write("<html><body><h1>Points</h1>"+ pointsMap+ "</body></html>");
		}
		else {
			pw.write("<html><body><h1>No points to be shown yet!</h1></body></html>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
