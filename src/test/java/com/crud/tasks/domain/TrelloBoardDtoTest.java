package com.crud.tasks.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloBoardDtoTest {

    @Test
    public void createTrelloBoardDtoTest() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("id", "name", new ArrayList<>());
        //Then
        assertEquals("id", trelloBoardDto.getId());
        assertEquals("name", trelloBoardDto.getName());
    }
}
