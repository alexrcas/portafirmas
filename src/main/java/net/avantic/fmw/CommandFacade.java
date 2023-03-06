package net.avantic.fmw;

import net.avantic.fmw.command.Command;

public interface CommandFacade<T extends Command> {

    void execute(T command);
}
