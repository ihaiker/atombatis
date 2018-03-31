package la.renzhen.db.atombatis;

import java.util.List;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\20 0020 21:50
 */
public interface ShardSelect<T> {
    List<T> getABSlave();
}