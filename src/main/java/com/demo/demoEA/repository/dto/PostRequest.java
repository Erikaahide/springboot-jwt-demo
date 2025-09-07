package com.demo.demoEA.repository.dto;

import jakarta.validation.constraints.NotBlank;

public record PostRequest(@NotBlank String content) {}

