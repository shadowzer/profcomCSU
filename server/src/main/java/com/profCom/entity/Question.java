package com.profCom.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by VolgiNN on 12.12.2016.
 */
@Entity
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy = "increment")
    public long id;

    @Column(name = "question",nullable = false, unique = true)
    public String question;

    @Column(name = "answer")
    public String answer;

    @OneToOne
    @JoinColumn(name="questioner",nullable = false)
    public User questioner;

    @OneToOne
    @JoinColumn(name="answerer")
    public User answerer;

    @Column(name = "anon",nullable =false)
    public boolean anon;

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

    public User getQuestioner() {
        return questioner;
    }

    public void setQuestioner(User questioner) {
        this.questioner = questioner;
    }

    public User getAnswerer() {
        return answerer;
    }

    public void setAnswerer(User answerer) {
        this.answerer = answerer;
    }

    public boolean isAnon() {
        return anon;
    }

    public void setAnon(boolean anon) {
        this.anon = anon;
    }

    public Question() {

    }
}
