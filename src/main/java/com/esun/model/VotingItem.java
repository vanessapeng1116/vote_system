package com.esun.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "voting_system")
public class VotingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(nullable = false,unique = true)
    private String name;

    @Transient
    private Long voteCount;


}
