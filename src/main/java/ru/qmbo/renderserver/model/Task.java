package ru.qmbo.renderserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * Task
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 24.11.2022
 */
@Accessors(chain = true)
@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;
    @JsonIgnore
    @Column
    private Date startDate;
    @JsonIgnore
    @Column
    private Date completeDate;
    @Column
    private String status;

}
