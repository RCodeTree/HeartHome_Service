package com.rong.heartcommon.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用返回结果类
 *
 * @param <T> 返回的数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private Integer code; // 状态码: 1-成功, 0-失败
    private String msg; // 返回消息
    private T data; // 返回数据

    /**
     * 成功响应（无数据）
     *
     * @param <E> 数据类型
     * @return Result对象
     */
    public static <E> Result<E> success() {
        return new Result<E>(200, "success", null);
    }

    /**
     * 成功响应（有数据）
     *
     * @param data 返回的数据
     * @param <E>  数据类型
     * @return Result对象
     */
    public static <E> Result<E> success(E data) {
        return new Result<E>(200, "success", data);
    }

    /**
     * 带状态码和消息的响应（自定义状态码和消息）
     *
     * @param code 状态码
     * @param msg  消息
     * @param data 数据
     * @param <E>  数据类型
     * @return Result对象
     */
    public static <E> Result<E> success(Integer code, String msg, E data) {
        return new Result<E>(code, msg, data);
    }

    /**
     * 失败响应
     *
     * @param msg 失败消息
     * @param <E> 数据类型
     * @return Result对象
     */
    public static <E> Result<E> error(String msg) {
        return new Result<E>(500, msg, null);
    }

    /**
     * 失败响应（自定义状态码和消息）
     *
     * @param code 状态码
     * @param msg  失败消息
     * @param <E>  数据类型
     * @return Result对象
     */
    public static <E> Result<E> error(Integer code, String msg) {
        return new Result<E>(code, msg, null);
    }
}