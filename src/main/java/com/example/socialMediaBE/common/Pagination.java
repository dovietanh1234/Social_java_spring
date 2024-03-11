package com.example.socialMediaBE.common;

import com.example.socialMediaBE.repositories.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Pagination {
    private final IUserRepo _iUserRepo;
}
