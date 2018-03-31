package la.renzhen.atombatis.datasource;

import la.renzhen.atombatis.Routing;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class RoutingDataSource extends AbstractRoutingDataSource implements Routing<DataSource> {

    private Map<String, DataSource> targets;

    @Override
    protected Object determineCurrentLookupKey() {
        return ContextHolder.getCustomerType();
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        Map<String, DataSource> targets = new HashMap<>();
        for (Map.Entry<Object, Object> entry : targetDataSources.entrySet()) {
            targets.put(String.valueOf(entry.getKey()), (DataSource) entry.getValue());
        }
        this.setTarget(targets);
    }

    @Override
    public void setTarget(Map<String, DataSource> target) {
        this.targets = target;
    }

    @Override
    public Map<String, DataSource> getTarget() {
        return targets;
    }

    @Override
    public DataSource getTarget(String name) {
        return (DataSource) targets.get(name);
    }
}