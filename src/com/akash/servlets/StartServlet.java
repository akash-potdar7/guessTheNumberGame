package com.akash.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StartServlet
 */
public class StartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
    	super.init();
    	System.out.println("in init() of S");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doGet() of S");
		String player = request.getParameter("name");
		System.out.println("name input = "+player);
		PrintWriter pw = response.getWriter();
		if(player==null || player.trim().equalsIgnoreCase("")) {
			pw.write("<html><body><h1>Error</h1><b>Name input is invalid!</b></body></html>");
		}
		else {
			int val = (int)(10 * Math.random());
			System.out.println("generated val = "+val);
			HttpSession session = request.getSession(true);
			session.setAttribute("user", player);
			session.setAttribute("genVal", val);
			session.setAttribute("tries", 3);

			RequestDispatcher rd = request.getRequestDispatcher("Guess.html");
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
