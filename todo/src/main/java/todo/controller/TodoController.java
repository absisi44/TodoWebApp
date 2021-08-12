package todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import todo.model.Todo;
import todo.repositories.TodoRepository;

@RestController
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	TodoRepository todorepository;

	@GetMapping("/findall")
	public List<Todo> fetchAll() {

		return this.todorepository.findAll();
	}

	@PostMapping("/todoadd")

	public ResponseEntity<?> addtodo(@RequestBody Todo todo) {

		return new ResponseEntity<>(this.todorepository.save(todo), HttpStatus.CREATED);
	}

	@DeleteMapping("/deletetodo/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable Integer id) {

		if (this.todorepository.findById(id).isPresent()) {

			this.todorepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/udatetodo/{id}")
	public ResponseEntity<?> updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {

		if (this.todorepository.findById(id).isPresent()) {
			todo.setId(id);
			return new ResponseEntity<>(this.todorepository.save(todo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
