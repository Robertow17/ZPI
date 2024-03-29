package com.zpi.serviceProvider.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zpi.R;
import com.zpi.model.Service;
import com.zpi.searcher.utils.ServicesAdapter;
import com.zpi.serviceProvider.model.Data;
import com.zpi.serviceProvider.model.ServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderMainActivity extends AppCompatActivity {
    private ServiceProvider serviceProvider;
    private RecyclerView recyclerView;
    private ServicesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_provider_main_activity);

        Data data = new Data();
        serviceProvider= data.getServiceProvider();

        Service service = getIntent().getParcelableExtra(ServicesAdapter.EXTRA_SERVICE);

        if(service!=null) {
            serviceProvider.addService(service);
        }

        setRecyclerView(serviceProvider.getServices());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addServiceButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceProviderMainActivity.this, AddService.class);
                startActivity(intent);
            }
        });
    }

    private void setRecyclerView(List<Service> services)
    {
        recyclerView = (RecyclerView) findViewById(R.id.services);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ServicesAdapter(this, services);
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onBackPressed() {

    }
}
