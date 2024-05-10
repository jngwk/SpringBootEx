package com.conan.demo.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.conan.demo.domain.Todo;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test // 메소드 실행 후 DEBUG CONSOLE에서 실행 확인
    public void test1() {
        log.info(
                "---------------------------");
        log.info(todoRepository);
    }

    @Test // 메소드 실행 후 workbench에서 테이블 내용 확인
    public void testInsert() {
        for (int i = 1; i <= 100; i++) {
            Todo todo = Todo.builder().title("Title ..." + i).dueDate(LocalDate.of(2024, 1, 1)).writer("user00")
                    .build();
            todoRepository.save(todo);

        }
    }

    @Test
    public void testRead() {
        Integer tno = 33;
        java.util.Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        log.info(todo);
    }

    @Test
    public void testModify() {
        Integer tno = 33;
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        todo.setTitle("33번 내용 수정");
        todo.setComplete(true);
        todo.setDueDate(LocalDate.of(2024, 5, 4));
        todoRepository.save(todo);
    }

    @Test
    public void testDelete() {
        todoRepository.deleteById(1);
    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
        Page<Todo> result = todoRepository.findAll(pageable);
        log.info(result.getTotalElements());
        result.getContent().stream().forEach(todo -> log.info(todo));
    }
}