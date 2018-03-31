package la.renzhen.atombatis;

import java.util.Map;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\31 0031 16:20
 */
public interface Routing<T> {

    void setTarget(Map<String, T> target);

    Map<String,T> getTarget();

    T getTarget(String name);
}
