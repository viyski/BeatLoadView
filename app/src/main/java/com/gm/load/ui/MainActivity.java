package com.gm.load.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gm.load.R;
import com.gm.load.widget.BeatLoadView;


/**
 * Created by lgm on 2017/3/12.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private BeatLoadView beatLoadView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beatLoadView = (BeatLoadView) findViewById(R.id.beat_view);
        Button button = (Button) findViewById(R.id.run_btn);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.run_btn:
                boolean running = beatLoadView.isRunning();
                beatLoadView.setDrawRunning(!running);
                break;
        }
    }
}
