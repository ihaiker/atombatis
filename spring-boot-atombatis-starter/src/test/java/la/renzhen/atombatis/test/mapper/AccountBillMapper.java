package la.renzhen.atombatis.test.mapper;

import java.util.List;
import la.renzhen.atombatis.test.entry.AccountBill;
import la.renzhen.atombatis.test.entry.AccountBillExample;
import la.renzhen.db.atombatis.ShardParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountBillMapper {
    int countByExample(AccountBillExample example);

    int deleteByExample(AccountBillExample example);

    int insert(AccountBill record);

    int insertSelective(AccountBill record);

    List<AccountBill> selectByExample(AccountBillExample example);

    int updateByExampleSelective(@Param("record") AccountBill record, @Param("example") AccountBillExample example);

    int updateByExample(@Param("record") AccountBill record, @Param("example") AccountBillExample example);

    int updateByPrimaryKeySelective(AccountBill record);

    int updateByPrimaryKey(AccountBill record);

    @Select("select * from account_bill_${bill.tableSubfix} where id = ${bill.id}")
    @ResultMap("BaseResultMap")
    AccountBill getById(@Param("bill") AccountBill id);
}