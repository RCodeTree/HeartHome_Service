package com.rong.heartserver.Config;

import com.rong.heartcommon.Enum.WorkTypeEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * WorkTypeEnum 自定义类型处理器
 * 用于处理数据库中的字符串值与 WorkTypeEnum 枚举之间的转换
 */
@MappedTypes(WorkTypeEnum.class)
public class WorkTypeEnumTypeHandler extends BaseTypeHandler<WorkTypeEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, WorkTypeEnum parameter, JdbcType jdbcType) throws SQLException {
        // 将枚举值转换为数据库字符串
        ps.setString(i, parameter.getValue());
    }

    @Override
    public WorkTypeEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 从数据库读取字符串值并转换为枚举
        String value = rs.getString(columnName);
        return value == null ? null : WorkTypeEnum.fromValue(value);
    }

    @Override
    public WorkTypeEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 从数据库读取字符串值并转换为枚举
        String value = rs.getString(columnIndex);
        return value == null ? null : WorkTypeEnum.fromValue(value);
    }

    @Override
    public WorkTypeEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // 从存储过程读取字符串值并转换为枚举
        String value = cs.getString(columnIndex);
        return value == null ? null : WorkTypeEnum.fromValue(value);
    }
}