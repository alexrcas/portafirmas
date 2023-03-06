package net.avantic.repositorio;


import jakarta.ws.rs.core.MediaType;
import net.avantic.model.entity.DocumentoFirmado;
import net.avantic.repositorio.dto.DocumentoRepositorioDto;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import java.io.InputStream;
import java.util.List;


@RegisterRestClient(configKey = "repositorio-client")
@RegisterProvider(RequestFilterProvider.class)
@Path("/repositorio")
public interface RepositorioWebService {


    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    String ping();

    @GET
    @Path("/documento/v1/expedientes/{uuid}/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    DocumentoRepositorioDto findDocumentoByUuid(@PathParam("uuid") String uuid);

    @GET
    @Path("/documento/v1/expedientes/{uuid}/contenido/byuuid")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    InputStream descargarContenidoDocumentoByUuid(@PathParam("uuid") String uuid);

    void enviarFirmas(List<DocumentoFirmado> documentosFirmados);
}
