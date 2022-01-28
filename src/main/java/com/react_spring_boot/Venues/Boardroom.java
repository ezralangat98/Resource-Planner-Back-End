package com.react_spring_boot.Venues;

import com.react_spring_boot.Organization.Organization;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Boardroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int boardroomId;
    private String boardroomName;
    private int capacity;
    private String tv;
    private String whiteboard;
    private String conferencePhone;

//    @ToString.Exclude
//    @ManyToOne
//    @JoinColumn(name = "organization_id")
////    @NotBlank(message = "Organization cannot be blank!")
//    private Organization organization;


}
