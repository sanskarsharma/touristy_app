package com.technovate18.sanskar.touristy.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technovate18.sanskar.touristy.R;
import com.technovate18.sanskar.touristy.expandableLV.GenreAdapter;

import static com.technovate18.sanskar.touristy.expandableLV.GenreDataFactory.makeClassicGenre;
import static com.technovate18.sanskar.touristy.expandableLV.GenreDataFactory.makeGenres;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DestinationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class DestinationsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public DestinationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DestinationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DestinationsFragment newInstance(String param1, String param2) {
        DestinationsFragment fragment = new DestinationsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_destinations, container, false);
    }

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    GenreAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_expandable);
         layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        adapter = new GenreAdapter(makeGenres());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        TextView clear = (TextView) view.findViewById(R.id.destinations_textview);
        String texty = "The state has various old historic spots. It is one of the fastest developing states in india . Located in central india, is leading producer of minerals such as coal, iron ore , dolamite etc. ";

        clear.setText(texty);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.toggleGroup(makeClassicGenre());
            }
        });


    }



}
