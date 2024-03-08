package bookmyshow.serviceApp.Models;

import bookmyshow.serviceApp.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private int price;
    private String seatNo;
    private Boolean isFoodAttached;



    @ManyToOne
    @JoinColumn
    private Show show;


}
