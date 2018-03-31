package la.renzhen.atombatis.test;

import la.renzhen.atombatis.EnableAtomBatisConfiguration;
import la.renzhen.atombatis.datasource.ContextHolder;
import la.renzhen.atombatis.test.entry.AccountBill;
import la.renzhen.atombatis.test.entry.AccountBillExample;
import la.renzhen.atombatis.test.mapper.AccountBillMapper;
import la.renzhen.atombatis.test.server.UserServer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\25 0025 16:13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
@ComponentScan("la.renzhen.atombatis.test")
@EnableAtomBatisConfiguration("la.renzhen.atombatis.test")
public class ApplicationTests {

    @Autowired
    UserServer server;

    @Autowired
    ApplicationContext application;

    @Test
    public void scanNames() {
        for (String name : application.getBeanDefinitionNames()) {
            if (name.length() > 20) {
                log.debug(application.getBean(name).getClass().getSimpleName());
            } else {
                log.debug(String.format("【%20s】  %s", name, application.getBean(name).getClass().getSimpleName()));
            }
        }
    }


    @Test
    @SneakyThrows
    public void testAtomBatisMapper() {
        Map<String, AccountBillMapper> mappers = application.getBeansOfType(AccountBillMapper.class);
        for (Map.Entry<String, AccountBillMapper> entry : mappers.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println("\t\t" + entry.getValue());
        }
    }

    @Test
    public void testMapper() {
        AccountBillMapper mapper = application.getBean("accountBillMapper", AccountBillMapper.class);
        {
            ContextHolder.setCustomerType("db1");
            AccountBillExample example = new AccountBillExample();
            example.setTableSubfix("00");
            List<AccountBill> bills = mapper.selectByExample(example);
            System.out.println(bills);
            ContextHolder.clearCustomerType();
        }
        {
            ContextHolder.setCustomerType("db2");
            AccountBillExample example = new AccountBillExample();
            example.setTableSubfix("00");
            List<AccountBill> bills = mapper.selectByExample(example);
            System.out.println(bills);
            ContextHolder.clearCustomerType();
        }
    }

    @Test
    public void testServer() {
        AccountBillExample example = new AccountBillExample();
        example.setOffset(0);
        example.setLimit(10);
        List<AccountBill> billList = server.getAccountBill(example);
        billList.forEach(System.out::println);
    }


    @Test
    public void testInsert() {
        AccountBill accountBill = new AccountBill();
        accountBill.setId(1002);
        accountBill.setUserId(80);
        accountBill.setCreateTime(new Date());
        server.saveBill(accountBill);
    }

    @Test
    public void testGet(){
        AccountBill bill = server.getAccount(1001,80);
        System.out.println(bill);
    }

    @Test
    public void testAll(){
        server.testAll();
    }
}