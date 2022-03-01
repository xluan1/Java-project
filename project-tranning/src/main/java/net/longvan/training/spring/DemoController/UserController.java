package net.longvan.training.spring.DemoController;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import net.longvan.training.spring.model.User;
import net.longvan.training.spring.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository repository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		LOGGER.info("Đây là hàm xuất tất cả các User");
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable String id) {
		LOGGER.info("Đây là hàm xuất User theo id");
		Optional<User> checkUser = repository.findById(id);
		if (checkUser.isPresent()) {
			return new ResponseEntity<>(checkUser.get(), HttpStatus.OK);
		}
		LOGGER.warn("Không tồn tại user có id là: " + id);
		return new ResponseEntity<>("Không tìm thấy user: " + id, HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody User user) {
		LOGGER.info("Đây là hàm thêm User theo id");
		Optional<User> checkUser = repository.findById(user.getId());
		if (checkUser.isPresent()) {
			LOGGER.warn("User đã tồn tại hãy dùng User khác!");
			return new ResponseEntity<>("User đã tồn tại: " + user.getId(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(repository.save(user), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String id) {
		LOGGER.info("Đây là hàm cập nhật User theo id");
		Optional<User> checkUser = repository.findById(id);
		if (checkUser.isPresent()) {
			User updateUser = checkUser.get();
			updateUser.setAddress(user.getAddress());
			updateUser.setBirthDate(user.getBirthDate());
			updateUser.setDescription(user.getDescription());
			updateUser.setEmail(user.getEmail());
			updateUser.setGender(user.getGender());
			updateUser.setName(user.getName());
			updateUser.setPassword(user.getPassword());
			updateUser.setStatus(user.getStatus());
			LOGGER.warn("Cập nhật thành công!");
			return new ResponseEntity<>(checkUser.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Không tìm thấy user: " + id, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		LOGGER.info("Đây là hàm xóa User theo id");
		Optional<User> checkUser = repository.findById(id);
		if (checkUser.isPresent()) {
			repository.deleteById(checkUser.get().getId());
			LOGGER.warn("Xóa thành công User: " + id);
			return new ResponseEntity<>("Đã xóa thành công user: " + id, HttpStatus.OK);
		}
		return new ResponseEntity<>("Không tìm thấy user: " + id, HttpStatus.NOT_FOUND);
	}

}