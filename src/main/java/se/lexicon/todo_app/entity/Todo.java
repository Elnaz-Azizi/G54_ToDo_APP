package se.lexicon.todo_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "todos")

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "attachments")
@EqualsAndHashCode(exclude = "attachments")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private boolean completed;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private Set<Attachment> attachments = new HashSet<>();

    // Constructor with required fields
    public Todo(String title, String description, boolean completed, LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.dueDate = dueDate;
    }

    public Todo(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Transient
    // transient annotation is used to indicate that a field should not be persisted in the database.
    public boolean demo;


    public void addAttachment(Attachment attachment) {
        if (attachments == null) {
            attachments = new HashSet<>();
        }
        attachments.add(attachment);
        attachment.setTodo(this); // sync back-reference
    }

    public void removeAttachment(Attachment attachment) {
        attachments.remove(attachment);
        attachment.setTodo(null); // disconnect both ways
    }

}
