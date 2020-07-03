package com.study.work.si.config;

import lombok.Data;

@Data
public class CodeErrorBean {
    String code;
    String message;
    boolean ignore;
}
