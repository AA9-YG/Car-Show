package com.binaryAA.CarShow.service;

import com.binaryAA.CarShow.entity.Owner;
import com.binaryAA.CarShow.entity.Owner;

import java.util.List;

public interface OwnerService {

    List<Owner> getOwners();

    Owner getOwnerById(Long id);

    Owner saveOwner(Owner owner);

    Owner updateOwner(Long id, Owner owner);

    Owner deleteOwner(Long id);
}
