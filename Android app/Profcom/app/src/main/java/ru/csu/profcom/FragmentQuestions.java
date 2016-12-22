package ru.csu.profcom;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.csu.profcom.retrofit.Question;
import ru.csu.profcom.retrofit.QuestionAPI;
import ru.csu.profcom.retrofit.QuestionAdapter;
import ru.csu.profcom.retrofit.User;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentQuestions.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentQuestions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentQuestions extends Fragment {
    private  UserInfoStorage userInfoStorage;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentQuestions() {
        // Required empty public constructor
    }

    public static FragmentQuestions newInstance() {
        return new FragmentQuestions();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentQuestions.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentQuestions newInstance(String param1, String param2) {
        FragmentQuestions fragment = new FragmentQuestions();
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
        final View inflate = inflater.inflate(R.layout.fragment_questions, container, false);

        userInfoStorage = new UserInfoStorage(getActivity());

        Button FAQButton = (Button) inflate.findViewById(R.id.FAQ_button);
        FAQButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(webIntent);
            }
        });


        final Retrofit client = new Retrofit.Builder()
                .baseUrl(HttpUrl.parse(userInfoStorage.getRetrofitServer()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final EditText questionEditText = (EditText)inflate.findViewById(R.id.questionEditText);
        Button askButton = (Button)inflate.findViewById(R.id.ask_button);
        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionAPI service = client.create(QuestionAPI.class);

                Question question = new Question();
                question.setQuestion(questionEditText.getText().toString());
                User user = new User();
                user.setId(userInfoStorage.getUsedID());
                question.setQuestioner(user);
                CheckBox anon = (CheckBox) inflate.findViewById(R.id.anonCheckBox);
                question.setAnon(anon.isChecked());

                Call<Question> call = service.postQuestion("application/json", question);
                call.enqueue(new Callback<Question>() {
                    @Override
                    public void onResponse(Call<Question> call, Response<Question> response) {
                        Toast.makeText(getContext(), "Ваш вопрос успешно отправлен", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Question> call, Throwable t) {
                        Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                questionEditText.setText("");
            }
        });


        QuestionAPI service = client.create(QuestionAPI.class);
        Call<List<Question>> call = service.getUserQuestions(Long.valueOf(userInfoStorage.getUsedID()));
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if (response.isSuccessful()) {
                    List<Question> questions = response.body();
                    ListView listView = (ListView)inflate.findViewById(R.id.questionsListView);
                    listView.setAdapter(new QuestionAdapter(inflate.getContext(), questions));
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(getActivity(), "Не удалось загрузить Ваши вопросы.\n" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
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
