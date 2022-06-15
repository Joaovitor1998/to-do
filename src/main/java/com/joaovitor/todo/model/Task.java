package com.joaovitor.todo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    @Lob
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private LocalDateTime finishedAt;

    private boolean isFinished;

    public Task() {
        this.createdAt = LocalDateTime.now();
        this.isFinished = false;
    }

    public Task(String title){
        this.title = title;
        this.createdAt = LocalDateTime.now();
        this.isFinished = false;
    }

    public Task(String title, String description){
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.isFinished = false;
    }

    public Long getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return this.modifiedAt;
    }

    public void modifyTask(){
        this.modifiedAt = LocalDateTime.now();
    }

    public LocalDateTime getFinishedAt(){
        return this.finishedAt;
    }

    public void finishTask(){
        this.finishedAt = LocalDateTime.now();
        this.isFinished = true;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public void recoverTask() {
        this.finishedAt = null;
        this.isFinished = false;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

}
