package com.jennifer.meritperk.infrastructure.controllers;

import com.jennifer.meritperk.usecase.payload.request.SendRewardRequest;
import com.jennifer.meritperk.usecase.payload.response.ApiResponse;
import com.jennifer.meritperk.usecase.payload.response.SendRewardResponse;
import com.jennifer.meritperk.usecase.services.SendRewardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/student")
public class SendRewardController {

    private final SendRewardService sendRewardService;

    @PostMapping("/sendReward")
    public ResponseEntity<ApiResponse<SendRewardResponse>> sendReward(@RequestBody SendRewardRequest sendRewardRequest) throws Exception {
        return ResponseEntity.ok(new ApiResponse<>("Reward sent",true,sendRewardService.sendRewardResponse(sendRewardRequest)));
    }

}
