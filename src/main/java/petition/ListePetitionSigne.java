package petition;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.SortDirection;

@SuppressWarnings("serial")
public class ListePetitionSigne extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

            // Demande tous les 5 derniers petitions triés par date décroissante
            Query q = new Query("petitionSigne").addSort("date", SortDirection.DESCENDING);
            List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withLimit(100));

            req.setAttribute("petitionSigne", results);
            this.getServletContext().getRequestDispatcher("/WEB-INF/indextrois.jsp").forward(req, resp);
            
         
           
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {	
       
    }

}