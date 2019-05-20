package petition;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.SortDirection;

@SuppressWarnings("serial")
public class HelloAppEngine extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

            // Demande tous les 5 derniers petitions triés par date décroissante
            Query q = new Query("petition").addSort("date", SortDirection.DESCENDING);
            List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withLimit(100));

            req.setAttribute("petitions", results);
            this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
            
         
           
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {	
        try {
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            
            if (req.getParameter("btn_petition") != null) {

            // Stocke la pétition créée
            Entity petition = new Entity("petition");

            petition.setProperty("titre", req.getParameter("titre"));
            petition.setProperty("description", req.getParameter("description"));
            petition.setProperty("Votes_Necessaires", req.getParameter("votes_necessaire"));
            petition.setProperty("vote_soumis", req.getParameter("vote_soumis"));
            petition.setProperty("date", new Date());

            datastore.put(petition);

            resp.sendRedirect("/");
            }
            else {
            	Entity petitionSigne = new Entity("petitionSigne");

                petitionSigne.setProperty("petitionSigne", req.getParameter("petitionSigne"));
                petitionSigne.setProperty("vote_soumis", req.getParameter("vote_soumis"));
                petitionSigne.setProperty("date", new Date());

                datastore.put(petitionSigne);

                resp.sendRedirect("/");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}