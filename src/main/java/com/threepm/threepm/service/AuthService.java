package com.threepm.threepm.service;

import com.threepm.threepm.payload.LoginDto;
import com.threepm.threepm.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
