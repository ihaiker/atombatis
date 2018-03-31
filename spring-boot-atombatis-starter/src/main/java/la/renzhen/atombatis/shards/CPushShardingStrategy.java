package la.renzhen.atombatis.shards;

import la.renzhen.atombatis.spring.DataSourcesAware;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 默认使用第一个<p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\21 0021 21:10
 */
@Data
public class CPushShardingStrategy implements ShardingStrategy, DataSourcesAware {

    //数据存储分区数量
    int shardSize = 8;

    //真实节点名称
    List<String> shards;

    //虚拟节点分配情况
    TreeMap<Long, String> nodes = null;

    //设置虚拟节点数目
    int virtualNum = 128;

    @Override
    @SneakyThrows
    public String router(Object partitionField) {
        if (shards.size() == 1) {
            return shards.get(0);
        } else {
            long out = toLong(partitionField);
            long hashCode = out % virtualNum;
            long key = 0;

            SortedMap<Long, String> tail = nodes.tailMap(hashCode);
            if (tail.isEmpty()) {
                key = nodes.firstKey();
            } else {
                key = tail.firstKey();
            }
            return nodes.get(key);
        }
    }

    @Override
    public List<String> routers() {
        return shards;
    }

    public void setShardSize(int shardSize) {
        this.shardSize = shardSize;
        this.shards = null;
    }

    public void setShards(List<String> shards) {
        this.shards = shards;
    }


    public void init() {
        if (shards != null) {
            shardSize = shards.size();
        } else {
            shards = new ArrayList<>();
            for (int i = 0; i < shardSize; i++) {
                shards.add(String.format("%02d", i));
            }
        }
        int zone = virtualNum / shardSize;
        nodes = new TreeMap<Long, String>();
        for (int i = 0; i < shards.size(); i++) {
            String out = shards.get(i);
            for (int j = 0; j < zone; j++) {
                long n = (i * zone) + j;
                nodes.put(n, out);
            }
        }
    }
}