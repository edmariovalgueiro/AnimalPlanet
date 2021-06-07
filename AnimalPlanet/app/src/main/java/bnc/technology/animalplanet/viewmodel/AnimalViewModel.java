package bnc.technology.animalplanet.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.List;

import bnc.technology.animalplanet.model.Animal;
import bnc.technology.animalplanet.repository.AnimalRepository;

public class AnimalViewModel extends ViewModel {

    private AnimalRepository animalRepository;

    public AnimalViewModel() {
        animalRepository = new AnimalRepository();
    }

    public List<Animal> getAnimals() {
        return animalRepository.getAnimals();
    }

    public Animal searchAnimal(int id) {
        return animalRepository.searchAnimal(id);
    }

    public boolean updateAnimal(Animal animal) {
        return animalRepository.updateAnimal(animal);
    }
}
