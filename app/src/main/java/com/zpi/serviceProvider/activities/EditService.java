package com.zpi.serviceProvider.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.zpi.Data;
import com.zpi.R;
import com.zpi.ServerConnector.ServerConnector;
import com.zpi.ServerConnector.ServiceName;
import com.zpi.model.Category;
import com.zpi.model.Photo;
import com.zpi.model.Service;
import com.zpi.model.Subcategory;
import com.zpi.model.TransportDetails;
import com.zpi.model.WeddingHallDetails;
import com.zpi.searcher.activities.ServiceDetails;
import com.zpi.searcher.utils.PageTransformer;
import com.zpi.searcher.utils.ServicesAdapter;
import com.zpi.searcher.utils.ViewPagerAdapter;
import com.zpi.serviceProvider.activities.ServiceProviderMainActivity;
import com.zpi.serviceProvider.model.ServiceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zpi.searcher.utils.ItemViewPagerAdapter.EXTRA_POSITION;
import static com.zpi.searcher.utils.ServicesAdapter.EXTRA_SERVICE;

public class EditService extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    //private Service service;
    private EditText name, localization, description, email, phone, maxsize;
    private TextView maxsizeText, accomodationText, title;
    private CheckBox accomodation;
    private ViewPager viewPager;
    private Spinner category, subcategory;
    private Button addPhoto, addService;

    private List<Photo> servicePhotos;
    private Photo nophotos;
    private Uri mImageUri;
    private ViewPagerAdapter viewPagerAdapter;


    Data data;
    List<Category> categories;
    List<Subcategory> subcategories;
    Service service;
    //Service createdService;

    ServiceProvider currentServiceProvider;
    com.zpi.serviceProvider.model.Data data1;
    int pos;
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_provider_add_service_activity);

        service = getIntent().getParcelableExtra(ServicesAdapter.EXTRA_SERVICE);
        pos = getIntent().getExtras().getInt(EXTRA_POSITION);

        index = service.getId();

        data1 = new com.zpi.serviceProvider.model.Data();
        currentServiceProvider = data1.getServiceProvider();


        data = new Data();
        data.setSubcategories();
        categories = data.getCategories();
        subcategories = data.getSubcategoriesList();

        name = findViewById(R.id.editText);
        localization = findViewById(R.id.editText2);
        description = findViewById(R.id.editText5);
        email = findViewById(R.id.editText3);
        phone = findViewById(R.id.editText4);
        maxsize = findViewById(R.id.editText6);

        title = findViewById(R.id.textView6);
        title.setText("Edytuj usługę");
        accomodationText = findViewById(R.id.textView16);
        maxsizeText = findViewById(R.id.textView15);

        accomodation = findViewById(R.id.checkBox);

        viewPager = findViewById(R.id.viewPager);

        subcategory = findViewById(R.id.spinner2);
        category = findViewById(R.id.spinner);

        addPhoto = findViewById(R.id.addPhotoButton);
        addService = findViewById(R.id.button3);
        addService.setText("Edytuj usługę");

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = validateDataAndModifyService();
                Toast.makeText(EditService.this, result, Toast.LENGTH_LONG).show();
                if(result.equals("Usługa została pomyślnie zmodyfikowana")){
                    Intent intent = new Intent(EditService.this, ServiceProviderMainActivity.class);
                    startActivity(intent);
                }
            }
        });

        servicePhotos =  service.getPhotos();

        viewPagerAdapter = new ViewPagerAdapter(EditService.this, servicePhotos);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPageTransformer(true, new PageTransformer());

        addPhoto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openFileChooser();

            }
        });

        buildCategorySpinner();
        setValuesFromService(service);

    }

    private void setValuesFromService(Service service){
        name.setText(service.getName());
        localization.setText(service.getLocalization());
        email.setText(service.getEmail());
        phone.setText(service.getPhoneNumber());
        description.setText(service.getDescription());
        int categoryIndex = findPositionInCategorySpinner(categories,service.getCategory().getName());
        category.setSelection(categoryIndex);

        if(service.getSubcategory()!=null) {
//            List<Subcategory> possibleSubcategories = categories.get(categoryIndex-1).getSubcategories();
//            subcategory.setSelection(findPositionInSubategorySpinner(possibleSubcategories, service.getSubcategory().getName()));
            //getIndex(subcategory, service.getSubcategory().getName());
        }
        if(service.getWeddingHallDetails()!=null){
            maxsize.setText(Integer.toString(service.getWeddingHallDetails().getMaxNumberOfGuests()));
            accomodation.setChecked(service.getWeddingHallDetails().isCanSleep());
        }
        if(service.getTransportDetails()!=null){
            maxsize.setText(Integer.toString(service.getTransportDetails().getMaxSittingPlaces()));
        }

    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equals(myString)){
                return i;
            }
        }

        return 0;
    }

    private int findPositionInCategorySpinner(List<Category> categories, String name) {
        int i = 0;
        for (Category c : categories) {
            i++;
            if(c.getName().equals(name)){
                return i;
            }
        }
        return 0;
    }

    private int findPositionInSubategorySpinner(List<Subcategory> subcategories, String name) {
        int i = 0;
        for (Subcategory c : subcategories) {
            i++;
            if(c.getName().equals(name)){
                return i;
            }
        }
        return 0;
    }
    private Category findValidCategory(String name){
        for (Category c: categories){
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

    private Subcategory findValidSubcategory(String name){
        for (Subcategory s: subcategories){
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }

    private String validateDataAndModifyService(){
        String serviceName = name.getText().toString();
        String serviceLocalization = localization.getText().toString();
        String serviceCategory = category.getSelectedItem().toString();
        String serviceSubcategory = subcategory.getSelectedItem().toString();
        String serviceEmail = email.getText().toString();
        String servicePhone = phone.getText().toString();
        String serviceMaxSize = maxsize.getText().toString();
        String serviceDescription = description.getText().toString();
        Boolean serviceAccomodation = accomodation.isChecked();


        String[] values= {serviceName, serviceLocalization, serviceEmail, servicePhone, serviceMaxSize, serviceCategory, serviceSubcategory};
        String[] names = {"Nazwa", "Lokalizacja","Email","Telefon","Liczba miejsc","Kategoria","Podkategoria"};
        boolean wrongData = false;


        String result = "Brakuje następujących danych: ";
        for(int i=0; i<4; i++){
            if((values[i]).equals("")){
                result = result + "\n"+ (names[i]);
                wrongData=true;
            }
        }
        if((values[5]).equals("SALE") || (values[5]).equals("TRANSPORT") ) {
            if ((values[4]).equals("")) {
                result = result + "\n" +( names[4]);
                wrongData = true;
            }
        }
        for(int j=5;j<7;j++) {
            if ((values[j]).equals("Wybierz...")) {
                result = result +"\n"+ ( names[j]);
                wrongData = true;
            }
        }
        if(wrongData){
            return result;
        }
        else
        {
            result = "Wpisano niepoprawnie: ";
            Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
            Matcher emailMatcher = emailPattern.matcher(values[2]);
            if(!emailMatcher.matches()){
                result = result + "\n"+names[2];
                wrongData = true;
            }
            Pattern phonePattern = Pattern.compile("(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)");
            Matcher phoneMatcher = phonePattern.matcher(values[3]);
            if(!phoneMatcher.matches()){
                result = result + "\n"+names[3];
                wrongData = true;
            }
            if(!wrongData){
                //modifyService(values, serviceDescription, serviceAccomodation);
                Service createdService = addNewService(values, serviceDescription, serviceAccomodation);
                //com.zpi.serviceProvider.model.Data data1 = new com.zpi.serviceProvider.model.Data();
                //data1.getServiceProvider().addService(createdService);
                ServerConnector<Service> serverConnector = new ServerConnector<>(ServiceName.services);
                boolean done = serverConnector.update(index,createdService);
                if( done) {return "Usługa została pomyślnie zmodyfikowana";}
                else { return "Błąd";}
            }
            else{
                return result;
            }

        }
    }

    private Integer getNumber(String s){
        try {
            Integer p = Integer.parseInt(s);
            return p;
        }
        catch (Exception e){
            return null;
        }
    }

    private Service addNewService(String[] values, String description, boolean accomodation){
        Service newService;
        if(values[5].equals("SALE")){
            //newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), new WeddingHallDetails(accomodation, getNumber(values[4])), null, servicePhotos);
            newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), new WeddingHallDetails(accomodation, getNumber(values[4])), null, new ArrayList<>());
        }
        else if(values[5].equals("TRANSPORT")){
            //newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), null, new TransportDetails(getNumber(values[4])), servicePhotos);
            newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), null, new TransportDetails(getNumber(values[4])), new ArrayList<>());
        }
        else{
            //newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), null, null, servicePhotos);
            newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), null, null, new ArrayList<>());
        }
        return newService;
    }

