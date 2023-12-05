package me.seongim.daangn.domain.entity.user;

import lombok.*;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Region {

    @Id
    @GeneratedValue
    @Column(name = "region_id")
    private Long id;

    private String state;   // 시, 도

    private String city;    // 시, 군, 구

    private String town;    // 읍, 면, 동, 리

    private Point location;   // 위도, 경도
}
