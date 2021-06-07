package bnc.technology.animalplanet.repository;

import java.util.ArrayList;
import java.util.List;

import bnc.technology.animalplanet.R;
import bnc.technology.animalplanet.model.Animal;

public class AnimalRepository {

    private List<Animal> animals;

    public AnimalRepository() {
        getAnimals();
    }

    public List<Animal> getAnimals() {
        if (animals == null) {
            List<Animal> animals = new ArrayList<>();

            Animal animal = new Animal();
            animal.setId(0);
            animal.setNome("Leão");
            animal.setSom(R.raw.leao);
            animal.setOpcoes(new String[]{"Elefante", "Leão", "Macaco"});
            animal.setImagem(R.drawable.leao);
            animal.setImagemCinza(R.drawable.leao_sombra);
            animal.setVideo(R.raw.leao_gif);
            animals.add(animal);

            animal = new Animal();
            animal.setId(1);
            animal.setNome("Cachorro");
            animal.setSom(R.raw.cachorro);
            animal.setOpcoes(new String[]{"Cobra", "Macaco", "Cachorro"});
            animal.setImagem(R.drawable.cachorro);
            animal.setImagemCinza(R.drawable.cachorro_sombra);
            animal.setVideo(R.raw.cachorro_gif);
            animals.add(animal);

            animal = new Animal();
            animal.setId(2);
            animal.setNome("Cavalo");
            animal.setSom(R.raw.cavalo);
            animal.setOpcoes(new String[]{"Ovelha", "Cavalo", "Gato"});
            animal.setImagem(R.drawable.cavalo);
            animal.setImagemCinza(R.drawable.cavalo_sombra);
            animal.setVideo(R.raw.cavalo_gif);
            animals.add(animal);

            animal = new Animal();
            animal.setId(3);
            animal.setNome("Cobra");
            animal.setSom(R.raw.cobra);
            animal.setOpcoes(new String[]{"Cobra", "Leão", "Porco"});
            animal.setImagem(R.drawable.cobra);
            animal.setImagemCinza(R.drawable.cobra_sombra);
            animal.setVideo(R.raw.cobra_gif);
            animals.add(animal);

            animal = new Animal();
            animal.setId(4);
            animal.setNome("Elefante");
            animal.setSom(R.raw.elefante);
            animal.setOpcoes(new String[]{"Cachorro", "Elefante", "Macaco"});
            animal.setImagem(R.drawable.elefante);
            animal.setImagemCinza(R.drawable.elefante_sombra);
            animal.setVideo(R.raw.elefante_gif);
            animals.add(animal);

            animal = new Animal();
            animal.setId(5);
            animal.setNome("Gato");
            animal.setSom(R.raw.gato);
            animal.setOpcoes(new String[]{"Gato", "Leão", "Galinha"});
            animal.setImagem(R.drawable.gato);
            animal.setImagemCinza(R.drawable.gato_sombra);
            animal.setVideo(R.raw.gato_gif);
            animals.add(animal);

            animal = new Animal();
            animal.setId(6);
            animal.setNome("Ovelha");
            animal.setSom(R.raw.ovelha);
            animal.setOpcoes(new String[]{"Pintinho", "Gavião", "Ovelha"});
            animal.setImagem(R.drawable.ovelha);
            animal.setImagemCinza(R.drawable.ovelha_sombra);
            animal.setVideo(R.raw.ovelha_gif);
            animals.add(animal);

            animal = new Animal();
            animal.setId(7);
            animal.setNome("Porco");
            animal.setSom(R.raw.porco);
            animal.setOpcoes(new String[]{"Jacaré", "Porco", "Hipopotámo"});
            animal.setImagem(R.drawable.porco);
            animal.setImagemCinza(R.drawable.porco_sombra);
            animal.setVideo(R.raw.porco_gif);
            animals.add(animal);

            return new ArrayList<>(animals);
        }
        return animals;
    }

    public Animal searchAnimal(int idAnimal) {
        if (animals == null) {
            animals = getAnimals();
        }
        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            if (animal.getId() == idAnimal)
                return animal;
        }
        return null;
    }

    public boolean updateAnimal(Animal animal) {
        for (int i = 0; i < animals.size(); i++) {
            Animal an = animals.get(i);
            if (animal.getId() == an.getId()) {
                animals.set(i, animal);
                return true;
            }
        }
        return false;
    }
}
