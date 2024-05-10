package com.conan.demo.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.conan.demo.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoServiceTest {
    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder().title("서비스 테스트").writer("tester").dueDate(LocalDate.of(2024, 05, 10))
                .build();
        Integer tno = todoService.register(todoDTO);
        log.info("TNO: " + tno);
    }

    @Test
    public void testGet() {
        Integer tno = 301;
        TodoDTO todoDTO = todoService.get(tno);
        log.info(todoDTO);
    }

    // @Test
    // public void testModify() {
    // Integer tno = 301;
    // TodoDTO todoDTO = todoService.get(tno);
    // log.info(todoDTO);
    // }

    // @Test
    // public void testDelete() {
    // Integer tno = 301;
    // TodoDTO todoDTO = todoService.get(tno);
    // log.info(todoDTO);
    // }
}
