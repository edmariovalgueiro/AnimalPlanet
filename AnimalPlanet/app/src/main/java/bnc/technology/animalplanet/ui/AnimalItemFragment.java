package bnc.technology.animalplanet.ui;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

import bnc.technology.animalplanet.R;
import bnc.technology.animalplanet.model.Animal;
import bnc.technology.animalplanet.viewmodel.AnimalViewModel;

public class AnimalItemFragment extends Fragment {

    private AnimalViewModel animalViewModel;
    private Animal animal;
    private MediaPlayer mediaPlayer;
    private ViewGroup container;
    private View root;
    private LinearLayout linearLayout;
    private ImageView imageView;
    private VideoView videoView;
    private ImageButton imageButton;
    private CheckBox checkBoxOpcao1, checkBoxOpcao2, checkBoxOpcao3;

    public AnimalItemFragment() {}

    public static AnimalItemFragment newInstance(int idAnimal) {
        AnimalItemFragment fragment = new AnimalItemFragment();
        Bundle args = new Bundle();
        args.putInt("id", idAnimal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animalViewModel = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_animal_item, container, false);
        this.container = container;

        int idAnimal = getArguments().getInt("id");
        animal = animalViewModel.searchAnimal(idAnimal);

        identifyComponents();
        buildInitialView();

        checkBoxOpcao1.setText( animal.getOpcoes()[0] );
        checkBoxOpcao2.setText( animal.getOpcoes()[1] );
        checkBoxOpcao3.setText( animal.getOpcoes()[2] );

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(requireContext(), animal.getSom());
                    mediaPlayer.start();
                }

                if ((mediaPlayer != null) && (!mediaPlayer.isPlaying())) {
                    mediaPlayer = MediaPlayer.create(requireContext(), animal.getSom());
                    mediaPlayer.start();
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (animal.isCanShowCorrectAnswer()) {
                    String path = "android.resource://" + requireActivity().getPackageName() + "/" + animal.getVideo();
                    videoView.setVideoURI(Uri.parse(path));

                    imageView.setVisibility(View.GONE);
                    videoView.setVisibility(View.VISIBLE);
                    videoView.start();
                }
            }
        });

        return root;
    }

    private void identifyComponents() {
        imageView = root.findViewById(R.id.imageView);
        imageButton = root.findViewById(R.id.imageButton);
        checkBoxOpcao1 = root.findViewById(R.id.checkBoxOpcao1);
        checkBoxOpcao2 = root.findViewById(R.id.checkBoxOpcao2);
        checkBoxOpcao3 = root.findViewById(R.id.checkBoxOpcao3);
        linearLayout = root.findViewById(R.id.linearLayout);
        videoView = root.findViewById(R.id.videoView);
    }

    private void buildInitialView() {
        if (animal.isCanShowCorrectAnswer()) {
            imageView.setImageResource(animal.getImagem());
            checkBoxOpcao1.setClickable(false);
            checkBoxOpcao2.setClickable(false);
            checkBoxOpcao3.setClickable(false);

            for (int i = 0; i < animal.getOpcoes().length; i++) {
                String opcao = animal.getOpcoes()[i];

                if (opcao.equalsIgnoreCase(animal.getNome())) {
                    switch (i) {
                        case 0:
                            updateCheckBoxState(checkBoxOpcao1);
                            break;

                        case 1:
                            updateCheckBoxState(checkBoxOpcao2);
                            break;

                        case 2:
                            updateCheckBoxState(checkBoxOpcao3);
                            break;
                    }
                    break;
                }
            }
        }
        else {
            imageView.setImageResource(animal.getImagemCinza());

            checkBoxOpcao1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(checkBoxOpcao1);
                }
            });
            checkBoxOpcao2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(checkBoxOpcao2);
                }
            });
            checkBoxOpcao3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(checkBoxOpcao3);
                }
            });
        }
    }

    private void checkAnswer(CheckBox checkBox) {
        clearCheckBoxes();
        CheckBox cb = root.findViewById(checkBox.getId());
        cb.setChecked(true);

        Resources resources = getResources();
        if (cb.getText().toString().equalsIgnoreCase( animal.getNome() )) {
            cb.setBackgroundColor(resources.getColor(android.R.color.holo_green_light));
            imageView.setImageResource(animal.getImagem());

            checkBoxOpcao1.setClickable(false);
            checkBoxOpcao2.setClickable(false);
            checkBoxOpcao3.setClickable(false);

            animal.setCanShowCorrectAnswer(true);
            animalViewModel.updateAnimal(animal);

            if (animalsAreChecked()) {
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(requireContext());
                dialog.setCancelable(true);

                View view = LayoutInflater.from(requireContext()).inflate(R.layout.alert_dialog_custom, container, false);
                VideoView videoView = view.findViewById(R.id.videoView2);

                String path = "android.resource://" + requireActivity().getPackageName() + "/" + R.raw.conquista;
                videoView.setVideoURI(Uri.parse(path));
                dialog.setView(view);
                createThread(dialog, videoView);
            }
        }
        else
            cb.setBackgroundColor(resources.getColor(android.R.color.holo_red_light));

        cb.setTextColor(resources.getColor(android.R.color.white));
    }

    private void clearCheckBoxes() {
       checkBoxOpcao1.setChecked(false);
       checkBoxOpcao2.setChecked(false);
       checkBoxOpcao3.setChecked(false);

       Resources resources = getResources();
       checkBoxOpcao1.setBackgroundColor(resources.getColor(R.color.white));
       checkBoxOpcao2.setBackgroundColor(resources.getColor(R.color.white));
       checkBoxOpcao3.setBackgroundColor(resources.getColor(R.color.white));
       checkBoxOpcao1.setTextColor(resources.getColor(R.color.black));
       checkBoxOpcao2.setTextColor(resources.getColor(R.color.black));
       checkBoxOpcao3.setTextColor(resources.getColor(R.color.black));
    }

    private void updateCheckBoxState(CheckBox checkBox) {
        Resources resources = getResources();
        checkBox.setBackgroundColor(resources.getColor(android.R.color.holo_green_light));
        checkBox.setTextColor(resources.getColor(android.R.color.white));
        checkBox.setChecked(true);
    }

    private boolean animalsAreChecked() {
        List<Animal> animals = animalViewModel.getAnimals();
        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            if (!animal.isCanShowCorrectAnswer())
                return false;
        }
        return true;
    }

    private void createThread(MaterialAlertDialogBuilder dialog, VideoView videoView) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dialog.show();
                videoView.start();
            }
        }, 4000);
    }

    @Override
    public void onResume() {
        if (videoView.getVisibility() == View.VISIBLE) {
            videoView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.stop();

        super.onDestroyView();
    }
}