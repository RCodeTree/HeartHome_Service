package com.rong.heartcommon.Exception.CommonException;

/**
 * @ClassName: GetDataException
 * @Description: 获取数据异常
 * @Author: rong
 * @Date: 2025/8/9
 */
public class GetDataException extends RuntimeException {
    private String detailMessage;

    public GetDataException() {
    }

    public GetDataException(String message) {
        this.detailMessage = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}
