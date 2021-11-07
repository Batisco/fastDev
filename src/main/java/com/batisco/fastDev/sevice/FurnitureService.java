package com.batisco.fastDev.sevice;

import com.batisco.fastDev.dal.FurnitureRepository;
import com.batisco.fastDev.model.Furniture;
import com.batisco.fastDev.model.exceptions.NotUniqueFurnitureException;
import com.batisco.fastDev.model.exceptions.UnknownFurnitureException;
import com.batisco.fastDev.model.exceptions.UnknownUserException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FurnitureService {

    private final FurnitureRepository furnitureRepository;

    @Autowired
    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Transactional(readOnly = true)
    public Page<Furniture> getAll(Pageable pageable,
                                  Optional<String> hasOwner,
                                  Optional<List<UUID>> exclude) {
        return furnitureRepository.getByFilter(pageable, hasOwner, exclude);
    }

    @Transactional(readOnly = true)
    public Furniture getById(UUID furnitureId) {
        if(furnitureId == null)
            throw new UnknownFurnitureException("Unknown furniture with id = null");

        return furnitureRepository.getById(furnitureId).
                orElseThrow(() -> new UnknownFurnitureException("Unknown furniture with id = " + furnitureId));
    }

    @Transactional
    public Furniture add(Furniture furnitureWithoutId) {
        try {
            furnitureWithoutId.setId(UUID.randomUUID());

            furnitureRepository.add(furnitureWithoutId);
            furnitureRepository.flush();

            return furnitureWithoutId;
        } catch(DataIntegrityViolationException e) {
            throw new NotUniqueFurnitureException(
                    "Furniture with id " + furnitureWithoutId.getId() + " already in another apartment"
            );
        }
    }

    @Transactional
    public Furniture update(Furniture furniture) {
        try {
            if(furnitureRepository.existsById(furniture.getId())) {
                furnitureRepository.update(furniture);
                furnitureRepository.flush();
                return furniture;
            }
            throw new UnknownUserException("Unknown furniture with id = " + furniture.getId());
        } catch(DataIntegrityViolationException e) {
            throw new NotUniqueFurnitureException(
                    "Furniture with id " + furniture.getId() + " already in another apartment"
            );
        }
    }

}
