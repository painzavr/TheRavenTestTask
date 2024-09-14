package com.example.demo.customer.customer;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long created;

    private Long updated;

    private String fullName;

    private String email;

    private String phone;

    private boolean isActive;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
