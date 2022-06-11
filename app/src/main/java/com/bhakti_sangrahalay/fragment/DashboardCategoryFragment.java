package com.bhakti_sangrahalay.fragment;


import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.adapter.CategoryAdapter1;
import com.bhakti_sangrahalay.adapter.CategoryAdapter2;
import com.bhakti_sangrahalay.adapter.CategoryAdapter3;
import com.bhakti_sangrahalay.contansts.GlobalVariables;

import java.util.ArrayList;

public class DashboardCategoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager RecyclerViewLayoutManager;
    private CategoryAdapter1 adapter1;
    private CategoryAdapter2 adapter2;
    private CategoryAdapter3 adapter3;
    private LinearLayoutManager HorizontalLayout;
    private Resources resources;


    private Activity activity;
    private int category;

    public static DashboardCategoryFragment newInstance(int category) {
        Bundle args = new Bundle();
        DashboardCategoryFragment fragment = new DashboardCategoryFragment();
        args.putInt("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getInt("category");
        }
        resources = getResources();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerViewLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        HorizontalLayout = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        getName();
        getImages();
        if (category == 1) {
            ArrayList<String> source = getName();
            ArrayList<Integer> imagesSource = getImages();
            ArrayList<Integer> rowFile = getRowFile();
            adapter1 = new CategoryAdapter1(activity, source, imagesSource, rowFile, GlobalVariables.aarti);
            recyclerView.setAdapter(adapter1);
        } else if (category == 3) {
            ArrayList<String> source = getNameForOther();
            ArrayList<Integer> imagesSource = getImagesForOther();
            ArrayList<Integer> rowFile = getRowFileForChalisha();
            adapter3 = new CategoryAdapter3(activity, source, imagesSource, rowFile, GlobalVariables.chalisha);
            recyclerView.setAdapter(adapter3);
        } else {
            ArrayList<String> source = getNameForChalisha();
            ArrayList<Integer> imagesSource = getImagesForChalisha();
            ArrayList<Integer> rowFile = getRowFileForChalisha();
            adapter2 = new CategoryAdapter2(activity, source, imagesSource, rowFile, GlobalVariables.chalisha);
            recyclerView.setAdapter(adapter2);
        }

        return view;
    }

    public ArrayList<String> getName() {
        ArrayList<String> source = new ArrayList<>();
        source.add(resources.getString(R.string.ganesh_ji));
        source.add(resources.getString(R.string.hanuman_ji));
        source.add(resources.getString(R.string.durga_ji));
        source.add(resources.getString(R.string.shiv_ji));
        //source.add(resources.getString(R.string.other));

        return source;
    }

    public ArrayList<Integer> getImages() {
        ArrayList<Integer> imagesSource = new ArrayList<>();
        imagesSource.add(R.drawable.ganeshji_aarti_image);
        imagesSource.add(R.drawable.hanumaanji_aarti_image);
        imagesSource.add(R.drawable.durgaji_aarti_image);
        imagesSource.add(R.drawable.shivji_aarti_image);
        //imagesSource.add(R.drawable.others1);
        return imagesSource;
    }

    public ArrayList<Integer> getRowFile() {
        ArrayList<Integer> imagesSource = new ArrayList<>();
        imagesSource.add(R.raw.ganeshji_ki_aarti);
        imagesSource.add(R.raw.hanumanji_ki_aarti);
        imagesSource.add(R.raw.durgaji_ki_aarti);
        imagesSource.add(R.raw.shivji_ki_aarti);
        //imagesSource.add(GlobalVariables.OTHERS);
        return imagesSource;
    }

    public ArrayList<String> getNameForChalisha() {
        ArrayList<String> source = new ArrayList<>();
        source.add(resources.getString(R.string.ganesh_cahlisa));
        source.add(resources.getString(R.string.hanuman_cahlisa));
        source.add(resources.getString(R.string.durga_cahlisa));
        source.add(resources.getString(R.string.shiv_cahlisa));
        //source.add(resources.getString(R.string.other));
        return source;
    }

    public ArrayList<Integer> getImagesForChalisha() {
        ArrayList<Integer> imagesSource = new ArrayList<>();
        imagesSource.add(R.drawable.ganeshji_chalisha);
        imagesSource.add(R.drawable.hanuman_chalisha);
        imagesSource.add(R.drawable.durga_chalisha);
        imagesSource.add(R.drawable.shiv_chalisha);
        //imagesSource.add(R.drawable.others1);
        return imagesSource;
    }

    public ArrayList<Integer> getRowFileForChalisha() {
        ArrayList<Integer> imagesSource = new ArrayList<>();
        imagesSource.add(R.raw.ganesh_chalisha);
        imagesSource.add(R.raw.hanuman_chalisha);
        imagesSource.add(R.raw.durga_chalisha);
        imagesSource.add(R.raw.shiv_chalisha);
        //imagesSource.add(GlobalVariables.OTHERS);
        return imagesSource;
    }

    public ArrayList<String> getNameForOther() {
        ArrayList<String> source = new ArrayList<>();
        source.add(resources.getString(R.string.vart_kathayen));
        source.add(resources.getString(R.string.pauranik_katha));
        source.add(resources.getString(R.string.mantra_sangrah));

        return source;
    }

    public ArrayList<Integer> getImagesForOther() {
        ArrayList<Integer> imagesSource = new ArrayList<>();
        imagesSource.add(R.drawable.kalash);
        imagesSource.add(R.drawable.dharmik_katha_icon);
        imagesSource.add(R.drawable.mantra_image);

        return imagesSource;
    }
}
