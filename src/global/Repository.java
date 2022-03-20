package global;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class Repository<T extends Entity> {

    private final Map<UUID, T> collection;
    private final Map<String, UUID> uuidCollection;

    public Repository() {
        collection = new HashMap<>();
        uuidCollection = new HashMap<>();
    }

    public void save(T entity) {
        this.collection.put(entity.getUuid(), entity);
    }

    public Optional<T> findById(final String id) {

        try {
            final UUID uuid = uuidCollection.get(id);

            return Optional.of(
                    collection.get(uuid)
            );
        } catch (NullPointerException e) {
            System.err.println("잘못된 요청입니다.");
            return Optional.empty();
        }
    }

}
