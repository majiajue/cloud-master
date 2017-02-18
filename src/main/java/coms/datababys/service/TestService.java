package coms.datababys.service;

import coms.datababys.entity.TestEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lovme on 2017/2/15.
 */

public interface TestService {
    String look(String i);

    List<TestEntity> insert();

    List<TestEntity> findall();

    String save(TestEntity testEntity);
}
