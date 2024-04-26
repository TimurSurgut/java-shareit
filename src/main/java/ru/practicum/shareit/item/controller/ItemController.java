package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final String ownerId = "X-Sharer-User-Id";
    private final ItemService itemService;

    @PostMapping
    public ItemDto createItem(@RequestBody ItemDto itemDto, HttpServletRequest request) {
        log.debug("Creating item element {}", itemDto);
        return itemService.create(itemDto, request.getIntHeader(ownerId));
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@PathVariable long itemId, @RequestBody ItemDto itemDto, HttpServletRequest request) {
        log.debug("Updating item element by id {}", itemId);
        itemDto.setId(itemId);
        return itemService.update(itemDto, request.getIntHeader(ownerId));
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable long itemId, HttpServletRequest request) {
        log.debug("Getting item by id : {}", itemId);
        return itemService.getItemById(itemId, request.getIntHeader(ownerId));
    }

    @GetMapping()
    public Collection<ItemDto> getUserItems(HttpServletRequest request) {
        log.debug("Getting all items by userId {}", request.getIntHeader(ownerId));
        return itemService.getItemsByUserId(request.getIntHeader(ownerId));
    }

    @GetMapping("/search")
    public Collection<ItemDto> getItemsBySearch(@RequestParam String text) {
        log.debug("Getting items by search text: {}", text);
        return itemService.getItemsBySearch(text);
    }
}