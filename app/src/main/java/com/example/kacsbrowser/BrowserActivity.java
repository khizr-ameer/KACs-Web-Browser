    package com.example.kacsbrowser;
    
    import android.annotation.SuppressLint;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.KeyEvent;
    import android.view.inputmethod.EditorInfo;
    import android.webkit.WebSettings;
    import android.webkit.WebView;
    import android.webkit.WebViewClient;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ImageButton;
    import android.widget.TextView;
    
    import androidx.appcompat.app.AppCompatActivity;
    
    public class BrowserActivity extends AppCompatActivity {
    
        WebView webView;
        EditText etWebUrl;
    
        @SuppressLint("SetJavaScriptEnabled")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_browser);
    
            webView = findViewById(R.id.webView);
            etWebUrl = findViewById(R.id.etWebUrl);
            ImageButton btnGoWeb = findViewById(R.id.btnGoWeb);
    
            // Enable JavaScript and storage
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDomStorageEnabled(true);
            webView.setWebViewClient(new WebViewClient());
    
            // Load URL passed from HomeActivity
            String url = getIntent().getStringExtra("url");
            if (url != null && !url.isEmpty()) {
                etWebUrl.setText(url);
                webView.loadUrl(url);
            }
    
            // Handle Go button click
            btnGoWeb.setOnClickListener(v -> {
                String typedUrl = etWebUrl.getText().toString().trim();
                if (!typedUrl.startsWith("http")) {
                    typedUrl = "https://" + typedUrl;
                }
                webView.loadUrl(typedUrl);
            });
    
            // Handle keyboard "Enter" to search
            etWebUrl.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
                if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE
                        || actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                    String typedUrl = etWebUrl.getText().toString().trim();
                    if (!typedUrl.startsWith("http")) {
                        typedUrl = "https://" + typedUrl;
                    }
                    webView.loadUrl(typedUrl);
                    return true;
                }
                return false;
            });
    
            // Navigation buttons
            Button btnBack = findViewById(R.id.btnBack);
            Button btnForward = findViewById(R.id.btnForward);
            Button btnReload = findViewById(R.id.btnReload);
            Button btnStop = findViewById(R.id.btnStop);
    
            btnBack.setOnClickListener(v -> {
                if (webView.canGoBack()) webView.goBack();
            });
    
            btnForward.setOnClickListener(v -> {
                if (webView.canGoForward()) webView.goForward();
            });
    
            btnReload.setOnClickListener(v -> webView.reload());
    
            btnStop.setOnClickListener(v -> webView.stopLoading());
        }
    
        @Override
    
        public void onBackPressed() {
            if (webView.canGoBack()) {
                webView.goBack(); // Go back in WebView history
            } else {
                super.onBackPressed(); // Go back to HomeActivity (if it's still in the back stack)
            }
        }
    
    
    }