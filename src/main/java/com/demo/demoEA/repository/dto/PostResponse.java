package com.demo.demoEA.repository.dto;

import java.sql.Timestamp;

public record PostResponse(
	    Long id,
	    Long userId,
	    String content,
	    Timestamp createdAt
	) {}
