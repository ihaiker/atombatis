package la.renzhen.atombatis.batis;

import la.renzhen.atombatis.Routing;
import lombok.Builder;
import lombok.Singular;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Map;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\31 0031 16:12
 */
public class RoutingSqlSessionFactory implements Routing<SqlSessionFactory> {

    public RoutingSqlSessionFactory(Map<String, SqlSessionFactory> targetFactortis) {
        this.targetFactortis = targetFactortis;
    }

    private Map<String, SqlSessionFactory> targetFactortis;

    @Override
    public void setTarget(Map<String, SqlSessionFactory> target) {
        this.targetFactortis = target;
    }

    @Override
    public Map<String, SqlSessionFactory> getTarget() {
        return this.targetFactortis;
    }

    @Override
    public SqlSessionFactory getTarget(String name) {
        return targetFactortis.get(name);
    }
}
