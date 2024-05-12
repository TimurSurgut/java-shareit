package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.comment.CommentDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final String path = "X-Sharer-User-Id";
    @PostMapping
    public ItemDto createItem(@RequestBody ItemDto itemDto, HttpServletRequest request) {
        log.debug("Создание вещи {}", itemDto);
        return itemService.create(itemDto, request.getIntHeader(path));
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@PathVariable long itemId, @RequestBody ItemDto itemDto, HttpServletRequest request) {
        log.debug("Обновление вещи по id {}", itemId);
        itemDto.setId(itemId);
        return itemService.update(itemDto, request.getIntHeader(path));
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable long itemId, HttpServletRequest request) {
        log.debug("Получение вещи по id : {}", itemId);
        return itemService.getItemById(itemId, request.getIntHeader(path));
    }

    @GetMapping()
    public Collection<ItemDto> getUserItems(HttpServletRequest request) {
        log.debug("Получение всех вещей по id пользователя {}", request.getIntHeader(path));
        return itemService.getItemsByUserId(request.getIntHeader(path));
    }

    @GetMapping("/search")
    public Collection<ItemDto> getItemsBySearch(@RequestParam String text) {
        log.debug("Получение вещей с помощью поиска: {}", text);
        return itemService.getItemsBySearch(text);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto createCommentToItem(@PathVariable Long itemId, @RequestBody CommentDto comment,
                                          HttpServletRequest request) {
        log.debug("Создание комментария к вещи по id пользователя {}", request.getIntHeader(path));
        comment.setCreated(LocalDateTime.now());
        return itemService.addCommentToItem((long) request.getIntHeader(path), itemId, comment);
    }
}