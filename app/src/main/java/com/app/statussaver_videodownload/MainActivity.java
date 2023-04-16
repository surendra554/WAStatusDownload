package com.app.statussaver_videodownload;

import static android.os.Build.VERSION.SDK_INT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int requestCode = 1;
    Adapter adapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    File[] files;

    ArrayList<ModelClass> fileslist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView = findViewById(R.id.swipe);
        chechPermission();

       // setuplayout();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                setuplayout();
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.setRefreshing(false);
                        }
                    }, 1000);

                }

            }
        });


    }

    private void setuplayout() {
        fileslist.clear();
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        adapter = new Adapter(MainActivity.this,getData());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private ArrayList<ModelClass> getData() {

        ModelClass f;
        String targetpaths = Environment.getExternalStorageDirectory().getAbsolutePath()+Constant.FOLDER_NAME+"Media/.Statuses";
        File targetdir = new File(targetpaths);
        files = targetdir.listFiles();
        for (int i =0; i<files.length; i++){

            File file = files[i];
            f = new ModelClass();
            f.setUri(Uri.fromFile(file));
            f.setPath(files[i].getAbsolutePath());
            f.setFilename(file.getName());

            if(!f.getUri(). toString().endsWith(".nomedia"))
            {
                fileslist.add(f);

            }
        }

        return fileslist;
    }


    private void chechPermission() {

        if(SDK_INT>23){
            if(checkSelfPermission(Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION)== PackageManager.PERMISSION_GRANTED)
            {
             // Main Code
                setuplayout();
            }
            else
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION},requestCode);
            }

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Already", Toast.LENGTH_SHORT).show();
            setuplayout();
        }
    }
}