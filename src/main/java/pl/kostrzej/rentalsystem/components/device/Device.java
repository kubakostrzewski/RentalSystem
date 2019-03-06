package pl.kostrzej.rentalsystem.components.device;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.kostrzej.rentalsystem.components.category.Category;
import pl.kostrzej.rentalsystem.components.client.Client;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"category", "clients"})
@ToString(exclude = {"category", "clients"})
@Table(name = "device")
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private Long id;
    private String name;
    @Column(length = 2048)
    private String description;
    private int amount;
    private double price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToMany(mappedBy = "devices")
    private List<Client> clients = new ArrayList<>();
}
