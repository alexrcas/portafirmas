package net.avantic.story.validardocumento;

import net.avantic.model.dao.TareaFirmaDao;
import net.avantic.model.dao.TareaValidacionDao;
import net.avantic.model.entity.Tarea;
import net.avantic.model.entity.TareaFirma;
import net.avantic.model.entity.TareaValidacion;
import net.avantic.service.RepositorioService;
import net.avantic.service.TareaService;
import net.avantic.type.UuidDocumento;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;

@ApplicationScoped
public class ValidarDocumentoFacadeImpl implements ValidarDocumentoFacade {

    @Inject
    TareaService tareaService;

    @Inject
    RepositorioService repositorioService;

    @Inject
    TareaValidacionDao tareaValidacionDao;

    @Override
    public void execute(ValidarDocumentoCommand command) {
        TareaValidacion tareaValidacion = tareaValidacionDao.findById(command.getIdTarea());
        tareaService.validarTarea(tareaValidacion);
    }

    @Override
    public String getContenidoDocumentoBase64(Long idTarea) {
        try {
            Tarea tarea = tareaService.getTarea(idTarea);
            String uri = tarea.getDocumentoRepositorio().getUriDocumento();
            UuidDocumento uuid = new UuidDocumento(UUID.fromString(uri));
            InputStream inputStream = repositorioService.descargarContenidoDocumentoByUuid(uuid);
            byte[] bytes = inputStream.readAllBytes();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
