package ru.csu.profcom;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.csu.profcom.retrofit.CategoryAdapter;
import ru.csu.profcom.retrofit.News;
import ru.csu.profcom.retrofit.NewsAPI;
import ru.csu.profcom.retrofit.NewsAdapter;
import ru.csu.profcom.retrofit.UserAPI;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentNews.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentNews#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNews extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<News> news;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentNews() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentNews.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNews newInstance(String param1, String param2) {
        FragmentNews fragment = new FragmentNews();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_news, container, false);

        news = new ArrayList<>();

        Retrofit client = new Retrofit.Builder()
                .baseUrl(HttpUrl.parse("http://192.168.0.103:88"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsAPI service = client.create(NewsAPI.class);
        Call<List<News>> call = service.getAllNews();
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news = response.body();

                    ListView listview = (ListView) inflate.findViewById(R.id.newsListView);
                    listview.setAdapter(new NewsAdapter(inflate.getContext(), news));
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Toast.makeText(getActivity(), "Не удалось загрузить новости.\n" + t.getLocalizedMessage() + "\n" + t.getMessage(), Toast.LENGTH_LONG).show();
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
