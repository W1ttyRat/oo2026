package ee.tanel.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "orders")

public class Order { //error syntax, ei sobi "Order"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Date created;
    private double total;
    private String parcelMachine;

    @ManyToOne
    private Person person;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderRow> orderRows;




}

