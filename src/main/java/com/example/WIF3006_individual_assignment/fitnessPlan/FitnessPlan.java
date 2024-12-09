package com.example.WIF3006_individual_assignment.fitnessPlan;

import com.example.WIF3006_individual_assignment.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "fitness_plan")
public class FitnessPlan {
    @Id
    @SequenceGenerator(
            name = "fitness_plan_sequence",
            sequenceName = "fitness_plan_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fitness_plan_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "plan_title")
    private String planTitle;
    @Column(name = "plan_desc")
    private String planDesc;
    @Column(name = "goal")
    private String goal;       // e.g., weight loss, muscle gain
    @Column(name = "duration")
    private String duration;  // Duration in weeks or days
    @Column(name = "difficulty_level")
    private String difficultyLevel; // e.g., beginner, intermediate, advanced

    // default constructor
    public FitnessPlan() {
    }

    // Constructor with id
    public FitnessPlan(Long id, User user, String planTitle, String planDesc, String goal, String duration, String difficultyLevel) {
        this.id = id;
        this.user = user;
        this.planTitle = planTitle;
        this.planDesc = planDesc;
        this.goal = goal;
        this.duration = duration;
        this.difficultyLevel = difficultyLevel;
    }

    // Constructor without id
    public FitnessPlan(User user, String planTitle, String planDesc, String goal, String duration, String difficultyLevel) {
        this.user = user;
        this.planTitle = planTitle;
        this.planDesc = planDesc;
        this.goal = goal;
        this.duration = duration;
        this.difficultyLevel = difficultyLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    public String getPlanDesc() {
        return planDesc;
    }

    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
