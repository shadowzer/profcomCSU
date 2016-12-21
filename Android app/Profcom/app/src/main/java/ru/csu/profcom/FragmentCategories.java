package ru.csu.profcom;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.csu.profcom.retrofit.Category;
import ru.csu.profcom.retrofit.CategoryAPI;
import ru.csu.profcom.retrofit.CategoryAdapter;
import ru.csu.profcom.retrofit.QuestionAPI;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCategories.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentCategories#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCategories extends Fragment {
    private List<Category> userCategories = new ArrayList<Category>();
    private List<Category> categoryList = new ArrayList<Category>();
    private UserInfoStorage userInfoStorage;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentCategories() {
        // Required empty public constructor
    }

    public static FragmentCategories newInstance() {
        return new FragmentCategories();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentCategories.
     */
    public static FragmentCategories newInstance(String param1, String param2) {
        FragmentCategories fragment = new FragmentCategories();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_categories, container, false);

        userInfoStorage = new UserInfoStorage(getActivity());

        Retrofit client = new Retrofit.Builder()
                .baseUrl(HttpUrl.parse("http://192.168.0.103:88"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final CategoryAPI service = client.create(CategoryAPI.class);
        Call<List<Category>> call = service.getUserCategoriesList(Long.valueOf(userInfoStorage.getUsedID()));
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    userCategories = response.body();
                    Call<List<Category>> categoriesList = service.getCategoriesList();
                    categoriesList.enqueue(new Callback<List<Category>>() {
                        @Override
                        public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                            if (response.isSuccessful()) {
                                FragmentCategories.this.categoryList = response.body();

                                ListView listview = (ListView) inflate.findViewById(R.id.categoriesList);
                                listview.setAdapter(new CategoryAdapter(inflate.getContext(), categoryList, userCategories));
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Category>> call, Throwable t) {
                            Toast.makeText(getActivity(), "Не удалось получить список всех категорий.\n" + t.getLocalizedMessage() , Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                            try {
                                // FIXME: 20.12.2016 REDIRECTING TO NEWS FRAGMENT
                                Class fragmentClass = FragmentPersonalArea.class;
                                Fragment fragment = (Fragment) fragmentClass.newInstance();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.FrameLayoutMain, fragment).commit();
                            } catch (Exception ex) {

                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getActivity(), "Не удалось получить список ваших категорий.\n" + t.getLocalizedMessage() , Toast.LENGTH_SHORT).show();
                getActivity().finish();
                try {
                    // FIXME: 20.12.2016 REDIRECTING TO NEWS FRAGMENT
                    Class fragmentClass = FragmentPersonalArea.class;
                    Fragment fragment = (Fragment) fragmentClass.newInstance();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.FrameLayoutMain, fragment).commit();
                } catch (Exception ex) {

                }
            }
        });


        Button saveCategoriesButton = (Button) inflate.findViewById(R.id.saveCategoriesButton);
        saveCategoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 21.12.2016 IF (SERVER != CLIENT) { IF (!CLIENT) DELETE;    IF (CLIENT) POST; }
            }
        });

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
