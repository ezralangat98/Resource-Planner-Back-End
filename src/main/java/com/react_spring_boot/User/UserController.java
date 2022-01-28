package com.react_spring_boot.User;

import com.react_spring_boot.Exception.ResourceNotFoundException;
import com.react_spring_boot.Organization.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /** READ or LIST*/
    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /** READ / Find By ID */
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + id));
        return ResponseEntity.ok().body(user);
    }

    /** CREATE / SAVE  */
    // @RequestBody Uses http message convertors to convert json message into java object
    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    /** UPDATE */
    // @PathVariable is used in Binding url value to method parameter value
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Integer id,
                                           @RequestBody User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + id));

        user.setEmailId(userDetails.getEmailId());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        user.setPhoneNo(userDetails.getPhoneNo());
        user.setGender(userDetails.getGender());

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    /** DELETE */
    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser1(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    /** Finding User's Organization */
    @GetMapping("/user/{id}/organization")
    public Organization getUsersOrganization(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        return user.get().getOrganization();
    }
}

