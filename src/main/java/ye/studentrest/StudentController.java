package ye.studentrest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
class StudentController {

    private final StudentRepository repository;

    StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/students")
    ResponseEntity<CollectionModel<EntityModel<Student>>> findAll() {

        List<EntityModel<Student>> students = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(student -> new EntityModel<>(student, //
                        linkTo(methodOn(StudentController.class).findOne(student.getId())).withSelfRel(), //
                        linkTo(methodOn(StudentController.class).findAll()).withRel("students"))) //
                .collect(Collectors.toList());

        return ResponseEntity.ok( //
                new CollectionModel<>(students, //
                        linkTo(methodOn(StudentController.class).findAll()).withSelfRel()));
    }

    @PostMapping("/students")
    ResponseEntity<?> newStudent(@RequestBody Student student) {

        try {

            Student savedStudent = repository.save(student);

            EntityModel<Student> studentResource = new EntityModel<>(savedStudent, //
                    linkTo(methodOn(StudentController.class).findOne(savedStudent.getId())).withSelfRel());

            return ResponseEntity //
                    .created(new URI(studentResource.getRequiredLink(IanaLinkRelations.SELF).getHref())) //
                    .body(studentResource);
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to create " + student);
        }
    }

    @GetMapping("/students/{id}")
    ResponseEntity<EntityModel<Student>> findOne(@PathVariable long id) {

        return repository.findById(id) //
                .map(student -> new EntityModel<>(student, //
                        linkTo(methodOn(StudentController.class).findOne(student.getId())).withSelfRel(), //
                        linkTo(methodOn(StudentController.class).findAll()).withRel("students"))) //
                .map(ResponseEntity::ok) //
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/students/{id}")
    ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable long id) {
        Student studentToUpdate = student;
        studentToUpdate.setId(id);
        repository.save(studentToUpdate);

        Link newlyCreatedLink = linkTo(methodOn(StudentController.class).findOne(id)).withSelfRel();

        try {
            return ResponseEntity.noContent().location(new URI(newlyCreatedLink.getHref())).build();
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to update " + studentToUpdate);
        }
    }

    @DeleteMapping("/students/{id}")
    ResponseEntity<?> deleteStudent(@PathVariable long id) {
        
        
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}