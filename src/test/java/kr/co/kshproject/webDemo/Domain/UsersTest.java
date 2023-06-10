package kr.co.kshproject.webDemo.Domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class UsersTest {
    @Test
    public void creation(){
        Users user= Users.builder()
                .email("tester@example.com")
                .name("tester")
                .level(100L)
                .build();
        assertThat(user.getName(),is("tester"));
        //  assertThat(user.isAdmin(),is(true));
        //  assertThat(user.isActive(),is(true));
        //  user.deactive();
        //  assertThat(user.isActive(),is(false));
    }
}