package ru.mephi.vikingdemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;
import ru.mephi.vikingdemo.service.VikingService;

import java.util.List;

@RestController
@RequestMapping("/api/vikings")
@Tag(name = "Vikings", description = "Операции с викингами")
public class VikingController {

    private final VikingService vikingService;
    private VikingListener vikingListener;

    public VikingController(VikingService vikingService, VikingListener vikingListener) {
        this.vikingService = vikingService;
        this.vikingListener = vikingListener;
    }
    
    @GetMapping
    @Operation(summary = "Получить список созданных викингов", 
            operationId = "getAllVikings")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список успешно получен")
    })
    public List<Viking> getAllVikings() {
        System.out.println("GET /api/vikings called");
        return vikingService.findAll();
    }

    @GetMapping("/test")
    @Operation(summary = "Получить список тестовых викингов", 
            operationId = "getTest")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список успешно получен")
    })
    public List<String> test() {
        System.out.println("GET /api/vikings/test called");
        return List.of("Ragnar", "Bjorn");
    }


    @PostMapping("/post")
    public void addViking(){
        vikingListener.testAdd();
    }

    @PostMapping("/many")
    public void addVikings(){
        vikingListener.manyAdd();
    }


    //БЛОК НА УДАЛЕНИЕ ВИКИНГА
    @DeleteMapping("/delete")
    @Operation(summary = "Удалить викинга с номером",
            operationId = "delete")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Викинг успешно удален"),
            @ApiResponse(responseCode = "500", description = "Ошибка при удалении викинга, вероятно такого id нет")
    })
    public void deleteViking(@RequestParam int id){
        System.out.println("DELETE api/vikings/delete called");
        vikingListener.testDelete(id);
    }

    @PostMapping("/postViking")
    @Operation(summary = "Создать конкретного викинга",
            operationId = "create")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Викинг успешно создан"),
            @ApiResponse(responseCode = "500", description = "Ошибка при создании")
    })
    public void addViking(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam int heightCm,
            @RequestParam HairColor hairColor,
            @RequestParam BeardStyle beardStyle
            ){
        System.out.println("POST api/vikings/postViking called");
        vikingListener.testAdd(name, age, heightCm, hairColor, beardStyle);
    }

    @PutMapping("/put")
    @Operation(summary = "Изменить викинга",
            operationId = "put")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Викинг успешно изменен"),
            @ApiResponse(responseCode = "500", description = "Ошибка при изменении")
    })
    public void editViking(
            @RequestParam int id,
            @RequestParam (required = false) String name,
            @RequestParam (required = false) Integer age,
            @RequestParam (required = false) Integer heightCm,
            @RequestParam (required = false) HairColor hairColor,
            @RequestParam (required = false) BeardStyle beardStyle
    ){
        System.out.println("PUT api/vikings/put called");
        vikingListener.testEdit(id, name, age, heightCm, hairColor, beardStyle);
    }
}
