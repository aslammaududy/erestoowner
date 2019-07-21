package me.aslammaududy.erestoowner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.aslammaududy.erestoowner.Adapter.LayoutBuilderAdapter;
import me.aslammaududy.erestoowner.Helper.ProgressBar;
import me.aslammaududy.erestoowner.Model.Layout;
import me.aslammaududy.erestoowner.NetworkManager.Connection;
import me.aslammaududy.erestoowner.NetworkManager.Endpoints;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private View divider;
    private RecyclerView layoutBuilder;
    private BootstrapButton buildButton;
    private BootstrapEditText layoutSum;
    private LayoutBuilderAdapter layoutBuilderAdapter;
    private List<Layout> layoutList;
    private Layout layout;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        divider = findViewById(R.id.divider);
        layoutBuilder = findViewById(R.id.layout_recycler);
        buildButton = findViewById(R.id.build_button);
        layoutSum = findViewById(R.id.layout_sum);

        preferences = getSharedPreferences("erestoowner", 0);
        editor = preferences.edit();

        layoutList = new ArrayList<>();

        layoutBuilder.setLayoutManager(new LinearLayoutManager(this));
    }

    public void createLayoutBuilder(View view) {
        ProgressBar.show(this);

        Handler handler = new Handler();
        handler.postDelayed(ProgressBar::dissmiss, 1000);

        divider.setVisibility(View.VISIBLE);
        layoutBuilder.setVisibility(View.VISIBLE);
        buildButton.setVisibility(View.VISIBLE);

        int sum = Integer.parseInt(layoutSum.getText().toString());
        List<String> layoutHints = new ArrayList<>();

        for (int i = 0; i < sum; i++) {
            layoutHints.add(String.valueOf(i + 1));
        }

        layoutBuilderAdapter = new LayoutBuilderAdapter(layoutHints);
        layoutBuilder.setAdapter(layoutBuilderAdapter);
    }

    public void buildLayout(View view) {
        List<LayoutBuilderAdapter.LayoutItem> layoutItems = layoutBuilderAdapter.retrieveData();
        Log.i("size", layoutItems.size() + "");
        for (int i = 0; i < layoutItems.size(); i++) {
            String name = layoutItems.get(i).layoutName;
            String start = layoutItems.get(i).startNumber;
            String end = layoutItems.get(i).endNumber;

            StringBuilder s = new StringBuilder();
            for (int j = Integer.parseInt(start); j <= Integer.parseInt(end); j++) {
                s.append(j);
                if (j < Integer.parseInt(end)) {
                    s.append(',');
                }
            }

            layout = new Layout();
            layout.setNama(name);
            layout.setNomorMeja(s.toString());

            layoutList.add(layout);
        }

        Endpoints endpoints = Connection.getEndpoints();
        endpoints.createLayouts(layoutList).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject object = new JSONObject(response.body().string());
                    if (object.get("status").equals("sukses")) {
                        Intent intent = new Intent(MainActivity.this, SelectTableActivity.class);
                        startActivity(intent);

                        editor.putBoolean("wizard", true);
                        editor.commit();
                    } else {
                        Toast.makeText(MainActivity.this, "Terjadi Kesalahan", Toast.LENGTH_LONG);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
