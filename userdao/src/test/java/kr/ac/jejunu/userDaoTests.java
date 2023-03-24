package kr.ac.jejunu;

import kr.ac.jejunu.user.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class userDaoTests {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "Jay";
        String password = "1234";
        //UserDao userDao = new UserDao();
        ConnectionMaker connectionMaker = new JejuConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

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
        ConnectionMaker connectionMaker=new JejuConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(),  is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

    //한라대
    @Test
    public void getForHalla() throws SQLException, ClassNotFoundException {
    // UserDao userDao = new HallaConnectionMaker();
        ConnectionMaker connectionMaker = new HallaConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

        //DB에 작성된 내용으로 지정
        Long id = 1l;
        String name  = "Jay";
        String password = "1234";

        User user = userDao.findById(id);
        assertThat(user.getId(),is(id));
        assertThat(user.getName(),is(name));
        assertThat(user.getPassword(),is(password));
    }

    @Test
    public void insertForHalla() throws SQLException, ClassNotFoundException {
        String name = "조현지";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

    //  UserDao userDao = new HallaConnectionMaker();
        ConnectionMaker connectionMaker = new HallaConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(),  is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

}