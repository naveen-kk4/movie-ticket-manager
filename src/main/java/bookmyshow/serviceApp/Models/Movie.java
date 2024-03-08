package bookmyshow.serviceApp.Models;

import bookmyshow.serviceApp.Enums.Genre;
import bookmyshow.serviceApp.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name="movies")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   @Column(nullable = false)
    private String movieName;

    private String director;

    private int duration;

    @Column(scale = 2)
    private double rating;

    private Date releaseDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();



}
