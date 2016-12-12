package com.profCom.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by VolgiNN on 10.12.2016.
 */
@Entity
@Table(name="FAQObject")
public class FAQObject {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy = "increment")
    public long id;

    @Column(name="question",nullable = false, unique = true)
    public String question;

    @Column(name="answer",nullable = false)
    public String answer;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public FAQObject() {
    }
}
