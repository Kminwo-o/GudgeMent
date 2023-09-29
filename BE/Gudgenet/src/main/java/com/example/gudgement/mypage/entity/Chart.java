package com.example.gudgement.mypage.entity;

import com.example.gudgement.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    private Member memberId;

    @Column(nullable = false)
    private long accountId;

    @Column(nullable = false)
    private int month;

    @Column(nullable = false)
    private int week;

    @Column
    private Long mon;

    @Column
    private Long tue;

    @Column
    private Long wen;

    @Column
    private Long thu;

    @Column
    private Long fri;

    @Column
    private Long sat;

    @Column
    private Long sun;

    @Builder
    public Chart(Member memberId, Long accountId, int month, int week) {
        this.memberId = memberId;
        this.accountId = accountId;
        this.month = month;
        this.week = week;
    }

    @PrePersist
    private void Chart () {
        this.mon = 0L;
        this.tue = 0L;
        this.wen = 0L;
        this.thu = 0L;
        this.fri = 0L;
        this.sat = 0L;
        this.sun = 0L;
    }

    public void setMon(Long mon) {
        this.mon += mon;
    }

    public void setTue(Long tue) {
        this.tue += tue;
    }

    public void setWen(Long wen) {
        this.wen += wen;
    }

    public void setThu(Long thu) {
        this.thu += thu;
    }

    public void setFri(Long fri) {
        this.fri += fri;
    }

    public void setSat(Long sat) {
        this.sat += sat;
    }

    public void setSun(Long sun) {
        this.sun += sun;
    }

    public void updateMon(Long mon) {
        this.mon = mon;
    }
    public void updateTue(Long tue) {
        this.tue = tue;
    }
    public void updateWen(Long wen) {
        this.wen = wen;
    }
    public void updateThu(Long thu) {
        this.thu = thu;
    }
    public void updateFri(Long fri) {
        this.fri = fri;
    }
    public void updateSat(Long sat) {
        this.sat = sat;
    }
    public void updateSun(Long sun) {
        this.sun = sun;
    }
}