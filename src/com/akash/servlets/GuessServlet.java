package com.akash.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GuessServlet
 */
public class GuessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuessServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in doGet() of GS");
		/*
		 * 1.get the guessed num from user and validate 2.get the session if
		 * available 3.get the data stored in session 4.check num of tries > 0
		 * 5.if yes, compare guess val with gen val 6.show message accordingly
		 * 7.if guess is correct, store 5 points against user in context!
		 */

		String gn = request.getParameter("guessNum");
		PrintWriter pw = response.getWriter();
		if (gn == null || gn.isEmpty() || gn.trim().equals("")) {
			pw.write("<html><body><h1>Error</h1><b>Guess!! that's what the game is all about!!!</b></body></html>");
		} else {
			try {
				int guessVal = Integer.parseInt(gn);

				// pull the session data!
				HttpSession session = request.getSession(false);
				if (session == null) {
					pw.write("<html><body><h1>Error</h1><b>Session time out...</b></body></html>");
				} else {
					String name = (String) session.getAttribute("user");
					int genVal = (Integer) session.getAttribute("genVal");
					int tries = (Integer) session.getAttribute("tries");

					if (tries <= 0) {
						pw.write("<html><body><h1>Error</h1><b>You have only 3 tries... Go to <a href='HomePage.html'>HomePage</a> to restart game!</b></body></html>");

						// forget about the user as he has completed the game!
						session.removeAttribute("user");
						session.removeAttribute("tries");
						session.removeAttribute("genVal");
						session.invalidate();
					} else {
						if (guessVal == genVal) {
							pw.write("<html><body><h1>Success</h1><b>Hooray!, correct guess... Go to <a href='HomePage.html'>HomePage</a> to restart game!</b></body></html>");

							// give points to user
							ServletContext ctx = getServletContext();
							if (ctx.getAttribute("pointsMap") != null) {
								@SuppressWarnings("unchecked")
								Map<String, Integer> points = (Map<String, Integer>) ctx.getAttribute("pointsMap");
								// check if user already exists in map
								if (points.get(name) != null) {
									points.put(name, points.get(name) + 5);
								} else {
									points.put(name, 5);
								}
							} else {
								// create new map and store it in context!
								Map<String, Integer> points = new HashMap<String, Integer>();
								points.put(name, 5);

								ctx.setAttribute("pointsMap", points);
							}

							// forget about the user as he has completed the
							// game!
							session.removeAttribute("user");
							session.removeAttribute("tries");
							session.removeAttribute("genVal");
							session.invalidate();
						} else {
							if (guessVal > genVal)
								pw.write("<html><body><h1>Oops</h1><b>Just miss...guess lower!</b></body></html>");
							else
								pw.write("<html><body><h1>Oops</h1><b>Just miss...guess higher!</b></body></html>");

							// reduce the number of tries and put it back to
							// session
							tries--;
							session.setAttribute("tries", tries);
						}
					}
				}
			} catch (NumberFormatException e) {
				pw.write("<html><body><h1>Error</h1><b>Enter integer number!</b></body></html>");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
