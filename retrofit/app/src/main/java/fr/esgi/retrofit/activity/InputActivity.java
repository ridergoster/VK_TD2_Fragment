package fr.esgi.retrofit.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.esgi.retrofit.R;

/**
 * Created by vincentk on 14/06/2016.
 */
public class InputActivity extends AppCompatActivity {

    public static final String GITHUB_NAME = "githubName";
    @BindView(R.id.github_name)
    EditText githubName;
    @BindView(R.id.send_button)
    Button sendButton;

    SharedPreferences sharedPref; //Correction stocker le sharedprefs en field

    public void openMainActivity(String username) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(GITHUB_NAME, username);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        ButterKnife.bind(this);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPref.getString(GITHUB_NAME, "");
        githubName.setText(username);
        githubName.setSelection(githubName.getText().length());

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick(R.id.send_button)
    public void submit() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        String username = githubName.getText().toString().isEmpty() ? "ridergoster" : githubName.getText().toString();
        sharedPref.edit().putString(GITHUB_NAME, username).apply(); //Correction inline & apply
        openMainActivity(username);
    }

}
