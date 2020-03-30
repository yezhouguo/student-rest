package ye.studentrest;

import org.springframework.data.repository.CrudRepository;


interface StudentRepository extends CrudRepository<Student, Long> 
{
}
