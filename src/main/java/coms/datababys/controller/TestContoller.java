package coms.datababys.controller;

import coms.datababys.entity.TestEntity;
import coms.datababys.service.TestService;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Lovme on 2017/2/15.
 */
@RestController
@RequestMapping(value="/management/test")
public class TestContoller {
    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping(value = "/")
    public String location() {
        return "北京";
    }

    @ResponseBody
    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request) {
        String com = request.getParameter("id");
        return testService.look(com);
    }

    @ResponseBody
    @RequestMapping(value = "/test1")
    public List<TestEntity> test1() {
        return testService.insert();
    }

    @ResponseBody
    @RequestMapping(value = "/test2")
    public List<TestEntity> test2() {
        List<TestEntity> ent = testService.findall();
        System.out.println(ent.size());
        return testService.findall();
    }

    @ResponseBody
    @RequestMapping(value = "/test3")
    public String test3() {
        TestEntity testEntity = new TestEntity();
        testEntity.setSex("3");
        testEntity.setName("小白白");
        testEntity.setAge("68");
        return testService.save(testEntity);
    }

}
