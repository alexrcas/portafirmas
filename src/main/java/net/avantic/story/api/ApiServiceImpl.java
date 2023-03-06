package net.avantic.story.api;

import net.avantic.model.entity.*;
import net.avantic.repositorio.RepositorioGatway;
import net.avantic.repositorio.dto.DocumentoRepositorioDto;
import net.avantic.service.CircuitoService;
import net.avantic.service.TareaService;
import net.avantic.type.UuidDocumento;
import net.avantic.type.request.DocumentoRepositorioCircuitoRequest;
import net.avantic.type.request.DocumentoRepositorioRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ApiServiceImpl implements ApiService {

    @Inject
    RepositorioGatway repositorioGatway;

    @Inject
    TareaService tareaService;

    @Inject
    CircuitoService circuitoService;

    @Override
    @Transactional
    public String registrarDocumento(DocumentoRepositorioRequest request) {

        UuidDocumento uuidDocumento = new UuidDocumento(UUID.fromString(request.getUriDocumento()));
        Optional<DocumentoRepositorioDto> documentoRepositorioDtoOpt = repositorioGatway.findDocumentoByUuid(uuidDocumento);
        if (documentoRepositorioDtoOpt.isEmpty()) {
            String mensaje = String.format("No se encuentra el documento con uri[%s]", request.getUriDocumento());
            throw new RuntimeException(mensaje);
        }

        if (tareaService.isDocumentoPendienteFirma(uuidDocumento)) {
            String mensaje = String.format("El documento con uri [%s] ya está pendiente de firma", uuidDocumento.getUuid().toString());
            throw new RuntimeException(mensaje);
        }

        DocumentoRepositorioDto documentoRepositorioDto = documentoRepositorioDtoOpt.get();

        DocumentoRepositorio documentoRepositorio = new DocumentoRepositorio(request.getUriDocumento(), documentoRepositorioDto.getNombreOriginal(), documentoRepositorioDto.getDescripcion());
        documentoRepositorio.persist();

        Usuario.findByDni(request.getDniUsuario())
                .ifPresentOrElse(usuario -> {
                            usuario.persist();
                            TareaFirma tarea = new TareaFirma(documentoRepositorio, usuario);
                            tarea.persist();
                        },
                        () -> {
                            String mensaje = String.format("No se encuentra el usuario con DNI[%s]", request.getDniUsuario());
                            throw new RuntimeException(mensaje);
                        }
                );


        return "ok";
    }

    @Override
    @Transactional
    public String enviarACircuito(DocumentoRepositorioCircuitoRequest request) {
        Optional<Circuito> circuitoOpt = circuitoService.findCircuitoById(Long.valueOf(request.getIdCircuito()));
        if (circuitoOpt.isEmpty()) {
            String mensaje = String.format("No existe el circuito con id [%s]", request.getIdCircuito());
            throw new RuntimeException(mensaje);
        }

        UuidDocumento uuidDocumento = new UuidDocumento(UUID.fromString(request.getUriDocumento()));

        Optional<DocumentoRepositorioDto> documentoRepositorioDtoOpt = repositorioGatway.findDocumentoByUuid(uuidDocumento);
        if (documentoRepositorioDtoOpt.isEmpty()) {
            String mensaje = String.format("No se encuentra el documento con uri[%s]", request.getUriDocumento());
            throw new RuntimeException(mensaje);
        }

        if (tareaService.isDocumentoPendienteFirma(uuidDocumento)) {
            String mensaje = String.format("El documento con uri [%s] ya está pendiente de firma", uuidDocumento.getUuid().toString());
            throw new RuntimeException(mensaje);
        }

        DocumentoRepositorioDto documentoRepositorioDto = documentoRepositorioDtoOpt.get();
        DocumentoRepositorio documentoRepositorio = new DocumentoRepositorio(request.getUriDocumento(), documentoRepositorioDto.getNombreOriginal(), documentoRepositorioDto.getDescripcion());
        documentoRepositorio.persist();
        Circuito circuito = circuitoOpt.get();
        circuitoService.enviarDocumento(circuito, documentoRepositorio);

        return "ok";
    }
}
