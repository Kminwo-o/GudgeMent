package com.example.gudgement.member.entity;

import com.example.gudgement.progress.entity.Progress;
import com.example.gudgement.shop.entity.Inventory;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
*  사용자 (멤버) 관리 Entity 입니다.
* */

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements Serializable {

    @Id
    @Column(nullable = false)
    private Long memberId;

    @Column(length = 50, nullable = false)
    private String email;

    @Column
    private int gender;

    @Column
    private int age;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private boolean emailApprove;

    @Column(nullable = false)
    private boolean nicknameApprove;

    @Column(nullable = false)
    private long tiggle;

    @Column(nullable = false)
    private long exp;

    @Column(nullable = false)
    private int level;
    
    // 달 별 과소비금액
    @Column
    private Long monthOverconsumption;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int pedometer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Grade grade;

    @Column
    private LocalDateTime createAt;

    @Column
    private String refreshToken;


    //계좌 연동을 위한 노아가 작성한 코드
//    @OneToOne(mappedBy = "member")
//    private VirtualAccount virtualAccount;
    @Column(nullable = true)
    private Long virtualAccountId; // This replaces the VirtualAccount field

    public void setVirtualAccountId(Long virtualAccountId) {
        this.virtualAccountId = virtualAccountId;
    }


    // 연결 관계
//    @OneToMany(mappedBy = "memberId", cascade = CascadeType.REMOVE)
//    private List<Item> set_item = new ArrayList<Item>();
    @Lob
    @Column
    private String firebaseToken;

    /* 연결 관계 */
    // 상점, 진행도 관련
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Inventory> setItem = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Progress> progresses = new ArrayList<>();

    // 미구현
    // 게임, 계좌 관련
//     @OneToOne(mappedBy = "memberId", cascade = CascadeType.REMOVE)
//    private GameRoom roomId;
//
//    @OneToMany(mappedBy = "memberId", cascade = CascadeType.REMOVE)
//    private List<PaymentHistory> paymentHistoryList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
//    private List<Account> accounts = new ArrayList<>();

    @Builder
    public Member(Long id, String email, int gender, int age, String nickname, String refreshToken) {
        this.memberId = id;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.nickname = nickname;
        this.refreshToken = refreshToken;
        this.exp = 0L;
        this.tiggle = 500L;
    }

    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
        this.grade = Grade.ROLE_USER;
        this.level = 1;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
        this.nicknameApprove = true;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateEmail(String email) {
        this.email = email;
        this.emailApprove = true;
    }
    
    public void useTiggle(Long tiggle) {
        this.tiggle -= tiggle;
    }

    public void addTiggle(Long tiggle) {
        this.tiggle += tiggle;
    }

    public void setFirebaseToken(String token) {
        this.firebaseToken = token;
    }

    public void addTiggle(long tiggle) {
        this.tiggle += tiggle;
    }

    public void subtractTiggle(long tiggle) {
        if (this.tiggle < tiggle) {
            throw new IllegalArgumentException("The user does not have enough money");
        }
        this.tiggle -= tiggle;
    }

    public void addExp(long exp) {
        this.exp += exp;
        
        // Calculate the total experience required for the next level.
        long requiredExpForNextLevel = (long) Math.pow(2, this.level);

        // Check if the user has enough experience to level up.
        while (this.exp >= requiredExpForNextLevel) {
            this.level++;
            requiredExpForNextLevel = (long) Math.pow(2, this.level);  // Update the requirement for the next level.
        }
    }






    public void updateGrade(Grade grade) {
        this.grade = grade;
    }

    public void updateOverConsumption(Long monthOverconsumption) {
        this.monthOverconsumption = monthOverconsumption;
    }
}
