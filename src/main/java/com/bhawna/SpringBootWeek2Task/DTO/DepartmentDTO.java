package com.bhawna.SpringBootWeek2Task.DTO;

import com.bhawna.SpringBootWeek2Task.annotations.PasswordValidation;
import com.bhawna.SpringBootWeek2Task.annotations.PrimeNumberValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 25, message = "Number of characters in title should be in the range of [3,25]")
    private String title;

    @JsonProperty("isActive")
    @AssertTrue(message = "Department should be active")
    private Boolean isActive;

    @PastOrPresent
    @NotNull
    private LocalDate createdAt;

    @PrimeNumberValidation
    private Integer checkPrime;

    @PasswordValidation
    private String password;

}
