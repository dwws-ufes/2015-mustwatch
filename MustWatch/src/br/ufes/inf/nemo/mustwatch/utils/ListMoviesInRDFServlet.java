package br.ufes.inf.nemo.mustwatch.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

import br.ufes.inf.nemo.mustwatch.domain.Movie;
import br.ufes.inf.nemo.mustwatch.persistence.MovieDAO;

@WebServlet(urlPatterns = { "/data/movies" })
public class ListMoviesInRDFServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DateFormat df = new SimpleDateFormat(
			"yyyy-MM- dd'T'HH:mm:ss");
	@EJB
	private MovieDAO movieDAO;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/xml");
		List<Movie> movies = movieDAO.retrieveAll();
		Model model = ModelFactory.createDefaultModel();
		String myNS = "http://localhost:8080/MustWatch/data/Movies/";
		String grNS = "http://purl.org/goodrelations/v1#";
		model.setNsPrefix("gr", grNS);
		Property hasReleaseDate = ResourceFactory.createProperty(grNS +
		"hasReleaseDate");
		/*for (Movie movie : movies) {
			model.createResource(myNS + movie.getId())
					.addProperty(RDF.type, grOffering)
					.addProperty(RDFS.label, movie.getName())
					.addProperty(RDFS.comment, movie.getDescription())
					.addLiteral(
							hasReleaseDate,
							ResourceFactory.createTypedLiteral(
									df.format(movie.getReleaseDate()),
									XSDDatatype.XSDdateTime)))
		}
		try (PrintWriter out = resp.getWriter()) {
			model.write(out, "RDF/XML");
		}*/
	}
}

