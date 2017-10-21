package com.example.niceday.fragmentlist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String datas;
    Gson gson = new Gson();
    private PersonList personList = new PersonList();
    private OnFragmentInteractionListener mListener;
    private LinearLayout rootMenu;
    private ArrayList<TextView> menuTexts = new ArrayList<TextView>();
    private String[] faceImgs ={
            "https://facedetection.com/wp-content/uploads/m01-32_gr.jpg",
            "https://facedetection.com/wp-content/uploads/w01-64_gr.jpg",
            "http://dreamicus.com/data/face/face-07.jpg",
            "http://ksassets.timeincuk.net/wp/uploads/sites/46/2016/09/beauty-natural-make-up-model-backstage-920x518.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-j6qiz5FCzONE2lksbmflHyB7XZHTzrZiG0L43rH9RQUw0A4S_w",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS5X_WHC76qeM4AktJoNuHBxXZhAUw4KdWNVJ1vxklc0T6Cxmgefw",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrnX_qVdYQsabmxytonuE3S7NVN9CTmOmC9UWREIfQg1lpZ25Z",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS5iImG2Q-vkbXnr8EP3TycTy6nF57S5efk4JNnBhlq1UsrpuD0Wg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKfud266XJjsgU1QY6lkkegTL8EMREpz2weqxByb9lu56Vq1M4",
            "https://i.pinimg.com/736x/3e/54/cc/3e54cc9c45c0f62d8de9d68224626817--girl-face-woman-face.jpg"
    };


    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
            datas = getArguments().getString("datas");
            personList = gson.fromJson(datas, PersonList.class);
            //add fictitious image for each person
            this.addFaceImage();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_menu, container,false);
        rootMenu = _view.findViewById(R.id.rootMenu);
        nameListSetup();
        return _view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String person) {
        if (mListener != null) {
            mListener.onFragmentInteraction(person);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            //attach mListener to MainActivity
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        for(int i=0; i<menuTexts.size();i++){

            if(view.getId()==menuTexts.get(i).getId()){
                menuTexts.get(i).setBackgroundColor(0xFF00FF00);
                Person thisPerson = personList.getPersonList().get(i);
                this.onButtonPressed(gson.toJson(thisPerson, Person.class));


            }
            else{
                menuTexts.get(i).setBackgroundColor(0xFFFFFFFF);
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String person);
    }

    public void addFaceImage(){
        for(int i=0; i< personList.getPersonList().size();i++){
            personList.getPersonList().get(i).setImg(faceImgs[i]);
        }
    }


    public void nameListSetup(){

        for(int i=0; i< personList.getPersonList().size();i++){
            addNameItem(personList.getPersonList().get(i).name);

        }

    }
    public void addNameItem(String name){

        View sepView = new View(getContext());
        sepView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 1));
        sepView.setBackgroundColor(0xFF00FF00);

        TextView newText = new TextView(getContext());
        newText.setId(View.generateViewId());
        newText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 60));
        newText.setTextSize(20);
        newText.setGravity(Gravity.CENTER);
        newText.setText(name);
        newText.setOnClickListener(this);
        menuTexts.add(newText);
        rootMenu.addView(sepView);
        rootMenu.addView(newText);

    }


}
