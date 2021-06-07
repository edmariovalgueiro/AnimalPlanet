package bnc.technology.animalplanet.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import bnc.technology.animalplanet.model.Animal;
import bnc.technology.animalplanet.ui.AnimalItemFragment;

public class AdapterAnimalsViewPager extends FragmentStateAdapter {

    private List<Animal> animals;

    public AdapterAnimalsViewPager(@NonNull final Fragment fragment, List<Animal> animals) {
        super(fragment);
        this.animals = animals;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Animal animal = animals.get(position);
        return AnimalItemFragment.newInstance(animal.getId());
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }
}