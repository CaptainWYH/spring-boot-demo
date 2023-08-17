package com.wyhcode.util;

import com.wyhcode.enums.NTEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author weiyuhui
 * @date 2023/8/11 9:40
 * @description
 */

@Slf4j
public class EnumUtils {

    private static final String MESSAGE = "INVALID ENUM CODE , %s ,  %s";

    public synchronized static <T extends NTEnum> T getEnumByCode(Integer code,Class<T> enumClass) throws Exception {
        Optional<T> enumOptional = Stream.of(enumClass.getEnumConstants()).filter(item -> item.code().equals(code)).findAny();
        if (! enumOptional.isPresent()){
            log.info(String.format(MESSAGE,enumClass.getName(),code));
            throw new Exception(String.format(MESSAGE, enumClass.getName(), code));
        }
        return enumOptional.get();
    }
}
