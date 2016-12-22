package ru.csu.profcom;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import ru.csu.profcom.retrofit.User;
import ru.csu.profcom.retrofit.UserCategory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCategories.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentCategories#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCategories extends Fragment {
    private List<Category> categoryList = new ArrayList<Category>();
    private List<Category> userCategories = new ArrayList<Category>();
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
                .baseUrl(HttpUrl.parse(userInfoStorage.getRetrofitServer()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final CategoryAPI service = client.create(CategoryAPI.class);
        fillUserCategories(inflate, service);

        final ListView listView = (ListView)inflate.findViewById(R.id.categoriesList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 22.12.2016 NOT WORKING LOGICAL OR VISUAL CHECKING
                Object object = listView.getItemAtPosition(position);
            }
        });

        Button saveCategoriesButton = (Button) inflate.findViewById(R.id.saveCategoriesButton);
        saveCategoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshUserCategories(inflate, service);
            }
        });

        return inflate;
    }



    private void fillUserCategories(final View inflate, final CategoryAPI service) {
        Call<List<Category>> call = service.getUserCategoriesList(Long.valueOf(userInfoStorage.getUsedID()));
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    userCategories = response.body();
                    Collections.sort(userCategories);

                    Call<List<Category>> categoriesList = service.getCategoriesList();
                    categoriesList.enqueue(new Callback<List<Category>>() {
                        @Override
                        public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                            if (response.isSuccessful()) {
                                categoryList = response.body();
                                Collections.sort(categoryList);

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
    }

    private void refreshUserCategories(final View inflate, final CategoryAPI service) {
        final ListView listView = (ListView)inflate.findViewById(R.id.categoriesList);

        HashMap<Category, CheckBox> checkedCategories = ((CategoryAdapter) listView.getAdapter()).getCheckedItems();
        for (final Map.Entry<Category, CheckBox> item : checkedCategories.entrySet()) {
            if (!userCategories.contains(item)) {
                UserCategory userCategory = new UserCategory();
                User user = new User();
                Category category = new Category();
                user.setId(userInfoStorage.getUsedID());
                category.setId(item.getKey().getId());
                userCategory.setCategory(category);
                userCategory.setUser(user);
                Call<UserCategory> userCategoryCall = service.postUserCategoryRecord("application/json", userCategory);
                userCategoryCall.enqueue(new Callback<UserCategory>() {
                    @Override
                    public void onResponse(Call<UserCategory> call, Response<UserCategory> response) {
                        if (!response.isSuccessful()) {
                            onFailure(call, new Throwable("Не удалось подписаться на категорию '" + item.getKey().getName() + "'"));
                        } else {
                            Toast.makeText(getActivity(), "Вы подписались на категорию '" + item.getKey().getName() + "'", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserCategory> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                try {
                    Thread.sleep(100);
                } catch (Exception ex) {

                }
            }
        }
        checkedCategories = ((CategoryAdapter)listView.getAdapter()).getCheckedItems();
        for (final Category item : userCategories) {
            if (!checkedCategories.containsKey(item)) {
                Call<Void> voidCall = service.removeCategoryFromUser(Long.valueOf(userInfoStorage.getUsedID()), item.getId());
                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            onFailure(call, new Throwable("Не удалось отписаться от категории '" + item.getName() + "'"));
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                try {
                    Thread.sleep(100);
                } catch (Exception ex) {

                }
            }
        }


        Call<List<Category>> userCategoriesList = service.getUserCategoriesList(Long.valueOf(userInfoStorage.getUsedID()));
        userCategoriesList.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    userCategories = response.body();
                    Collections.sort(userCategories);
                    ListView listview = (ListView) inflate.findViewById(R.id.categoriesList);
                    listview.setAdapter(new CategoryAdapter(inflate.getContext(), categoryList, userCategories));
                } else {
                    onFailure(call, new Throwable("Не удалось загрузить с сервера список Ваших категорий"));
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private Category getCategoryByName(String name) {
        for (Category item : categoryList) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
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
