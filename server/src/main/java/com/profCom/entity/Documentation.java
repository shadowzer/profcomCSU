package com.profCom.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by VolgiNN on 13.12.2016.
 */
@Entity
@Table(name="Documentation")
public class Documentation {
    @Id
    @Column(name="title",nullable = false,unique = true)
    public String title;

    @Column(name="href",nullable = false,unique = true)
    public String href;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Documentation() {

    }
}
