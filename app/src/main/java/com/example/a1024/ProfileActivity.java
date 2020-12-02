package com.example.a1024;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.a1024.Base.BaseActivity;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileActivity extends BaseActivity {

    @BindView(R.id.profile_activity_imageview_profile)
    ImageView imageViewProfile;
    @BindView(R.id.profile_activity_edit_text_username)
    TextInputEditText textInputEditTextUsername;
    @BindView(R.id.profile_activity_text_view_email)
    TextView textViewEmail;
    @BindView(R.id.profile_activity_progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }

    @OnClick(R.id.profile_activity_button_update)
    public void onClickUpdateButton() { }

    @OnClick(R.id.profile_activity_button_sign_out)
    public void onClickSignOutButton() { }

    @OnClick(R.id.profile_activity_button_delete)
    public void onClickDeleteButton() { }
}