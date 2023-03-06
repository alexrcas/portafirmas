package net.avantic.repositorio;

import net.avantic.repositorio.dto.Credenciales;
import net.avantic.repositorio.dto.TokenDto;
import net.avantic.repositorio.dto.TokenRequest;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;


public class RequestFilterProvider implements ClientRequestFilter {

    @Inject
    @RestClient
    TokenWebService tokenWebService;

    @Override
    public void filter(ClientRequestContext clientRequestContext) throws IOException {
        clientRequestContext.getHeaders().putSingle("Authorization", "Bearer " + getToken());
    }

    private String getToken() {
        Credenciales credenciales = new Credenciales("abc123&", "SUSTRATO", "expedientes", "avantic-remoto", "admin");
        TokenRequest tokenRequest = new TokenRequest(credenciales);
        TokenDto tokenDto = tokenWebService.token(tokenRequest);
        return tokenDto.getToken();
    }
}
