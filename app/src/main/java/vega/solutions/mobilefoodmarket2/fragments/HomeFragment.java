package vega.solutions.mobilefoodmarket2.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vega.solutions.mobilefoodmarket2.MobileMarketApp;
import vega.solutions.mobilefoodmarket2.R;
import vega.solutions.mobilefoodmarket2.http.APICallback;
import vega.solutions.mobilefoodmarket2.http.HttpClient;
import vega.solutions.mobilefoodmarket2.http.MobileMarketAPI;
import vega.solutions.mobilefoodmarket2.object.Farm;
import vega.solutions.mobilefoodmarket2.utils.FarmRecyclerAdapter;
import vega.solutions.mobilefoodmarket2.utils.ScreenState;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    TextView theBestMore;
    TextView theClosestMore;
    TextView thePopularMore;

    RecyclerView rvTheBest;
    RecyclerView rvTheClosest;
    RecyclerView rvThePopular;

    FarmRecyclerAdapter farmRecyclerAdapter;
    FarmRecyclerAdapter farmRecyclerAdapter2;
    FarmRecyclerAdapter farmRecyclerAdapter3;


    RelativeLayout scrollviewContentContainer;
    ProgressBar progressBar;

    ArrayList<Farm> farms;

    MobileMarketApp app;
    MobileMarketAPI mobileMarketApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initComponents(view);

        app = MobileMarketApp.getInstance();
        mobileMarketApi = new MobileMarketAPI(new HttpClient());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (app.getFarms() == null) {

            setUpUi(ScreenState.LOADING);

            mobileMarketApi.getFarms(new APICallback<ArrayList<Farm>>() {

                @Override
                public void onCallback(ArrayList<Farm> apiResponse) {
                    farms = apiResponse;
                    app.setFarms(farms);

                    Log.e(TAG, apiResponse.toString());
                    getActivity().runOnUiThread(() -> {
                        updateLists(farms);
                        Log.e(TAG, "UPDATING LIST");
                        setUpUi(ScreenState.DONE);
                    });
                }

                @Override
                public void onFailure(@NotNull Throwable t) {
                    getActivity().runOnUiThread(() -> {
                        setUpUi(ScreenState.ERROR);
                    });
                }
            });

        } else {

            Log.e(TAG, "HERE 2");

            getActivity().runOnUiThread(() -> {
                updateLists(farms);
                setUpUi(ScreenState.DONE);
            });
        }
    }

    private void setUpUi(ScreenState state) {

        switch (state) {
            case LOADING: {
                scrollviewContentContainer.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                progressBar.animate();
            }
            case DONE: {
                scrollviewContentContainer.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
            case ERROR: {
                //scrollviewContentContainer.setVisibility(View.INVISIBLE);
                //progressBar.setVisibility(View.GONE);
            }
        }
    }

    private void initComponents(View view) {
        theBestMore = view.findViewById(R.id.the_best_more);
        rvTheBest = view.findViewById(R.id.rv_the_best);
        scrollviewContentContainer = view.findViewById(R.id.scrollview_content_container);
        progressBar = view.findViewById(R.id.progressBar);
        theClosestMore = view.findViewById(R.id.the_closest_more);
        rvTheClosest = view.findViewById(R.id.rv_the_closest);
        thePopularMore = view.findViewById(R.id.the_popular_more);
        rvThePopular = view.findViewById(R.id.rv_the_popular);

        theBestMore.setOnClickListener(view1 -> {
            Toast.makeText(getActivity(), "Dragonhack traja 24 ur, kako lahko toliko od nas pričakuješ? :)",
                    Toast.LENGTH_LONG).show();

        });
        theClosestMore.setOnClickListener(view1 -> {
            Toast.makeText(getActivity(), "Dragonhack traja 24 ur, kako lahko toliko od nas pričakuješ? :)",
                    Toast.LENGTH_LONG).show();
        });

        thePopularMore.setOnClickListener(view1 -> {
            Toast.makeText(getActivity(), "Dragonhack traja 24 ur, kako lahko toliko od nas pričakuješ? :)",
                    Toast.LENGTH_LONG).show();
        });
        initList();
    }

    private void initList() {
        rvTheBest.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTheBest.setLayoutManager(layoutManager);
        rvTheBest.setNestedScrollingEnabled(false);

        farmRecyclerAdapter = new FarmRecyclerAdapter();
        farmRecyclerAdapter.list = null;
        farmRecyclerAdapter.context = getContext();
        rvTheBest.setAdapter(farmRecyclerAdapter);

        rvTheClosest.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTheClosest.setLayoutManager(layoutManager2);
        rvTheClosest.setNestedScrollingEnabled(false);

        farmRecyclerAdapter2 = new FarmRecyclerAdapter();
        farmRecyclerAdapter2.list = null;
        farmRecyclerAdapter2.context = getContext();
        rvTheClosest.setAdapter(farmRecyclerAdapter2);

        rvThePopular.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvThePopular.setLayoutManager(layoutManager3);
        rvThePopular.setNestedScrollingEnabled(false);

        farmRecyclerAdapter3 = new FarmRecyclerAdapter();
        farmRecyclerAdapter3.list = null;
        farmRecyclerAdapter3.context = getContext();
        rvThePopular.setAdapter(farmRecyclerAdapter3);
    }

    private void updateLists(List items) {
        Collections.swap(items, 0, 3);
        Collections.swap(items, 3, 2);
        farmRecyclerAdapter.list = items;
        farmRecyclerAdapter.notifyDataSetChanged();

        ArrayList<Farm> items2 = new ArrayList<>(items);
        Collections.swap(items2, 0, 2);
        Collections.swap(items2, 1, 3);
        Collections.swap(items2, 2, 4);

        farmRecyclerAdapter2.list = items2;
        farmRecyclerAdapter2.notifyDataSetChanged();

        ArrayList<Farm> items3 = new ArrayList<>(items);
        Collections.swap(items3, 0, 4);

        farmRecyclerAdapter3.list = items3;
        farmRecyclerAdapter3.notifyDataSetChanged();
    }

}