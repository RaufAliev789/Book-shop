package com.example.book_shop;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Null
    private Long authorId;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    @Past
    private LocalDate birthday;
}
