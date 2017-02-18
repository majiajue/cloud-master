package coms.datababys.service.impl;

import coms.datababys.dao.TestDAO;
import coms.datababys.entity.TestEntity;
import coms.datababys.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lovme on 2017/2/15.
 */

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDAO testDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String look(String i){
        String tell=null;
        if ("1".equals(i)){
            tell="输出i=男";
        }else if("0".equals(i)){
            tell="输出i=女";
        }else{
            tell="输出i=另类";
        }
        return tell;
    }

    public List<TestEntity> insert(){
        String sql ="select * from test";
        return jdbcTemplate.query(sql,new RowMapper<TestEntity>() {
            @Override
            public TestEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                TestEntity t = new TestEntity(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("sex"),resultSet.getString("age"));
                return t;
            }
        });
    }

    public List<TestEntity> findall(){
       return testDAO.findAll();
    }

    public String save(TestEntity testEntity){
        testDAO.save(testEntity);
       return "保存成功!";
    }

}
