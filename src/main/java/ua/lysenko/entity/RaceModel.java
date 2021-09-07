package ua.lysenko.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "race_models", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"race_id", "horse_id"}, name = "RACE_HORSE_CONSTRAINT"),
        @UniqueConstraint(columnNames = {"race_id", "position"}, name = "RACE_POSITION_CONSTRAINT")
})
public class RaceModel {

    @Column(name = "race_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long raceId;

    @Column(name = "horse_id")
    private long horseId;

    @Column
    private long result;

    @Column
    private long position;

    @Column
    private LocalDate date;

    @Column
    private boolean bet;
}
