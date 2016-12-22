package ru.csu.profcom;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.zip.Inflater;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.csu.profcom.retrofit.User;
import ru.csu.profcom.retrofit.UserAPI;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPersonalArea.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPersonalArea#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPersonalArea extends Fragment {
    private UserInfoStorage userInfoStorage;

    private static final int PICKFILE_RESULT_CODE = 1;
    private static final int RESULT_OK = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton photoLoaderButton;
    private Button changePasswordButton;

    private OnFragmentInteractionListener mListener;

    public FragmentPersonalArea() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPersonalArea.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPersonalArea newInstance(String param1, String param2) {
        FragmentPersonalArea fragment = new FragmentPersonalArea();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_personal_area, container, false);
        userInfoStorage = new UserInfoStorage(getActivity());

        photoLoaderButton = (ImageButton) inflate.findViewById(R.id.photoLoadButton);
        photoLoaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(webIntent);
            }
        });


        Retrofit client = new Retrofit.Builder()
                .baseUrl(HttpUrl.parse(userInfoStorage.getRetrofitServer()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserAPI service = client.create(UserAPI.class);
        UserInfoStorage userInfoStorage = new UserInfoStorage(getActivity());
        if (userInfoStorage.isLogin()) {
            Call<User> user = service.getUser(Long.valueOf(userInfoStorage.getUsedID()));
            user.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        CheckBox feePay = (CheckBox)inflate.findViewById(R.id.feePayCheckBox);
                        if (response.body().getFeePay())
                            feePay.setChecked(true);
                        else
                            feePay.setChecked(false);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getContext(), "Не удалось получить данные о вашем профиле", Toast.LENGTH_LONG).show();
                }
            });
        }

        return inflate;
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
