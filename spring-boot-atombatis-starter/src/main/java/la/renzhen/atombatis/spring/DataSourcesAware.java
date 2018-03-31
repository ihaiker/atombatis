package la.renzhen.atombatis.spring;

import java.util.List;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\22 0022 20:29
 */
public interface DataSourcesAware {

    /**
     * 设置所有数据分片名称
     *
     * @param shards
     */
    void setShards(List<String> shards);

}