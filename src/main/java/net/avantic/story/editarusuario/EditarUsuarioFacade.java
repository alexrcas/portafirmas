package net.avantic.story.editarusuario;

import net.avantic.fmw.CommandFacade;

public interface EditarUsuarioFacade extends CommandFacade<EditarUsuarioCommand> {

    void populateCommand(EditarUsuarioCommand command);
}
