package net.avantic.repositorio;


import jakarta.ws.rs.core.MediaType;
import net.avantic.repositorio.dto.Credenciales;
import net.avantic.repositorio.dto.TokenDto;
import net.avantic.repositorio.dto.TokenRequest;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.ws.rs.*;


@RegisterRestClient(configKey = "repositorio-token")
@Path("/token")
public interface TokenWebService {

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    TokenDto token(@MultipartForm TokenRequest tokenRequest);

}
