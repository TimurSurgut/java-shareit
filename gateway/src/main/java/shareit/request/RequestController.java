package shareit.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shareit.validator.PageableValidator;

import javax.validation.constraints.Positive;

@Controller
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
@Slf4j
@Validated
public class RequestController {

    private final PageableValidator pageableValidator;
    private final ItemRequestValidator itemRequestValidator;
    private final RequestClient requestClient;
    private final String path = "X-Sharer-User-Id";


    @PostMapping
    public ResponseEntity<Object> addNewRequest(@RequestBody ItemRequestDto requestDto, @RequestHeader(path) @Positive long userId) {
        itemRequestValidator.validateItemRequestData(requestDto);
        log.debug("Gateway: Creating item request element {}", requestDto);
        return requestClient.createRequest(userId, requestDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllUserItemsWithResponses(@RequestHeader(path) @Positive long userId) {
        log.debug("Gateway: Getting collection of users' items requests");
        return requestClient.getAllUserItemsWithResponses(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCreatedRequests(@RequestParam(defaultValue = "0") Integer from, @RequestParam(defaultValue = "10") Integer size, @RequestHeader(path) @Positive long userId) {
        pageableValidator.checkingPageableParams(from, size);
        log.debug("Gateway: Getting collection of created requests");
        return requestClient.getAllCreatedRequests(userId, from, size);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Object> getRequestById(@PathVariable @Positive Long requestId, @RequestHeader(path) @Positive long userId) {
        log.debug("Gateway: Getting request by id: {}", requestId);
        return requestClient.getRequestById(userId, requestId);
    }
}
