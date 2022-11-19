package com.fan.api.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @date: 2022/11/19 - 11 - 19 - 13:18
 * @version: 1.0
 * 所有请求统一返回
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseResult {

    public ResponseResult(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public ResponseResult(String resultCode) {
        this.resultCode = resultCode;
    }

    //响应状态码
    String resultCode;
    //响应信息（出错时会有）
    String resultMsg;
    //具体结果
    Object result;
}
