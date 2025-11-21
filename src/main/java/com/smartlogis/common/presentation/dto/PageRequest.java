package com.smartlogis.common.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
	@Schema(description = "페이지 번호(0부터 시작)", example = "0", defaultValue = "0")
	private int page = 0;
	@Schema(description = "페이지 크기", example = "10", defaultValue = "10")
	private int size = 10;
	@Schema(description = "정렬 기준 필드명", example = "createdAt")
	private String sortBy;
	@Schema(description = "정렬 방향 (ASC/DESC)", example = "DESC")
	private String direction;
}