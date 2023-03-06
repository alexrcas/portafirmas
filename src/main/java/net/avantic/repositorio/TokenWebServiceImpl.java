package net.avantic.repositorio;

import net.avantic.repositorio.dto.Credenciales;
import net.avantic.repositorio.dto.TokenDto;
import net.avantic.repositorio.dto.TokenRequest;
import net.avantic.type.UuidDocumento;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;


@ApplicationScoped
@Path("/repositorio")
public class TokenWebServiceImpl {

    @Inject
    @RestClient
    TokenWebService tokenWebService;

    public TokenDto token(@MultipartForm TokenRequest tokenRequest) {
        return tokenWebService.token(tokenRequest);
    }

}
