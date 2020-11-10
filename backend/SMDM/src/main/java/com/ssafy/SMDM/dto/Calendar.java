package com.ssafy.SMDM.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "calendar")
@Data
@NoArgsConstructor
public class Calendar {
    @Id
    @Column(name = "receipt_date")
    private String receiptdate;

    @Column(name = "user_Id")
    private String userid;

    @Column(name = "shopping_list")
    private String shoppinglist;

    @Column(name = "money")
    private Integer money;

    @Column(name = "grade")
    private Integer grade;

    @Builder
    public Calendar(String receiptDate, String userId, String shoppingList, Integer money, Integer grade) {
        receiptdate = receiptDate;
        userid = userId;
        shoppinglist = shoppingList;
        this.money = money;
        this.grade = grade;

    }

}
