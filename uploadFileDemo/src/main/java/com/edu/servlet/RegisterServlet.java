package com.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.catalina.mbeans.MBeanUtils;

import com.edu.model.Student;

@MultipartConfig
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//			String name = request.getParameter("name");
			Student student = new Student();
			MBeanUtils.
			Part part = request.getPart("image");
			String realPath = request.getServletContext().getRealPath("/image");
			String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();
			if(!Files.exists(Path.of(realPath))) {
				Files.createDirectories(Path.of(realPath));
			}
			part.write(realPath+"/"+filename);
			try(PrintWriter out = response.getWriter()){
				out.println("<h1>Name: "+name+"</h1>");
				out.println("<img src='image/"+filename+ "'width='80' heigh='80' >");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
