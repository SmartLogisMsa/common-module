package com.smartlogis.common.presentation.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PageResponse<T> {

	private List<T> content;
	private int page;
	private int size;
	private long total;

	private static final ObjectMapper mapper = new ObjectMapper();

	private PageResponse(List<T> content, int page, int size, long total) {
		this.content = content;
		this.page = page;
		this.size = size;
		this.total = total;
	}

	public static <T> PageResponse<T> from(Page<T> page) {
		return new PageResponse<>(
			page.getContent(),
			page.getNumber(),
			page.getSize(),
			page.getTotalElements()
		);
	}

	public static <T, K> PageResponse<K> from(Page<T> page, Class<K> dto) {
		List<K> mapped = page.getContent().stream().map(entity -> {
			try {
				return mapper.convertValue(entity, dto);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			}
		}).toList();

		return new PageResponse<>(
			mapped,
			page.getNumber(),
			page.getSize(),
			page.getTotalElements()
		);
	}
}
