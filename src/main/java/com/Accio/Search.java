package com.Accio;

import com.Accio.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        //get parameter called "keyword from request
     String keyword=request.getParameter("keyword");
     System.out.println(keyword);
     try {
         //etablished get to the database
         Connection connection = DatabaseConnection.getConnection();
         //save the keyword and link associated into history table
         PreparedStatement preparedStatement=connection.prepareStatement("Insert into history values(?,?)");
         preparedStatement.setString(1,keyword);
         preparedStatement.setString(2,"http://localhost:8080/SearchEngineJava/Search?keyword="+keyword);
         preparedStatement.executeUpdate();


         //showing the result as per count of keyword
         ResultSet resultSet = connection.createStatement().executeQuery("select pagetitle,pagelink, (length(lower(pagetext))-length(replace(lower(pagetext),'" +keyword+"','')))/length('" + keyword+"') as countoccurence from pages order by countoccurence desc limit 30;");
         //putting all result into arraylist
         ArrayList<SearchResult> results = new ArrayList<>();
         //itarating the arraylist to get the result
         while (resultSet.next()) {
             SearchResult searchResult=new SearchResult();
             //get the page title & page link
             searchResult.setPageTitle(resultSet.getString("pageTitle"));
             searchResult.setPageLink(resultSet.getString("pageLink"));
             results.add(searchResult);

         }
        // display result in consol
         for(SearchResult searchResult :results){
             System.out.println(searchResult.getPageTitle()+" "+searchResult.getPageLink()+"\n");

         }
         //set attributes of request with result arraylist
         request.setAttribute("results",results);
         //forward request into front end search.jsp
         request.getRequestDispatcher("/search.jsp").forward(request,response);
         response.setContentType("text/html");
         PrintWriter out=response.getWriter();
     }
     catch(SQLException | ServletException | IOException sqlException){
         sqlException.printStackTrace();
     }
    }
}
