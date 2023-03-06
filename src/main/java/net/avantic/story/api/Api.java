package net.avantic.story.api;

import net.avantic.type.request.DocumentoRepositorioCircuitoRequest;
import net.avantic.type.request.DocumentoRepositorioRequest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class Api {

    @Inject
    ApiService apiService;

    @POST
    @Path("/registro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String registrarDocumento(DocumentoRepositorioRequest request) {
        apiService.registrarDocumento(request);
        return "ok";
    }

    @POST
    @Path("/circuito")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String registrarDocumento(DocumentoRepositorioCircuitoRequest request) {
        apiService.enviarACircuito(request);
        return "ok";
    }

}
