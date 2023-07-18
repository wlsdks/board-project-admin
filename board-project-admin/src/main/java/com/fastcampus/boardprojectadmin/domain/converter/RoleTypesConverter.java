package com.fastcampus.boardprojectadmin.domain.converter;

import com.fastcampus.boardprojectadmin.domain.constant.RoleType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * RoleType의 converter이다.
 * 바꾸는 장치니까 @Converter 라고 어노테이션을 적는다.
 * 컨버터 옵션에서 autoApply=true로 해주면 알아서 컨버터가 인식되어 동작한다. -> 근데 CollectionConverter에는 제대로 동작을 안한다..
 */
@Converter
public class RoleTypesConverter implements AttributeConverter<Set<RoleType>, String> {

    // 구분자를 선언한다.
    private static final String DELIMITER = ",";

    // RoleType 엔티티에서 db로 저장할때 동작한다.
    @Override
    public String convertToDatabaseColumn(Set<RoleType> attribute) {
        return attribute.stream()
                .map(RoleType::name)
                .sorted()
                .collect(Collectors.joining(DELIMITER)); // DELEMITER를 기준으로 join을 한다.
    }

    /**
     * dbData에서 entity를 RoleType으로 만들때 동작한다. DELIMITER를 기준으로 문자열을 쪼개서 stream()을 사용한다.
     * 참고로 여기선 String을 split() 했기 때문에 Arrays.stream()으로 해야만 스트림이 된다.
     */
    @Override
    public Set<RoleType> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(DELIMITER))
                .map(RoleType::valueOf) // enum의 이름을 받아서 출력해야하니 value를 받는다.
                .collect(Collectors.toSet()); // entity의 값은 변경이 가능해야하니 그냥 Set을 해준다.
    }

}
