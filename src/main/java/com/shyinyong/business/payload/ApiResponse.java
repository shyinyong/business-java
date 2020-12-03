package com.shyinyong.business.payload;

import lombok.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

@Data
public class ApiResponse {
    private int code;
    private String msg;
    private String time;

    public ApiResponse() {
        this.code = 0;
        this.msg = "";
        this.time = "";
        this.time = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now());
    }
}
