package net.avantic.fmw;

import net.avantic.fmw.command.Command;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public interface ModalController<T extends Command> {

    String getModal(@Context UriInfo uriInfo);

    Response postModal(T command);
}
