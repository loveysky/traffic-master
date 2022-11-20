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
@Data
public class ResponseResult {

    public ResponseResult(String resultCode, String resultMsg, Object result) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.result = result;
    }

    public ResponseResult(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public ResponseResult(String resultCode) {
        this.resultCode = resultCode;
    }

    //系统通用成功状态码
    private static final String TRAFFIC_SYSTEM_SUCCESS = "000000";

    //响应状态码
    String resultCode;
    //响应信息（出错时会有）
    String resultMsg;
    //具体结果
    Object result;



    /**
     * 构建通用成功的返回结果
     * @return ResponseResult
     */
    public static ResponseResult buildResponseResult(){
        return new ResponseResult(TRAFFIC_SYSTEM_SUCCESS);
    }

    public static ResponseResult buildResponseResult(String resultCode){
        return new ResponseResult(resultCode);
    }

    public static ResponseResult buildResponseResult(String resultCode, String resultMsg){
        return new ResponseResult(resultCode,resultMsg);
    }
    public static ResponseResult buildResponseResult(String resultCode, String resultMsg, Object result){
        return new ResponseResult(resultCode,resultMsg,result);
    }

}
