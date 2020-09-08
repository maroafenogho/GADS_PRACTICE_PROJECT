package com.maro.gadspracticeprojectmaroafenogho;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.ApiClient;
import util.ApiInterface;

public class SubmissionPage extends AppCompatActivity {

    TextInputEditText email, firstName, lastName, projectLink;
    ProgressBar progressBar;
    Button submit, ok;
    ImageView cancel, backArrow;
    ImageView success;
    TextView done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission_page);

        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);

        email = findViewById(R.id.email_editText);
        firstName = findViewById(R.id.firstname_editText);
        lastName = findViewById(R.id.lastname_editText);
        projectLink = findViewById(R.id.github_editText);
        submit = findViewById(R.id.button);
        backArrow = findViewById(R.id.backArrow);

        backArrow.setOnClickListener(v -> {
            Intent intent = new Intent(SubmissionPage.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        submit.setOnClickListener(v -> {
            if (Objects.requireNonNull(email.getText()).toString().isEmpty()){
                Toast.makeText(SubmissionPage.this, "Please enter email address", Toast.LENGTH_SHORT).show();
            } else if (Objects.requireNonNull(firstName.getText()).toString().isEmpty()){
                Toast.makeText(SubmissionPage.this, "Please enter name", Toast.LENGTH_SHORT).show();
            } else if (Objects.requireNonNull(lastName.getText()).toString().isEmpty()){
                Toast.makeText(SubmissionPage.this, "Please enter last name", Toast.LENGTH_SHORT).show();
            } else if ( Objects.requireNonNull(projectLink.getText()).toString().isEmpty()){
                Toast.makeText(SubmissionPage.this, "Submission link can't be empty", Toast.LENGTH_SHORT).show();
            }else {
                questionDialog();
            }
        });

    }

    private void showFailureDialog() {
        progressBar.setVisibility(View.GONE);
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionPage.this);
        View view = View.inflate(this, R.layout.feedback,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        success = view.findViewById(R.id.feedback_image);
        done = view.findViewById(R.id.feedback_textView);
        success.setImageResource(R.drawable.ic_baseline_warning_24);
        done.setText(R.string.sub_failed);
    }

    private void performSubmission(){
        ApiInterface apiInterface;
        apiInterface = ApiClient.getForm().create(ApiInterface.class);
//
        Call<ResponseBody> call = apiInterface.submitProject(Objects.requireNonNull(email.getText()).toString(),
                Objects.requireNonNull(firstName.getText()).toString(), Objects.requireNonNull(lastName.getText()).toString(),
                Objects.requireNonNull(projectLink.getText()).toString());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call1, Response<ResponseBody> response) {
                if (response.code()==200){
                    showSuccessDialog();
                } else if(response.code()==400){
                    showFailureDialog();
                    Toast.makeText(SubmissionPage.this, "Please enter a valid email and link", Toast.LENGTH_SHORT).show();
                    Log.d("Error", "onResponse: "+ response);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call1, Throwable t) {

            }
        });
    }
    private void questionDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionPage.this);
        View view = View.inflate(this, R.layout.submit_question,null);
        builder.setView(view);
       cancel = view.findViewById(R.id.cancel);
        ok = view.findViewById(R.id.ok_button);
        AlertDialog dialog = builder.create();
        dialog.show();
        cancel.setOnClickListener(v -> {
            dialog.dismiss();

        });
        ok.setOnClickListener(v -> {
            performSubmission();
            progressBar.setVisibility(View.VISIBLE);
            dialog.dismiss();
        });
    }

    private void showSuccessDialog() {
        progressBar.setVisibility(View.GONE);
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionPage.this);
        View view = View.inflate(this, R.layout.feedback,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        success = view.findViewById(R.id.feedback_image);
        done = view.findViewById(R.id.feedback_textView);
        success.setImageResource(R.drawable.ic_baseline_check_circle_24);
        done.setText(R.string.sub_success);
    }

}