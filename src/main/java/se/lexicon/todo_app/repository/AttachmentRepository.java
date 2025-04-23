package se.lexicon.todo_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.todo_app.entity.Attachment;

public interface AttachmentRepository extends CrudRepository<Attachment, Long> {
}
