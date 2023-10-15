package br.com.ramonmendes.todolist.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_task")
public class TaskModel {
    @Id
    @GeneratedValue(generator= "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalTime startAt;
    private LocalTime endAt;
    private String priority;
    private UUID idUser;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
}
