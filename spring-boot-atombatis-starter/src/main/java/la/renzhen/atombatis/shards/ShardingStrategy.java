package la.renzhen.atombatis.shards;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\21 0021 11:11
 */
public interface ShardingStrategy<T> {
    /**
     * 获取分区规则点
     *
     * @param t
     * @return
     */
    String router(T t);

    /**
     * 获取所有分区
     *
     * @return
     */
    List<String> routers();

    default long toLong(Object partitionField) {
        if (partitionField instanceof Long) {
            return ((Long) partitionField).longValue();
        } else if (partitionField instanceof Integer) {
            return ((Integer) partitionField).intValue();
        } else {
            return Objects.hashCode(partitionField);
        }
    }

    default void init(){}
}