package ru.csu.profcom;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
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
        Call<List<Category>> call = service.getUserCategoriesList(Long.valueOf(userInfoStorage.getUserID()));
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
        final boolean[] success = { true, true };

        /**
         * POSTING NEW CHECKED CATEGORIES
         */
        HashMap<Integer, CheckBox> checkedCategories = ((CategoryAdapter) listView.getAdapter()).getCheckedItems();
        Set<Integer> IDs = ((CategoryAdapter) listView.getAdapter()).getCheckedItems().keySet();
        for (final Map.Entry<Integer, CheckBox> item : checkedCategories.entrySet()) {
            final Category category = getCategoryByID(item.getKey());

            if (!userCategoriesContainsID(item.getKey())) {
                UserCategory userCategory = new UserCategory();
                User user = new User();
                user.setId(userInfoStorage.getUserID());
                userCategory.setCategory(category);
                userCategory.setUser(user);
                Call<UserCategory> userCategoryCall = service.postUserCategoryRecord("application/json", userCategory);
                userCategoryCall.enqueue(new Callback<UserCategory>() {
                    @Override
                    public void onResponse(Call<UserCategory> call, Response<UserCategory> response) {
                        if (!response.isSuccessful()) {
                            success[0] = false;
                            success[1] = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<UserCategory> call, Throwable t) {
                        Toast.makeText(getActivity(), "Не удалось подписаться на категорию '" + category.getName() + "'\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        success[0] = false;
                        success[1] = false;
                    }
                });
                try {
                    Thread.sleep(30);
                } catch (Exception ex) { }
            }
        }

        // FIXME: 23.12.2016 REMOVE THIS. IT'S FOR DEBUG ONLY
        if (!success[0])
            Toast.makeText(getActivity(), "Произошла ошибка во время подписки на новые отмеченные категории", Toast.LENGTH_SHORT).show();
        success[0] = true;

        /**
         * DELETING UNCHECKED CATEGORIES
         */
        for (final Category item : userCategories) {
            if (!checkedCategories.containsKey(item.getId())) {
                Call<Void> voidCall = service.removeCategoryFromUser(Long.valueOf(userInfoStorage.getUserID()), item.getId());
                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            success[0] = false;
                            success[1] = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getActivity(), "Не удалось отписаться от категории '" + item.getName() + "'\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        success[1] = false;
                    }
                });
                try {
                    Thread.sleep(30);
                } catch (Exception ex) {

                }
            }
        }

        // FIXME: 23.12.2016 REMOVE THIS TOAST. IT'S FOR DEBUG ONLY
        if (!success[0])
            Toast.makeText(getActivity(), "Произошла ошибка во время отписки от категорий", Toast.LENGTH_SHORT).show();

        if (success[1])
            Toast.makeText(getActivity(), "Успешно обновлен список отмеченных категорий", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "Ошибка во время обновления списка категорий", Toast.LENGTH_SHORT).show();

        Call<List<Category>> userCategoriesList = service.getUserCategoriesList(Long.valueOf(userInfoStorage.getUserID()));
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

    private void refreshUserCategoriesV2(final View inflate, final CategoryAPI service) { // delete all prev categories and subs for all checked
        final boolean[] success = {true, true};
        for(final Category item : userCategories) {
            Call<Void> voidCall = service.removeCategoryFromUser(Long.valueOf(userInfoStorage.getUserID()), item.getId());
            voidCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (!response.isSuccessful()) {
                        try {
                            Log.d("Sub response", item.getName() + ":" + response.code() + "\n" + response.errorBody().string());
                            success[0] = false;
                            success[1] = false;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else
                        Log.d("DELETE RESPONSE SUCCESS", item.getName() + ":" + response.code() + "\n");
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getActivity(), "Не удалось отписаться от категории '" + item.getName() + "'\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    success[1] = false;
                }
            });
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // FIXME: 23.12.2016 REMOVE THIS TOAST. IT'S FOR DEBUG ONLY
        if (!success[0])
            Toast.makeText(getActivity(), "Произошла ошибка во время отписки от категорий", Toast.LENGTH_SHORT).show();
        success[0] = true;

        ListView listView = (ListView)inflate.findViewById(R.id.categoriesList);
        Set<Integer> IDs = ((CategoryAdapter) listView.getAdapter()).getCheckedItems().keySet();
        for(Integer item : IDs) {
            final Category tag = getCategoryByID(item);
            UserCategory userCategory = new UserCategory();
            User user = new User();
            user.setId(userInfoStorage.getUserID());
            userCategory.setUser(user);
            userCategory.setCategory(tag);
            Call<UserCategory> userCategoryCall = service.postUserCategoryRecord("application/json", userCategory);
            userCategoryCall.enqueue(new Callback<UserCategory>() {
                @Override
                public void onResponse(Call<UserCategory> call, Response<UserCategory> response) {
                    if (!response.isSuccessful()) {
                        try {
                            Log.d("Sub response", tag.getName() + ":" + response.code() + "\n" + response.errorBody().string());
                            success[0] = false;
                            success[1] = false;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else
                        Log.d("SUB RESPONSE SUCCESS", tag.getName() + ":" + response.code() + "\n");
                }

                @Override
                public void onFailure(Call<UserCategory> call, Throwable t) {
                    Toast.makeText(getActivity(), "Не удалось подписаться на категорию '" + tag.getName() + "'\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    success[0] = false;
                    success[1] = false;
                }
            });
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // FIXME: 23.12.2016 REMOVE THIS. IT'S FOR DEBUG ONLY
        if (!success[0])
            Toast.makeText(getActivity(), "Произошла ошибка во время подписки на отмеченные категории", Toast.LENGTH_SHORT).show();

        if (success[1])
            Toast.makeText(getActivity(), "Успешно обновлен список отмеченных категорий", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "Ошибка во время обновления списка категорий", Toast.LENGTH_SHORT).show();
    }

    private boolean userCategoriesContainsID(Integer ID) {
        for(Category item:userCategories) {
            if (item.getId() == ID)
                return true;
        }
        return false;
    }

    private Category getCategoryByName(String name) {
        for (Category item : categoryList) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    private Category getCategoryByID(Integer ID) {
        for (Category item : categoryList) {
            if (item.getId() == ID) {
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
