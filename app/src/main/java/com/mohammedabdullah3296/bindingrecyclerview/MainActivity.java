package com.mohammedabdullah3296.bindingrecyclerview;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mohammedabdullah3296.bindingrecyclerview.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

////    UsersAdapter adapter ;
//    RecyclerView recyclerView ;


    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        recyclerView = findViewById(R.id.dd);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) ;
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        UserViewModel model = ViewModelProviders.of(this).get(UserViewModel.class);
//
//        model.getUsers().observe(this, new Observer<List<User>>() {
//            @Override
//            public void onChanged(@Nullable List<User> heroList) {
//
//                adapter = new UsersAdapter();
//                adapter.setCallData(heroList , MainActivity.this);
//                recyclerView.setAdapter(adapter);
//            }
//        });
//
                 setupBindings(savedInstanceState);
    }


    private void setupBindings(Bundle savedInstanceState) {
        ActivityMainBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        activityBinding.setModel(viewModel);
        setupListUpdate();

    }


    private void setupListUpdate() {
         viewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> dogBreeds) {
                 if (dogBreeds.size() == 0) {
                 } else {
                     viewModel.setDogBreedsInAdapter(dogBreeds);
                }
            }
        });

    }


}
