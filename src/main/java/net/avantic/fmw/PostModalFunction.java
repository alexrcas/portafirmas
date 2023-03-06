package net.avantic.fmw;


import net.avantic.fmw.command.Command;

public interface PostModalFunction<T extends Command> {
    void fn(T command);
}
