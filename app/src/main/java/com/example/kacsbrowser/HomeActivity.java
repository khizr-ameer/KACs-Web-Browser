package com.example.kacsbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    EditText etUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        etUrl = findViewById(R.id.etUrl);
        Button btnGo = findViewById(R.id.btnGo);

        btnGo.setOnClickListener(view -> {
            String url = etUrl.getText().toString().trim();
            if (!url.startsWith("http")) {
                url = "https://" + url;
            }
            openUrl(url);
        });

        // Shortcut Buttons
        ImageButton btnFacebook = findViewById(R.id.btnFacebook);
        ImageButton btnGitHub = findViewById(R.id.btnGitHub);
        ImageButton btnLinkedIn = findViewById(R.id.btnLinkedIn);
        ImageButton btnReddit = findViewById(R.id.btnReddit);

        btnFacebook.setOnClickListener(v -> openUrl("https://www.facebook.com"));
        btnGitHub.setOnClickListener(v -> openUrl("https://www.github.com"));
        btnLinkedIn.setOnClickListener(v -> openUrl("https://www.linkedin.com"));
        btnReddit.setOnClickListener(v -> openUrl("https://www.reddit.com"));

        // Overflow menu button setup
        ImageButton menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(v -> {
            showPopupMenu(v);
        });
    }

    private void showPopupMenu(View anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);

        // Add menu items programmatically
        popupMenu.getMenu().add("New tab");
        popupMenu.getMenu().add("New incognito tab");
        popupMenu.getMenu().add("Bookmarks");
        popupMenu.getMenu().add("History");
        popupMenu.getMenu().add("Downloads");
        popupMenu.getMenu().add("Settings");
        popupMenu.getMenu().add("Help");
        popupMenu.getMenu().add("Exit");

        popupMenu.setOnMenuItemClickListener(item -> {
            Toast.makeText(this, item.getTitle() + " option is open", Toast.LENGTH_SHORT).show();
            return true;
        });

        popupMenu.show();
    }

    public void openUrl(String url) {
        Intent intent = new Intent(HomeActivity.this, BrowserActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
