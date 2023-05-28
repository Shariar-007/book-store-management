package com.book.store.management.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BookDAO {

    private Long id;

    @Size(min = 6, message = "Title must be min of 6 character !!")
    private String title;
    @NotEmpty
    @NotNull
    private String ISBN;

    @NotNull
    private Date yearOfPublication;

    @NotEmpty
    @NotNull
    private List<Long> authors = new ArrayList<>();
}
