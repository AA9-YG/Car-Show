package com.binaryAA.CarShow.service;

import com.binaryAA.CarShow.entity.Owner;
import com.binaryAA.CarShow.exception.ResourceNotFoundException;
import com.binaryAA.CarShow.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepo;

    @Override
    public List<Owner> getOwners() {
        return (List<Owner>) ownerRepo.findAll();
    }

    @Override
    public Owner getOwnerById(Long id) {
        Owner owner = ownerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find owner with id:" + id));
        return owner;
    }

    @Override
    public Owner saveOwner(Owner owner) {
        return ownerRepo.save(owner);
    }

    @Override
    public Owner updateOwner(Long id, Owner owner) {
        Owner existingOwner = ownerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find owner by id: " + id));

        existingOwner.setFirstName(owner.getFirstName());
        existingOwner.setLastName(owner.getLastName());

        return ownerRepo.save(existingOwner);
    }

    @Override
    public Owner deleteOwner(Long id) {
        Owner existingOwner = ownerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find owner by id: " + id));

        ownerRepo.delete(existingOwner);

        return existingOwner;
    }
}
