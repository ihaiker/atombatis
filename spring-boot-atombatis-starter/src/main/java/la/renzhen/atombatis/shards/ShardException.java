package la.renzhen.atombatis.shards;

import java.lang.reflect.Method;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\22 0022 15:33
 */
public class ShardException extends Exception {
    Method method;

    public ShardException(Method method) {
        super(method.getDeclaringClass().getName() + "@" + method.getName());
        this.method = method;
    }

    @Override
    public String getMessage() {
        return "Call method " + method.getDeclaringClass().getName() + "@" + method.getName() + " expect a value but specify multiple partitions!";
    }
}
