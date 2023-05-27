package com.book.store.management.payloads;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class AuthorDTO {
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 6, message = "Author name must be min of 6 character !!")
    private String name;

    @Email(message = "Email address is not valid !!")
    private String email;
    private String biography;
}
