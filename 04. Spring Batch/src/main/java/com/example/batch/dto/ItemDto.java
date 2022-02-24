package com.example.batch.dto;

import lombok.Data;

/**
 * tip.
 * kotlin 에서는 기본 생성자가 생성되지 않는다. 따라서 FlatFileItem 사용 시 해당 이슈로 오류가 발생한다.
 * - @NoArg plug-in 추가
 * - val -> var 설정
 *
 * @NoArg
 * data class ItemDto(
 *     var name: String,
 *     var age: Int
 * )
 */
@Data
public class ItemDto {
    String name;
    int age;
}
