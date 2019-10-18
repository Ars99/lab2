package com.demo;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.Random;

@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {

    private HashMap<Integer, Set<String>> map;
    private Singleton session;

    public void init(){
        map = new HashMap<Integer, Set<String>>();
        session = Singleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Random random = new Random();
        int num1 = -125 + random.nextInt(348 + 125);;
        int num2 = -125 + random.nextInt(348 + 125);;
        int sum = num1 + num2;
        String hashcode = Integer.toString(Long.toString(sum + System.currentTimeMillis()).hashCode());
        if (map.containsKey(sum)){
            map.get(sum).add(hashcode);
        }
        else{
            Set<String> str = new HashSet<String>();
            str.add(hashcode);
            map.put(sum, str);
        }
        request.setAttribute("num1", num1);
        request.setAttribute("num2", num2);
        request.setAttribute("hashcode", hashcode);
        request.getRequestDispatcher("/count_to_get_in.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int answer = Integer.parseInt(request.getParameter("answer"));
        String hashcode = request.getParameter("hashcode");
        if(map.containsKey(answer)) {
            if (map.get(answer).contains(hashcode)) {
                String sessionId = UUID.randomUUID().toString();
                session.add(sessionId);
                Cookie cookie = new Cookie("sessionId", sessionId);
                response.addCookie(cookie);
                request.getRequestDispatcher("hello_inside.jsp").forward(request, response);
            }
        }

        else{
            response.sendRedirect("http://localhost:8080/lab2_war_exploded/");
        }
    }
}
