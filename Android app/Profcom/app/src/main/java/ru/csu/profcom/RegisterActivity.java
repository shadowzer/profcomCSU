package ru.csu.profcom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.csu.profcom.retrofit.User;
import ru.csu.profcom.retrofit.UserAPI;

public class RegisterActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private RelativeLayout mActivityView;
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserRegisterTask mRegisterTask = null;

    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mFirstnameView;
    private EditText mLastnameView;
    private EditText mSurnameView;
    private EditText mGroup;
    private Budget budget;
    private FulltimeEducation fulltimeEducation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mActivityView = (RelativeLayout) findViewById(R.id.activity_register);
        mProgressBar=(ProgressBar) findViewById(R.id.progressbar);
        mProgressBar.setVisibility(mProgressBar.INVISIBLE);
        mUsernameView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.register_password);
        mSurnameView = (EditText) findViewById(R.id.surname);
        mFirstnameView = (EditText) findViewById(R.id.firstname);
        mLastnameView = (EditText) findViewById(R.id.lastname);
        mGroup = (EditText) findViewById(R.id.group);

        final RadioButton fulltimeBtn = (RadioButton) findViewById(R.id.fulltimeLearnRadioBtn);
        final RadioButton distanceBtn = (RadioButton) findViewById(R.id.distanceLearnRadioBtn);

        final RadioButton budgetBtn = (RadioButton) findViewById(R.id.budgetRadioBtn);
        final RadioButton contractBtn = (RadioButton) findViewById(R.id.contractRadioBtn);

        fulltimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (distanceBtn.isChecked()) {
                    distanceBtn.setChecked(false);
                }
            }
        });
        distanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fulltimeBtn.isChecked()) {
                    fulltimeBtn.setChecked(false);
                }
            }
        });

        budgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contractBtn.isChecked()) {
                    contractBtn.setChecked(false);
                }
            }
        });
        contractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (budgetBtn.isChecked()) {
                    budgetBtn.setChecked(false);
                }
            }
        });

        Button registerButton = (Button) findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

        findViewById(R.id.username).requestFocus();
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptRegister() {
        if (mRegisterTask != null) {
            return;
        }

        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);
        mFirstnameView.setError(null);
        mLastnameView.setError(null);
        mSurnameView.setError(null);
        mGroup.setError(null);


        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();
        String firstname = mFirstnameView.getText().toString();
        String lastname = mLastnameView.getText().toString();
        String surname = mSurnameView.getText().toString();
        String group = mGroup.getText().toString();

        final RadioButton fulltimeBtn = (RadioButton) findViewById(R.id.fulltimeLearnRadioBtn);
        final RadioButton distanceBtn = (RadioButton) findViewById(R.id.distanceLearnRadioBtn);
        final RadioButton budgetBtn = (RadioButton) findViewById(R.id.budgetRadioBtn);
        final RadioButton contractBtn = (RadioButton) findViewById(R.id.contractRadioBtn);

        boolean cancel = false;
        View focusView = null;

        // Checking for required fields
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (TextUtils.isEmpty(surname)) {
            mSurnameView.setError(getString(R.string.error_field_required));
            focusView = mSurnameView;
            cancel = true;
        } else if (TextUtils.isEmpty(firstname)) {
            mFirstnameView.setError(getString(R.string.error_field_required));
            focusView = mFirstnameView;
            cancel = true;
        } else if (TextUtils.isEmpty(lastname)) {
            mLastnameView.setError(getString(R.string.error_field_required));
            focusView = mLastnameView;
            cancel = true;
        } else if (TextUtils.isEmpty(group)) {
            mGroup.setError(getString(R.string.error_field_required));
            focusView = mGroup;
            cancel = true;
        }


        // Check for a valid field, if the user entered one.
        if (!TextUtils.isEmpty(username) && !isUsernameValid(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_email));
            Toast.makeText(getApplicationContext(), getString(R.string.email_hint), Toast.LENGTH_LONG).show();
            focusView = mUsernameView;
            cancel = true;
        } else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            Toast.makeText(getApplicationContext(), getString(R.string.password_hint), Toast.LENGTH_LONG).show();
            focusView = mPasswordView;
            cancel = true;
        } else if (!TextUtils.isEmpty(surname) && !isNameValid(surname)) {
            mSurnameView.setError(getString(R.string.error_invalid_name));
            Toast.makeText(getApplicationContext(), getString(R.string.name_hint), Toast.LENGTH_LONG).show();
            focusView = mSurnameView;
            cancel = true;
        } else if (!TextUtils.isEmpty(firstname) && !isNameValid(firstname)) {
            mFirstnameView.setError(getString(R.string.error_invalid_name));
            Toast.makeText(getApplicationContext(), getString(R.string.name_hint), Toast.LENGTH_LONG).show();
            focusView = mFirstnameView;
            cancel = true;
        } else if (!TextUtils.isEmpty(lastname) && !isNameValid(lastname)) {
            mLastnameView.setError(getString(R.string.error_invalid_name));
            Toast.makeText(getApplicationContext(), getString(R.string.name_hint), Toast.LENGTH_LONG).show();
            focusView = mLastnameView;
            cancel = true;
        } else if (!TextUtils.isEmpty(group) && !isGroupValid(group)) {
            mGroup.setError(getString(R.string.error_invalid_group));
            Toast.makeText(getApplicationContext(), getString(R.string.group_hint), Toast.LENGTH_LONG).show();
            focusView = mGroup;
            cancel = true;
        }else if (!fulltimeBtn.isChecked() && !distanceBtn.isChecked()) {
            Toast.makeText(getApplicationContext(), "Выберите очное/заочное обучение", Toast.LENGTH_LONG).show();
            cancel = true;
            focusView = budgetBtn;
        } else if (!budgetBtn.isChecked() && !contractBtn.isChecked()) {
            Toast.makeText(getApplicationContext(), "Выберите бюджетное/контрактное обучение", Toast.LENGTH_LONG).show();
            cancel = true;
            focusView = budgetBtn;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Get info from radio buttons
            if (fulltimeBtn.isChecked())
                fulltimeEducation = FulltimeEducation.FULLTIME;
            else
                fulltimeEducation = FulltimeEducation.DISTANCE;
            if (budgetBtn.isChecked())
                budget = Budget.BUDGET;
            else
                budget = Budget.CONTRACT;

            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mRegisterTask = new UserRegisterTask(username, password, surname, firstname, lastname, group, budget, fulltimeEducation);
            mRegisterTask.execute((Void) null);
        }
    }

    private boolean isUsernameValid(String username) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._-]+@[A-Za-z0-9-]+[.]{1}[A-Za-z]{2,}$");
        // TODO: 25.11.2016 USING PATTERN FOR 5 MIN LENGTH FOR USERNAME INSTEAD EMAIL
        //Pattern pattern = Pattern.compile("^[A-Za-z]{1}+[A-Za-z0-9.-_]{4,}");
        Matcher matcher = pattern.matcher(username);
        if (matcher.find()){
            return true;
        } else {
            return false;
        }
    }

    private boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._-]{8,35}$");
        Matcher matcher = pattern.matcher(password);
        if (matcher.find()){
            return true;
        } else {
            return false;
        }
    }

    private boolean isNameValid(String name) {
        Pattern pattern = Pattern.compile("^[А-Яа-я-]{2,25}$");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()){
            return true;
        } else {
            return false;
        }
    }

    private boolean isGroupValid(String name) {
        Pattern pattern = Pattern.compile("^[А-Яа-я]{2,20}-[0-9]{1,4}$");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mActivityView.setVisibility(show ? View.GONE : View.VISIBLE);
            mActivityView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mActivityView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mActivityView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /*@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(long MS) {
        CountDownTimer mCountDownTimer;
        final int[] i = {0};

        mProgressBar.setVisibility(mProgressBar.VISIBLE);
        mProgressBar.setProgress(i[0]);
        mCountDownTimer=new CountDownTimer(MS, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i[0] + millisUntilFinished);
                i[0]++;
                mProgressBar.setProgress(i[0]);

            }

            @Override
            public void onFinish() {
                //Do what you want
                i[0]++;
                mProgressBar.setProgress(i[0]);
            }
        };
        mCountDownTimer.start();
    }*/

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUsername;
        private final String mPassword;
        private final String mSurname;
        private final String mFirstname;
        private final String mLastname;
        private final String mGroup;
        private final Budget mBudget;
        private final FulltimeEducation mFulltimeEducation;

        private boolean success = false;

        UserRegisterTask(String username, String password, String surname, String firstname, String lastname, String group, Budget budget, FulltimeEducation fulltimeEducation) {
            mUsername = username;
            mPassword = password;
            mSurname = surname;
            mFirstname = firstname;
            mLastname = lastname;
            mGroup = group;
            mBudget = budget;
            mFulltimeEducation = fulltimeEducation;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            boolean budg = false;
            boolean ft = false;
            if (budget == Budget.BUDGET)
                budg = true;
            if (fulltimeEducation == FulltimeEducation.FULLTIME)
                ft = true;

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(HttpUrl.parse("http://192.168.0.103:88"))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserAPI service = client.create(UserAPI.class);
            this.success = false;
            try {
                // Simulate network access.
                User user = new User();
                user.setLogin(mUsername);
                user.setPassword(mPassword);
                user.setFirstName(mFirstname);
                user.setLastName(mLastname);
                user.setSurName(mSurname);
                user.setGroup(mGroup);
                user.setBudget(budg);
                user.setFulltime(ft);
                user.setFeePay(false);
                Call<User> call = service.registerUser("application/json", user);
                Thread.sleep(1000);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Пользователь " + response.body().getLogin() + " успешно зарегистрирован.", Toast.LENGTH_LONG).show();
                            success = true;
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        success = false;
                    }
                });
                Thread.sleep(100);
            } catch (Exception e) {
                return false;
            }

            return this.success;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mRegisterTask = null;
            showProgress(false);

            if (success || this.success) {
                finish();
            } else {
                Snackbar.make(findViewById(R.id.activity_register), "Регистрация не удалась. Попробуйте позже", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }

        @Override
        protected void onCancelled() {
            mRegisterTask = null;
            showProgress(false);
        }
    }
}
