package com.vnpt.hethonghotro.controller;

import com.vnpt.hethonghotro.dto.HanhDongRequest;
import com.vnpt.hethonghotro.service.YeuCauService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/yeu-cau")
@RequiredArgsConstructor
public class YeuCauController {

    private final YeuCauService yeuCauService;

    @PostMapping("/{id}/hanh-dong")
    public ResponseEntity<?> xuLyHanhDong(
            @PathVariable String id,
            @Valid @RequestBody HanhDongRequest request,
            Authentication authentication // Spring Security sẽ tự động inject đối tượng này
    ) {
        try {
            // Lấy username từ người dùng đã được xác thực
            String username = authentication.getName();
            yeuCauService.xuLyHanhDong(id, request, username);

            String message = "Hành động '" + request.getHanhDong() + "' đã được thực hiện thành công.";
            return ResponseEntity.ok(Map.of("message", message));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}