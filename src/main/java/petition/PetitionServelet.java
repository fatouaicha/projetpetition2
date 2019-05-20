package petition;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.SortDirection;

@SuppressWarnings("serial")
public class PetitionServelet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

            // Demande tous les 5 derniers petitions triés par date décroissante
            Query q = new Query("petition").addSort("date", SortDirection.DESCENDING);
            List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withLimit(100));

            req.setAttribute("petitions", results);
            this.getServletContext().getRequestDispatcher("/WEB-INF/indexdeux.jsp").forward(req, resp);
            
           
           
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {	
        try {
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

            // Stocke la pétition créée
            Entity petitionSigne = new Entity("petitionSigne");

            petitionSigne.setProperty("petitionSigne", req.getParameter("petitionSigne"));
            petitionSigne.setProperty("vote_soumis", req.getParameter("vote_soumis"));
            petitionSigne.setProperty("date", new Date());

            datastore.put(petitionSigne);

            resp.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}