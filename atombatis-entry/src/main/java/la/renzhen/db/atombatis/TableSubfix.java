package la.renzhen.db.atombatis;

/**
 * table subfix 实现接口<p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\20 0020 15:25
 */
public interface TableSubfix {
    /**
     * 获取表后缀
     *
     * @return
     */
    String getTableSubfix();

    void setTableSubfix(String name);
}
