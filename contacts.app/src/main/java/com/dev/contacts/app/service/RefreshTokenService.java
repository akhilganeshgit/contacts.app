package com.dev.contacts.app.service;

import com.dev.contacts.app.exception.ContactAppCustomException;
import com.dev.contacts.app.model.RefreshToken;
import com.dev.contacts.app.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepository.save(refreshToken);
    }

    public void validateRefreshToken(String refreshToken) {
        refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new ContactAppCustomException("Invalid Refresh Token"));
    }

    public void deleteRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteByToken(refreshToken);
    }
}
