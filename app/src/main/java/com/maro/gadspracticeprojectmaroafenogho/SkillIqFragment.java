package com.maro.gadspracticeprojectmaroafenogho;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.ApiClient;
import util.ApiInterface;

public class SkillIqFragment extends Fragment {

    public SkillIqFragment() {}

    TextView errorText, reload;
    ImageView errorImage, refreshIcon;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    List<SkillIq> skillIqList;
    SkillsAdapter skillsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        progressBar = rootView.findViewById(R.id.progressBar);
        errorText = rootView.findViewById(R.id.errorText);
        errorImage = rootView.findViewById(R.id.imageView2);
        reload = rootView.findViewById(R.id.reload);
        refreshIcon = rootView.findViewById(R.id.refresh_icon);

        reload.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            reload.setVisibility(View.GONE);
            errorText.setVisibility(View.GONE);
            errorImage.setVisibility(View.GONE);
            refreshIcon.setVisibility(View.GONE);
            loadData();
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        skillsAdapter = new SkillsAdapter(getContext(), skillIqList);
        skillIqList = new ArrayList<>();

        loadData();

        return rootView;
    }

    private void loadData(){
        ApiInterface apiInterface;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<SkillIq>> call = apiInterface.getSkillIq();
        call.enqueue(new Callback<List<SkillIq>>() {
            @Override
            public void onResponse(Call<List<SkillIq>> call, Response<List<SkillIq>> response) {
                progressBar.setVisibility(View.GONE);
                reload.setVisibility(View.GONE);
                errorText.setVisibility(View.GONE);
                errorImage.setVisibility(View.GONE);
                refreshIcon.setVisibility(View.GONE);
                skillIqList = response.body();
                skillsAdapter.setSkillIqList(skillIqList);
                recyclerView.setAdapter(skillsAdapter);
            }

            @Override
            public void onFailure(Call<List<SkillIq>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                reload.setVisibility(View.VISIBLE);
                errorText.setVisibility(View.VISIBLE);
                errorImage.setVisibility(View.VISIBLE);
                refreshIcon.setVisibility(View.VISIBLE);
                errorImage.setImageResource(R.drawable.ic_baseline_error_outline_24);
                errorText.setText(R.string.connection_error_message);
                refreshIcon.setImageResource(R.drawable.ic_baseline_refresh_24);
                reload.setText(R.string.reload);
                Toast.makeText(getContext(), "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}