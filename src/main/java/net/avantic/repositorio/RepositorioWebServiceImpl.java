package net.avantic.repositorio;

import net.avantic.model.entity.DocumentoFirmado;
import net.avantic.repositorio.dto.Credenciales;
import net.avantic.repositorio.dto.DocumentoRepositorioDto;
import net.avantic.repositorio.dto.TokenDto;
import net.avantic.repositorio.dto.TokenRequest;
import net.avantic.type.UuidDocumento;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.InputStream;
import java.util.List;


@ApplicationScoped
@Path("/repositorio")
public class RepositorioWebServiceImpl {

    @Inject
    @RestClient
    RepositorioWebService repositorioWebService;

    public String ping() {
        return repositorioWebService.ping();
    }

    DocumentoRepositorioDto findDocumentoByUuid(UuidDocumento uuidDocumento) {
        return repositorioWebService.findDocumentoByUuid(uuidDocumento.getUuid().toString());
    }

    InputStream descargarContenidoDocumentoByUuid(UuidDocumento uuidDocumento) {
        return repositorioWebService.descargarContenidoDocumentoByUuid(uuidDocumento.getUuid().toString());
    }


    public void enviarFirmas(List<DocumentoFirmado> documentosFirmados) {
        repositorioWebService.enviarFirmas(documentosFirmados);
    }
}
