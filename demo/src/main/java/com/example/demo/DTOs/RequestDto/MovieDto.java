package com.example.demo.DTOs.RequestDto;


import com.example.demo.Enums.Genre;
import com.example.demo.Enums.Language;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private String name;
    private double duration;
    private double rating;
    private Genre genre;
    private Language language;
    private Date reliseDate;
}