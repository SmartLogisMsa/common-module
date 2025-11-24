package com.smartlogis.common.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathBuilder;

public class QuerydslSortUtils {
	/**
	 * 기본 정렬 기준만 적용
	 *
	 * @param path  정렬 대상 Q 클래스
	 * @param field 기본 정렬 필드명 (예: "createdAt")
	 * @return OrderSpecifier 배열
	 */
	public static <T> OrderSpecifier<?>[] toOrderSpecifiers(EntityPathBase<T> path, String field) {
		return toOrderSpecifiers(path, field, Sort.unsorted());
	}

	/**
	 * Pageable의 정렬 정보와 기본 정렬 기준 적용
	 */
	public static <T> OrderSpecifier<?>[] toOrderSpecifiers(EntityPathBase<T> path, String field, Sort sort) {
		List<OrderSpecifier<?>> orders = new ArrayList<>();
		PathBuilder<T> pathBuilder = new PathBuilder<>(path.getType(), path.getMetadata());

		// 요청된 정렬 정보 추가
		for (Sort.Order order : sort) {
			Order direction = order.isAscending() ? Order.ASC : Order.DESC;
			orders.add(new OrderSpecifier<>(direction, pathBuilder.getString(order.getProperty())));
		}

		// 기본 정렬 (ex. createdAt DESC)
		orders.add(new OrderSpecifier<>(Order.DESC, pathBuilder.getDateTime(field, LocalDateTime.class)));

		return orders.toArray(new OrderSpecifier[0]);
	}
}
