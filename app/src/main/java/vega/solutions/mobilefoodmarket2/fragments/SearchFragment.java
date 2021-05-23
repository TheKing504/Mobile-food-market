package vega.solutions.mobilefoodmarket2.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import vega.solutions.mobilefoodmarket2.MobileMarketApp;
import vega.solutions.mobilefoodmarket2.R;
import vega.solutions.mobilefoodmarket2.http.HttpClient;
import vega.solutions.mobilefoodmarket2.http.MobileMarketAPI;
import vega.solutions.mobilefoodmarket2.object.Farm;
import vega.solutions.mobilefoodmarket2.utils.FarmRecyclerAdapter;
import vega.solutions.mobilefoodmarket2.utils.SearchtemsRecyclerAdapter;

public class SearchFragment extends Fragment {

    EditText search;
    RecyclerView rv;

    SearchtemsRecyclerAdapter searchtemsRecyclerAdapter;

    MobileMarketApp app;
    MobileMarketAPI mobileMarketApi;

    ArrayList<Farm> farms;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initComponents(view);

        app = MobileMarketApp.getInstance();
        mobileMarketApi = new MobileMarketAPI(new HttpClient());

        farms = app.getFarms();

        updateList(farms);

        return view;
    }

    private void initComponents(View v) {
        search = v.findViewById(R.id.search);
        rv = v.findViewById(R.id.search_rv);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() == 0) {
                    updateList(farms);
                    return;
                }

                ArrayList<Farm> filteredFarms = new ArrayList<>();

                for (Farm farm : farms) {
                    if (farm.getName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredFarms.add(farm);
                    }
                }
                getActivity().runOnUiThread(() -> {
                    updateList(filteredFarms);
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        initList();
    }

    private void initList() {
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setNestedScrollingEnabled(false);

        searchtemsRecyclerAdapter = new SearchtemsRecyclerAdapter();
        searchtemsRecyclerAdapter.list = null;
        searchtemsRecyclerAdapter.context = getContext();
        rv.setAdapter(searchtemsRecyclerAdapter);
    }

    private void updateList(List<Farm> items) {
        searchtemsRecyclerAdapter.list = items;
        searchtemsRecyclerAdapter.notifyDataSetChanged();
    }
}