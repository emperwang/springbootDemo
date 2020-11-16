package com.wk.mapper.slave;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostgresqlUsersMapper {
    int getSeq();
}
