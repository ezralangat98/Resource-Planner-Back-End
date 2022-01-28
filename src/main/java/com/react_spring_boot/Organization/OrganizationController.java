package com.react_spring_boot.Organization;

import com.react_spring_boot.Exception.ResourceNotFoundException;
import com.react_spring_boot.Exception.ResourceNotFoundExceptionOrg;
import com.react_spring_boot.User.User;
import com.react_spring_boot.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class OrganizationController {

    @Autowired
    private OrganizationRepository organizationRepository;

    /** READ or LIST*/
    @GetMapping("/organizations")
    public List<Organization> getAllOrganizations(){

        return organizationRepository.findAll();
    }

    /** Find By ID */
    @GetMapping("/organizations/{organizationId}")
    public ResponseEntity<Organization> getOrganizationById(
            @PathVariable(value = "organizationId")
            Integer organizationId) throws ResourceNotFoundException {

        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found for this id :: " + organizationId));

        return ResponseEntity.ok(organization);
    }

    /** CREATE / SAVE  */
    @PostMapping("/organizations")
    public Organization createOrg(@RequestBody Organization organization) {
        return organizationRepository.save(organization);
    }

    /** UPDATE */
    @PutMapping("/organizations/{organizationId}")
    public ResponseEntity<Organization> updateOrg(@PathVariable(value = "organizationId") Integer organizationId,
                                                  @RequestBody Organization organizationDetails) {

        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found for this id :: " + organizationId));

        organization.setOrganizationName(organizationDetails.getOrganizationName());

        Organization updatedOrg = organizationRepository.save(organization);
        return ResponseEntity.ok(updatedOrg);
    }

    /** DELETE */
    @DeleteMapping("/organizations/{organizationId}")
    public Map<String, Boolean> deleteOrg(@PathVariable(value = "organizationId") Integer organizationId)
            throws ResourceNotFoundException {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + organizationId));

        organizationRepository.delete(organization);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    /** Finding Organization's Users */
    @GetMapping("/organizations/{organizationId}/users")
    public List<User> getUsersInOrganization(@PathVariable int organizationId){
        Optional<Organization> organization = organizationRepository.findById(organizationId);

        List<User> users = organization.get().getUsers();
        return users;
    }

}




//http://localhost:8080/api/v1/user/1/organization