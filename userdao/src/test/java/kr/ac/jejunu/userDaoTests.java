package kr.ac.jejunu;

import kr.ac.jejunu.user.*;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class userDaoTests {
    private static UserDao userDao;
    @BeforeAll
    public static void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "Jay";
        String password = "1234";
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String name = "조현지";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(),  is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        User user = insertedUser();

        String updatedName = "updatedaJay";
        user.setName(updatedName);
        String updatedPassword = "2222";
        user.setPassword(updatedPassword);
        userDao.update(user);

        User updatedUser = userDao.findById(user.getId());
        assertThat(updatedUser.getName(), is(updatedName));
        assertThat(updatedUser.getPassword(), is(updatedPassword));

    }

    private static User insertedUser() throws ClassNotFoundException, SQLException {
        String name = "조현지";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        return user;
    }


    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        User user = insertedUser();
        userDao.delete(user.getId());

        User deletedUser = userDao.findById(user.getId());

        assertThat(deletedUser, IsNull.nullValue());

    }

}