package net.avantic.story.firmardocumento;

import net.avantic.model.dao.TareaFirmaDao;
import net.avantic.model.entity.Tarea;
import net.avantic.model.entity.TareaFirma;
import net.avantic.service.RepositorioService;
import net.avantic.service.TareaService;
import net.avantic.type.UuidDocumento;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.InputStream;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class FirmarDocumentoFacadeImpl implements FirmarDocumentoFacade {

    @Inject
    TareaService tareaService;

    @Inject
    RepositorioService repositorioService;

    @Inject
    TareaFirmaDao tareaFirmaDao;

    @Override
    public void execute(FirmarDocumentoCommand command) {
        TareaFirma tareaFirma = tareaFirmaDao.findById(command.getIdTarea());
        tareaService.firmarTarea(tareaFirma, command.getFirma());
    }

    @Override
    public String getContenidoDocumentoBase64(Long idTarea) {
        try {
            TareaFirma tareaFirma = tareaFirmaDao.findById(idTarea);
            String uri = tareaFirma.getDocumentoRepositorio().getUriDocumento();
            UuidDocumento uuid = new UuidDocumento(UUID.fromString(uri));
            InputStream inputStream = repositorioService.descargarContenidoDocumentoByUuid(uuid);
            byte[] bytes = inputStream.readAllBytes();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
