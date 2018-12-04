package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PracticaAjax")
public class PracticaAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PracticaAjax() {
        super();
    }
    
    protected void averiguaProvincias(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	boolean esAjax;
    	esAjax="XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")); // Cabecera X-Requested-With
    	
    	if(esAjax) {
    		String comunidad = request.getParameter("comunidad");
    		
    		String fichero = "C:\\Users\\manu\\eclipse-workspace\\PracticaAjax\\WebContent\\ficheros\\comunidades_provincias.txt"; 
    		String lineaFichero;
    		    		
    		FileReader fr = new FileReader(fichero);
    		BufferedReader br = new BufferedReader(fr);
    		
    		while((lineaFichero = br.readLine()) != null) {
    			String[] provincias = lineaFichero.split(";");
    			if(provincias[0].equalsIgnoreCase(comunidad)) {
    				out.println(lineaFichero);
    			}
    		}
    		br.close();
    		
    	} else {
    		out.println("Ejecuta esta aplicación vía Ajax");
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		averiguaProvincias(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		averiguaProvincias(request, response);
	}
}
