package la.renzhen.atombatis.shards;

import la.renzhen.atombatis.spring.DataSourcesAware;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\23 0023 15:14
 */
@Data
public class MoldShardingStrategy implements ShardingStrategy, DataSourcesAware {
    private int mold = 2;

    private List<String> shards;

    @Override
    public String router(Object o) {
        return shards.get((int) (toLong(o) % mold));
    }

    @Override
    public List<String> routers() {
        return shards;
    }

    public void setShards(List<String> shards) {
        this.shards = shards;
        this.mold = shards.size();
    }

    public void setMold(int mold) {
        this.mold = mold;
        this.shards = null;
    }

    @Override
    public void init() {
        if (shards == null) {
            shards = new ArrayList<>();
            for (int i = 0; i < mold; i++) {
                shards.add(String.format("%02d", toLong(i) % mold));
            }
        }
    }
}
