package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String movieId;
    @Column(nullable = false)
    private String reviewText;

    @Column(nullable = false)
    private int stars;

}
