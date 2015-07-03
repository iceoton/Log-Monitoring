package com.ascend.tmn.scouter.model;

import javax.persistence.*;

/**
 * Created by keerati on 7/1/15 AD.
 */
@Entity
@Table(name = "kios")
public class KiosLog {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
