package com.rainbowsea.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpResult {

    private Integer code;  // 返回给前端的自定义响应码
    private String msg;  // 返回给前端的消息
    private Object data;  // 返回给前端的数据

}
