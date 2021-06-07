package bnc.technology.animalplanet.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import bnc.technology.animalplanet.R;
import bnc.technology.animalplanet.adapter.AdapterAnimalsViewPager;
import bnc.technology.animalplanet.model.Animal;
import bnc.technology.animalplanet.viewmodel.AnimalViewModel;

public class AnimalsFragment extends Fragment {

    private AnimalViewModel animalViewModel;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;

    public AnimalsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animalViewModel = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_animals, container, false);

        AdapterAnimalsViewPager adapterAnimalsViewPager = new AdapterAnimalsViewPager(AnimalsFragment.this, animalViewModel.getAnimals());
        viewPager2 = root.findViewById(R.id.viewPager2);
        viewPager2.setAdapter(adapterAnimalsViewPager);

        tabLayout = root.findViewById(R.id.tabLayout);
        TabLayoutMediator.TabConfigurationStrategy tabConfigurationStrategy = new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        };
        new TabLayoutMediator(tabLayout, viewPager2, true, tabConfigurationStrategy).attach();

        return root;
    }
}