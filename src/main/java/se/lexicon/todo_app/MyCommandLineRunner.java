package se.lexicon.todo_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.todo_app.entity.Attachment;
import se.lexicon.todo_app.entity.Person;
import se.lexicon.todo_app.entity.Todo;
import se.lexicon.todo_app.repository.PersonRepository;
import se.lexicon.todo_app.repository.TodoRepository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final TodoRepository todoRepository;

    public MyCommandLineRunner(PersonRepository personRepository, TodoRepository todoRepository) {
        this.personRepository = personRepository;
        this.todoRepository = todoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello, World!");
        Person dev1 = Person.builder().name("Dev1").email("dev1@test.se").build();
        Person dev2 = Person.builder().name("Dev2").email("dev2@test.se").build();
        Person dev3 = Person.builder().name("Dev3").email("dev3@test.se").build();

        dev1 = personRepository.save(dev1);
        dev2 = personRepository.save(dev2);
        dev3 = personRepository.save(dev3);

        Todo t1 = Todo.builder()
                .title("Todo1")
                .description("Description1")
                .completed(false)
                .dueDate(LocalDateTime.now().plusDays(1))
                .build();

        Todo t2 = Todo.builder()
                .title("Todo2")
                .description("Description3")
                .completed(false)
                .dueDate(LocalDateTime.now().plusDays(2))
                .build();

        Todo t3 = Todo.builder()
                .title("Todo3")
                .description("Description3")
                .completed(false)
                .dueDate(LocalDateTime.now().plusDays(3))
                .build();

        Todo t4 = Todo.builder()
                .title("Todo4")
                .description("Description4")
                .completed(false)
                .dueDate(LocalDateTime.now().plusDays(1))
                .build();


        Todo t5 = Todo.builder()
                .title("Todo5")
                .description("Description5")
                .completed(false)
                .build();


        Attachment file1 = Attachment.builder()
                .fileName("unit-test-guide-pdf")
                .fileType("pdf")
                .data("Sample PDF Content".getBytes())
                .todo(t1).build();

        Attachment file2 = Attachment.builder()
                .fileName("ui.png")
                .fileType("png")
                .data("Sample Image Content".getBytes())
                .todo(t1).build();

        Attachment file3 = Attachment.builder()
                .fileName("todo_app.png")
                .fileType("png")
                .data(Files.readAllBytes(Paths.get( "img/todo_app.png")))
                .todo(t1).build();


        t1.setPerson(dev1);
        t2.setPerson(dev1);
        t3.setPerson(dev1);
        t4.setPerson(dev2);

        t1.setAttachments(Arrays.asList(file1, file2, file3));

        todoRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5));


    }
}
