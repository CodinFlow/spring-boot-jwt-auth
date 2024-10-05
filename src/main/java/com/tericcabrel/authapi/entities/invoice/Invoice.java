package com.tericcabrel.authapi.entities.invoice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int user_id;
    private String username;
    private String invoiceFrom;
    private String billTo;
    private String shipTo;
    private String invoiceNumber;
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Europe/Zagreb")
    private LocalDate date;
    private String payment;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;
    private boolean archived;
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Europe/Zagreb")
    private LocalDate dueDate;
    private String poNumber;
    private String notes;
    private String terms;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Item> items;


    private BigDecimal amountPaid;
    private BigDecimal subtotal;
    private BigDecimal balanceDue;


    @CreationTimestamp
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", timezone = "Europe/Zagreb")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", timezone = "Europe/Zagreb")
    private LocalDateTime updatedAt;

}
