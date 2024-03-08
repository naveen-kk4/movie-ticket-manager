package bookmyshow.serviceApp.DTOs.RequestDto;

import bookmyshow.serviceApp.Enums.Genre;
import bookmyshow.serviceApp.Enums.Language;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;
@Data
public class AddMovieDto {

    private String movieName;

    private int duration;

    private String director;


    private double rating;

    private Date releaseDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Language language;
}
