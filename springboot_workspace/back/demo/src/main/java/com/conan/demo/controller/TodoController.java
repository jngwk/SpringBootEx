package com.conan.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conan.demo.dto.PageRequestDTO;
import com.conan.demo.dto.PageResponseDTO;
import com.conan.demo.dto.TodoDTO;
import com.conan.demo.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService service;

    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable(name = "tno") Integer tno) {
        return service.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> List(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);
        return service.list(pageRequestDTO);
    }

    @PostMapping("/")
    public Map<String, Integer> register(@RequestBody TodoDTO todoDTO) {
        log.info("TodoDTO: " + todoDTO);
        Integer tno = service.register(todoDTO);
        return Map.of("TNO", tno);
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(
            @PathVariable(name = "tno") Integer tno,
            @RequestBody TodoDTO todoDTO) {
        todoDTO.setTno(tno);
        log.info("Modify : " + todoDTO);
        service.modify(todoDTO);
        return Map.of("RESULT", "SUCCESS");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> remove(@PathVariable(name = "tno") Integer tno) {
        log.info("Remove :" + tno);
        service.remove(tno);
        return Map.of("RESULT", "SUCCESS");
    }
}
