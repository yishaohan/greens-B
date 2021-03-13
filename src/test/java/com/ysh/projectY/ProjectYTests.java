package com.ysh.projectY;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ProjectYTests {

    //    @Test
    @Transactional(readOnly = true)
    void contextLoads() {
    }
}
