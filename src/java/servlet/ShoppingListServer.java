/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Clock;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 715583
 */
public class ShoppingListServer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        System.out.println("action:"+action);
        ArrayList<String> itemlist = (ArrayList<String>) session.getAttribute("itemlist");        
        if(itemlist==null) itemlist=new ArrayList<String>();
        
       
        if(action.equals("add"))
        {
            //TODO get parameter from jsp file, add to arraylist and renew the session
            String username = (String) session.getAttribute("username");
            String item = req.getParameter("item");

            itemlist.add(item);
            session.setAttribute("itemlist", itemlist);
            
//            int listsize = itemlist.size();
//            req.setAttribute("listsize", listsize);
//            req.setAttribute("username", username);
//            req.setAttribute("itemlist", itemlist);
//            
//            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(req, resp);
             resp.sendRedirect("shoppinglist");
            return;
        }
        if(action.equals("delete"))
        {
            //TODO get the remove item and delete from arraylist, renew session, 
            int index =Integer.parseInt(req.getParameter("itemname"));
            itemlist.remove(index);
            session.setAttribute("itemlist", itemlist);
            
//             int listsize = itemlist.size();
//            req.setAttribute("listsize", listsize);
//            
//            req.setAttribute("username", username);
//            req.setAttribute("itemlist", itemlist);
//            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(req, resp);
            resp.sendRedirect("shoppinglist");
            return;
            
        }
        if(action.equals("register"))
        {
            //TODO add user name to session , forward page             
            String username = req.getParameter("username");
            session.setAttribute("username", username);
           // req.setAttribute("username", username);
          //  getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(req, resp);
          resp.sendRedirect("shoppinglist");
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        String url ="/WEB-INF/register.jsp";
        ArrayList<String> itemlist;
        String action=req.getParameter("action");
        
        if(action==null)
        {
            if(username==null)
            {
                System.out.println("are you nin here ?");
            }
            else
            {
                url="/WEB-INF/shoppingList.jsp";
                itemlist = (ArrayList<String>)session.getAttribute("itemlist");
                if(itemlist!=null)
                {
                    int listsize = itemlist.size();
                    req.setAttribute("listsize", listsize);
                   // System.out.println("listsize:"+ listsize);
                    
                    req.setAttribute("itemlist", itemlist);
                    System.out.println("itemlist" +itemlist);
                }
                else
                {
                    req.setAttribute("listsize", 0);           
                }
                req.setAttribute("username", username);
            }
        }
        
        else if(action.equals("logout"))
        { 
            //TODO back to register page, delete all session 
           
            session.removeAttribute("username");
            session.removeAttribute("itemlist");
            System.out.println("fdjskflaejfklasf");
//            getServletContext().getRequestDispatcher(url).forward(req, resp);
//            
//            return;
        }
        
      
        
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
