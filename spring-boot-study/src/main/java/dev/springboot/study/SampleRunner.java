package dev.springboot.study;

import dev.springboot.study.neo4j.Account;
import dev.springboot.study.neo4j.AccountRepository;
import dev.springboot.study.neo4j.Role;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setEmail("henry@mail.com");
        account.setUsername("henry");

        Role role = new Role();
        role.setName("admin");

        account.getRoles().add(role);

        accountRepository.save(account);

//        Session session = sessionFactory.openSession();
//        session.save(account);
//        sessionFactory.close();
    }
}
