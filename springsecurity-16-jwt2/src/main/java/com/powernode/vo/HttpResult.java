package com.powernode.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpResult implements Serializable {
    private Integer code; //响应码
    private String msg; //响应消息
    private Object data; //响应对象
}