package fans.java.esm.core.context;

import lombok.Data;

import java.util.Objects;

/**
 * 属性key
 *
 * @author SSS
 */
@Data
public class AttrKey<T> {
    private String name;

    public AttrKey(String name) {
        this.name = name;
    }

    public static <T> AttrKey<T> of(String name) {
        return new AttrKey<>(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttrKey<?> that = (AttrKey<?>) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
