package shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shareit.validator.PageableValidator;
import shareit.validator.ItemValidator;

import javax.validation.constraints.Positive;


@Controller
@RequestMapping(path = "/items")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ItemController {

    private final PageableValidator pageableValidator;
    private final ItemValidator itemValidator;
    private final ItemClient itemClient;
    private final String header = "X-Sharer-User-Id";

    @PostMapping
    public ResponseEntity<Object> createItem(@RequestBody ItemDto itemDto, @RequestHeader(header) @Positive long userId) {
        itemValidator.validateItemData(itemDto);
        return itemClient.createItem(userId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> updateItem(@PathVariable @Positive long itemId, @RequestBody ItemDto itemDto, @RequestHeader(header) @Positive long userId) {
        itemValidator.validateItemDataUpdate(itemDto);
        itemDto.setId(itemId);
        return itemClient.updateItem(userId, itemId, itemDto);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Object> getItemById(@PathVariable @Positive long itemId, @RequestHeader(header) @Positive long userId) {
        return itemClient.getItemById(userId, itemId);
    }

    @GetMapping()
    public ResponseEntity<Object> getUserItems(@RequestParam(defaultValue = "0") Integer from, @RequestParam(defaultValue = "10") Integer size,
                                               @RequestHeader(header) @Positive long userId) {
        pageableValidator.checkingPageableParams(from, size);
        return itemClient.getUserItems(userId, from, size);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> getItemsBySearch(@RequestParam(defaultValue = "0") Integer from, @RequestParam(defaultValue = "10") Integer size,
                                                   @RequestParam String text, @RequestHeader(header) @Positive long userId) {
        pageableValidator.checkingPageableParams(from, size);
        return itemClient.getItemsBySearch(userId, from, size, text);
    }

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<Object> createCommentToItem(@PathVariable @Positive Long itemId, @RequestBody CommentDto comment,
                                                      @RequestHeader(header) long userId) {
        itemValidator.validateCommentData(comment);
        return itemClient.createCommentToItem(userId, itemId, comment);
    }
}
