package com.test.day;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @Autowired
    private TestService testService;

    //注入testService
/*    public void setTestService(TestService testService) {
        this.testService = testService;
    }*/

/*    public TestController(TestService testService){
        this.testService = testService;
    }*/

    public void test() {
        testService.test();
    }

}

