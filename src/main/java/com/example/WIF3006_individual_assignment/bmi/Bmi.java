package com.example.WIF3006_individual_assignment.bmi;

import com.example.WIF3006_individual_assignment.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "bmi")
public class Bmi {
    @Id
    @SequenceGenerator(
            name = "bmi_sequence",
            sequenceName = "bmi_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bmi_sequence"
    )
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "height", nullable = false)
    private Integer height;
    @Column(name = "weight", nullable = false)
    private Integer weight;
    @Column(name = "bmi_value")
    private Integer bmiValue;
    @Column(name = "bmi_category")
    private String bmiCategory;

    public Bmi() {
    }

    public Bmi(Long id, User user, Integer height, Integer weight) {
        this.id = id;
        this.user = user;
        this.height = height;
        this.weight = weight;
        calculateBmi();
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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getBmiValue() {
        return bmiValue;
    }

    public void setBmiValue(Integer bmiValue) {
        this.bmiValue = bmiValue;
    }

    public String getBmiCategory() {
        return bmiCategory;
    }

    public void setBmiCategory(String bmiCategory) {
        this.bmiCategory = bmiCategory;
    }

    // calculate BMI
    public void calculateBmi() {
        if (height != null && height > 0 && weight != null && weight > 0) {
            this.bmiValue = (weight * 10000) / (height * height);

            if (bmiValue < 18.5) {
                this.bmiCategory = "Underweight";
            } else if (bmiValue >= 18.5 && bmiValue <= 24.9) {
                this.bmiCategory = "Normal";
            } else if (bmiValue >= 25 && bmiValue <= 29.9) {
                this.bmiCategory = "Overweight";
            } else {
                this.bmiCategory = "Obese";
            }
        } else {
            this.bmiValue = null;
            this.bmiCategory = null;
        }
    }
}
