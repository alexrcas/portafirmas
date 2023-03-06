package net.avantic.type;

import java.util.UUID;

public class UuidDocumento {

    private UUID uuid;

    public UuidDocumento(UUID uuid) {
        this.uuid = uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
