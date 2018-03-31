package la.renzhen.atombatis.test.server;

import la.renzhen.atombatis.test.entry.AccountBill;
import la.renzhen.atombatis.test.entry.AccountBillExample;
import la.renzhen.atombatis.test.mapper.AccountBillMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\25 0025 16:18
 */
@Slf4j
@Service
public class UserServer {

    @Autowired
    AccountBillMapper accountBillMapper;

    public List<AccountBill> getAccountBill(AccountBillExample example) {
        return accountBillMapper.selectByExample(example);
    }

    public int saveBill(AccountBill accountBill) {
        return accountBillMapper.insert(accountBill);
    }

    public AccountBill getAccount(int id, int userId) {
        AccountBill accountBill = new AccountBill();
        accountBill.setId(id);
        accountBill.setUserId(userId);
        return accountBillMapper.getById(accountBill);
    }

    @Transactional
    public void testAll(){
        {
            AccountBill accountBill = new AccountBill();
            accountBill.setId(1004);
            accountBill.setUserId(2);
            accountBillMapper.insert(accountBill);
        }
        {
            AccountBill accountBill = new AccountBill();
            accountBill.setId(1003);
            accountBill.setUserId(123);
            accountBillMapper.insert(accountBill);
        }

    }
}
