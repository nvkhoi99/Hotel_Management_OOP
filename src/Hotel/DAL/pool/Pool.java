package Hotel.DAL.pool;

public interface Pool<T> {
    T get() throws Exception;

    void release(T t);
}
