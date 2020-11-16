package com.wk.web.service.slave;

import com.wk.mapper.slave.PostgresqlUsersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Slf4j
public class PostgresqlUserService {
    @Autowired
    private PostgresqlUsersMapper userMapper;

    @Transactional(isolation = Isolation.DEFAULT, transactionManager = "slaveTransactionManager")
    public int getSeqs(){
        int seq = userMapper.getSeq();
        return seq;
    }

    public void seqs(){
//        for (int i = 0; i < 10; i++) {
//            log.info("seq:{}", getSeqs());
//        }
        Arrays.asList(1,2,3,4,5,6,7,8,9).stream().forEach(tt -> {
            log.info("list : {}", tt);
            log.info("get seq: {}",  getSeqs());
        });
    }
}