//    private void modifyService(String[] values, String description, boolean accomodation){
//        if(values[5].equals("SALE")){
//            currentServiceProvider.getServices().get(pos).editService(values[0], values[1], description, values[3], values[2],findValidSubcategory(values[6]),  findValidCategory(values[5]),  new WeddingHallDetails(accomodation, getNumber(values[4])), null, servicePhotos);
//        }
//        else if(values[5].equals("TRANSPORT")){
//            currentServiceProvider.getServices().get(pos).editService(values[0], values[1], description, values[3], values[2],findValidSubcategory(values[6]), findValidCategory(values[5]), null, new TransportDetails(getNumber(values[4])), servicePhotos);
//        }
//        else{
//            currentServiceProvider.getServices().get(pos).editService(values[0], values[1], description, values[3], values[2], findValidSubcategory(values[6]), findValidCategory(values[5]), null, null, servicePhotos);
//        }
//    }

    private void setVisibilityInCaseOfCategory(String categoryName) {
        if(categoryName.equals("SALE")){
            maxsize.setVisibility(View.VISIBLE);
            accomodation.setVisibility(View.VISIBLE);
            accomodationText.setVisibility(View.VISIBLE);
            maxsizeText.setVisibility(View.VISIBLE);
            maxsizeText.setText("Maksymalna liczba gości:");
//            maxsize.setText("");
//            accomodation.setChecked(false);
        }
        else if(categoryName.equals("TRANSPORT")){
            maxsize.setVisibility(View.VISIBLE);
            accomodation.setVisibility(View.INVISIBLE);
            accomodationText.setVisibility(View.INVISIBLE);
            maxsizeText.setVisibility(View.VISIBLE);
            maxsizeText.setText("Maksymalna liczba pasażerów:");
//            maxsize.setText("");
        }
        else {
            maxsize.setVisibility(View.INVISIBLE);
            accomodation.setVisibility(View.INVISIBLE);
            accomodationText.setVisibility(View.INVISIBLE);
            maxsizeText.setVisibility(View.INVISIBLE);
        }
    }

    public void addEmptyPhoto() {

       // nophotos = new Photo(String.valueOf(R.drawable.no_photos));
        //servicePhotos.add(nophotos);

    }

    public void notifyPhoto() {
        viewPagerAdapter.notifyDataSetChanged();
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            String uriString = mImageUri.toString();
            if(servicePhotos.size()==1){
                if(servicePhotos.get(0)==nophotos){

                    servicePhotos.remove(0);

                }
            }

            //servicePhotos.add(new Photo(uriString));
            if(servicePhotos.size()==2){
                if(servicePhotos.get(0)==nophotos){

                    servicePhotos.remove(0);

                }
            }

            notifyPhoto();


        }
    }

    private void buildCategorySpinner() {

        final ArrayList<String> helper = new ArrayList<String>();
        helper.add("Wybierz...");
        for(int i=0; i<categories.size(); i++){
            helper.add(categories.get(i).getName());
        }
        ArrayAdapter<String> CategoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, helper);
        category.setAdapter(CategoriesAdapter);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // First item will be gray
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(EditService.this, android.R.color.darker_gray));
                    buildSubcategorySpinner();
                    subcategory.setEnabled(false);
                } else {
                    ((TextView) view).setTextColor(ContextCompat.getColor(EditService.this, android.R.color.black));
                    if(categories.get(position-1).getSubcategories()!=null){
                        subcategory.setEnabled(true);
                        buildSubcategorySpinner(categories.get(position-1).getSubcategories());
                        if(service.getSubcategory()!=null){
                            List<Subcategory> possibleSubcategories = categories.get(position-1).getSubcategories();
                            subcategory.setSelection(findPositionInSubategorySpinner(possibleSubcategories, service.getSubcategory().getName()));
                        }
                    }
                    else{
                        buildEmptySubcategorySpinner();
                        subcategory.setEnabled(false);
                    }


                }
                setVisibilityInCaseOfCategory(helper.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    private void buildSubcategorySpinner(List<Subcategory> subcategories) {
        ArrayList<String> helper = new ArrayList<String>();
        helper.add("Wybierz...");
        for(int i=0; i<subcategories.size(); i++){
            helper.add(subcategories.get(i).getName());
        }
        ArrayAdapter<String> subcategoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, helper);
        subcategory.setAdapter(subcategoriesAdapter);
        subcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // First item will be gray
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(EditService.this, android.R.color.darker_gray));
                } else {
                    ((TextView) view).setTextColor(ContextCompat.getColor(EditService.this, android.R.color.black));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void buildSubcategorySpinnerAndSetSelection(List<Subcategory> subcategories, int position) {
        ArrayList<String> helper = new ArrayList<String>();
        helper.add("Wybierz...");
        for(int i=0; i<subcategories.size(); i++){
            helper.add(subcategories.get(i).getName());
        }
        ArrayAdapter<String> subcategoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, helper);
        subcategory.setAdapter(subcategoriesAdapter);
        subcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // First item will be gray
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(EditService.this, android.R.color.darker_gray));
                } else {
                    ((TextView) view).setTextColor(ContextCompat.getColor(EditService.this, android.R.color.black));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        subcategory.setSelection(position);
    }

    private void buildEmptySubcategorySpinner() {
        ArrayList<String> helper = new ArrayList<String>();
        helper.add("Brak podkategorii");
        ArrayAdapter<String> subcategoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, helper);
        subcategory.setAdapter(subcategoriesAdapter);
        subcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // First item will be gray
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(EditService.this, android.R.color.darker_gray));
                } else {
                    ((TextView) view).setTextColor(ContextCompat.getColor(EditService.this, android.R.color.black));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void buildSubcategorySpinner() {
        ArrayList<String> helper = new ArrayList<String>();
        helper.add("Wybierz...");
        ArrayAdapter<String> subcategoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, helper);
        subcategory.setAdapter(subcategoriesAdapter);
        subcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // First item will be gray
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(EditService.this, android.R.color.darker_gray));
                } else {
                    ((TextView) view).setTextColor(ContextCompat.getColor(EditService.this, android.R.color.black));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


}
