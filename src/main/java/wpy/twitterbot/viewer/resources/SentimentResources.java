package wpy.twitterbot.viewer.resources;

import java.io.IOException;
import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import wpy.twitterbot.viewer.service.SentimentService;

/**
 * @author wpengyu
 *
 */
@Path("/sentiment/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SentimentResources {
    private SentimentService service = new SentimentService();
    private final Log log = LogFactory.getLog(getClass());

    @GET
    @Path("/{symbol}")
    public String get(@PathParam("symbol") String symbol, @QueryParam("date") String date)
            throws IOException, ParseException {
        if (date != null) {
            return service.get(symbol, date);
        } else {
            return service.getStats(symbol);
        }
    }
}
