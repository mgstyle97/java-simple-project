package global.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiPredicate;

public class Repository<T extends Entity> {

    protected final Map<UUID, T> collection;
    private final Map<String, UUID> uuidCollection;

    public Repository() {
        collection = new HashMap<>();
        uuidCollection = new HashMap<>();
    }

    public void save(T entity) {
        this.collection.put(entity.getUuid(), entity);
    }

    public Optional<T> findById(final String id, final BiPredicate<String, T> predicate) {

        return collection.values().stream()
                .filter(entity -> predicate.test(id, entity))
                .findFirst();
    }

    public void update(T entity) {

    }

    public void delete(final T entity) {
        this.collection.remove(entity.getUuid(), entity);
    }

}
