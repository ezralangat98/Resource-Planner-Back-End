package com.react_spring_boot.Organization;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.react_spring_boot.User.User;
import com.react_spring_boot.Venues.Boardroom;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@JsonIncludeProperties({"organizationId", "organizationName"})
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private int organizationId;

    @Column()
    private String organizationName;

    @ToString.Exclude
    @OneToMany(mappedBy = "organization")
    List<User> users = new ArrayList<>();

//    @OneToMany(mappedBy = "organization")
//    List<Boardroom> boardrooms = new ArrayList<>();


}
