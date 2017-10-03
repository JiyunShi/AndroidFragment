package com.example.niceday.fragmentlist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Person thisPerson;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Gson gson=new Gson();

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
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

        View _view = inflater.inflate(R.layout.fragment_detail, container,false);

        TextView myText = (TextView) _view.findViewById(R.id.nameTxt);
        TextView userNameTxt = _view.findViewById(R.id.uNameTxt);
        TextView emailTxt = _view.findViewById(R.id.emailTxt);
        TextView streetTxt = _view.findViewById(R.id.streetTxt);
        TextView suiteTxt = _view.findViewById(R.id.suiteTxt);
        TextView cityTxt = _view.findViewById(R.id.cityTxt);
        TextView zipcodeTxt = _view.findViewById(R.id.zipcodeTxt);
        ImageView selfphoto = _view.findViewById(R.id.selfphoto);

        if (getArguments() != null){
            thisPerson = gson.fromJson(getArguments().getString("datas"), Person.class);
            myText.setText(thisPerson.name);
            emailTxt.setText(thisPerson.email);
            userNameTxt.setText(thisPerson.username);
            streetTxt.setText(thisPerson.address.street);
            suiteTxt.setText(thisPerson.address.suite);
            cityTxt.setText(thisPerson.address.city);
            zipcodeTxt.setText(thisPerson.address.zipcode);
            Picasso.with(_view.getContext()).load(thisPerson.getImg()).fit().placeholder(R.drawable.ic_android_temp)
                    .error(R.drawable.ic_error).into(selfphoto);
        }
        return _view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
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
        void onFragmentInteraction(Uri uri);
    }
}
