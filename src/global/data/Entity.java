package global.data;

import java.util.UUID;

public class Entity {

    private UUID uuid;

    public Entity() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return this.uuid;
    }

}
