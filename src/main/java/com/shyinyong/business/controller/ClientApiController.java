package com.shyinyong.business.controller;

import com.shyinyong.business.payload.*;
import com.shyinyong.business.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client_api")
public class ClientApiController {
    @GetMapping("/event_report")
    public ApiResponse getCurrentUser() {
        return new ApiResponse();
    }
}
