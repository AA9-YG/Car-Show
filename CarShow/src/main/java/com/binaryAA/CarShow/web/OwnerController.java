package com.binaryAA.CarShow.web;

import com.binaryAA.CarShow.entity.Owner;

import com.binaryAA.CarShow.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;


    @GetMapping("/all")
    public ResponseEntity<List<Owner>> getOwners() {
        return new ResponseEntity<>(ownerService.getOwners(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable Long id) {
        return new ResponseEntity<>(ownerService.getOwnerById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Owner> saveOwner(@RequestBody Owner owner) {
        return new ResponseEntity<>(ownerService.saveOwner(owner), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
        return new ResponseEntity<>(ownerService.updateOwner(id, owner), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Owner> deleteOwner(@PathVariable Long id) {
        return new ResponseEntity<>(ownerService.deleteOwner(id), HttpStatus.NO_CONTENT);
    }
}
