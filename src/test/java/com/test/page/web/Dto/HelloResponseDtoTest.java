package com.test.page.web.Dto;

import org.junit.Test;

public class HelloResponseDtoTest {

    @Test
    public void DtoTest() {
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        assert (dto.getName()).equals(name);
        assert (dto.getAmount()) == amount;
    }
}
