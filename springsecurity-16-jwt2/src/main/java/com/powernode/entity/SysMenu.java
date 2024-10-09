package com.powernode.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysMenu implements Serializable {
    private Integer id;
    private Integer pid;
    private Integer type;
    private String name;
    private String code;
}