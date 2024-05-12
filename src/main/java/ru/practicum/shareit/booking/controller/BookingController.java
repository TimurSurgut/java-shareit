package ru.practicum.shareit.booking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.service.BookingService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@Slf4j
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final String path = "X-Sharer-User-Id";

    @PostMapping
    public BookingDto createBooking(@RequestBody BookingDto bookingDto, HttpServletRequest request) {
        log.info("Создание бронирования : {}", bookingDto);
        return bookingService.addBooking(bookingDto, Long.valueOf(request.getHeader(path)));
    }

    @PatchMapping("/{bookingId}")
    public BookingDto approveBooking(@PathVariable Long bookingId, @RequestParam String approved,
                                     HttpServletRequest request) {
        log.info("Присвоить бронированию статус \"одобрить\": {}, status: {}", bookingId, approved);
        return bookingService.approveBooking(bookingId, Long.valueOf(request.getHeader(path)), approved);
    }

    @GetMapping
    public List<BookingDto> getAllBookingsForUser(@RequestParam(defaultValue = "ALL") String state,
                                                  HttpServletRequest request) {
        log.info("Получение информации по бронированиям пользователя");
        return bookingService.getAllBookingsByUserId(Long.valueOf(request.getHeader(path)), state);
    }

    @GetMapping("/owner")
    public List<BookingDto> getAllBookingsForOwner(@RequestParam(defaultValue = "ALL") String state,
                                                   HttpServletRequest request) {
        log.info("Получение информации по бронированиям владельцев");
        return bookingService.getAllBookingsByOwnerId(Long.valueOf(request.getHeader(path)), state);
    }

    @GetMapping("/{bookingId}")
    public BookingDto getInfoForBooking(@PathVariable Long bookingId, HttpServletRequest request) {
        log.info("Получение информации для бронирования: {}", bookingId);
        return bookingService.getBookingInfo(bookingId, Long.valueOf(request.getHeader(path)));
    }
}