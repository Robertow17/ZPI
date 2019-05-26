package com.zpi.serviceProvider.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
import com.zpi.searcher.utils.PageTransformer;
import com.zpi.searcher.utils.ServicesAdapter;
import com.zpi.searcher.utils.ViewPagerAdapter;
import com.zpi.serviceProvider.model.ServiceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zpi.searcher.utils.ItemViewPagerAdapter.EXTRA_POSITION;

public class EditService extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText name, localization, description, email, phone, maxsize;
    private TextView maxsizeText;
    private TextView accommodationText;
    private CheckBox accommodation;
    private Spinner category, subcategory;

    private List<Photo> servicePhotos;
    private Photo nophotos;
    private ViewPagerAdapter viewPagerAdapter;

    List<Category> categories;
    List<Subcategory> subcategories;
    Service service;
    ServiceProvider currentServiceProvider;

    int pos;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_provider_add_service_activity);

        service = getIntent().getParcelableExtra(ServicesAdapter.EXTRA_SERVICE);
        pos = Objects.requireNonNull(getIntent().getExtras()).getInt(EXTRA_POSITION);
        index = service.getId();
        currentServiceProvider = com.zpi.serviceProvider.model.Data.getServiceProvider();

        Data.setSubcategories();
        categories = Data.getCategories();
        subcategories = Data.getSubcategoriesList();

        name = findViewById(R.id.editText);
        localization = findViewById(R.id.editText2);
        description = findViewById(R.id.editText5);
        email = findViewById(R.id.editText3);
        phone = findViewById(R.id.editText4);
        maxsize = findViewById(R.id.editText6);

        TextView title = findViewById(R.id.textView6);
        title.setText("Edytuj usługę");
        accommodationText = findViewById(R.id.textView16);
        maxsizeText = findViewById(R.id.textView15);

        accommodation = findViewById(R.id.checkBox);

        ViewPager viewPager = findViewById(R.id.viewPager);

        subcategory = findViewById(R.id.spinner2);
        category = findViewById(R.id.spinner);

        Button addPhoto = findViewById(R.id.addPhotoButton);
        Button addService = findViewById(R.id.button3);
        addService.setText("Edytuj usługę");

        addService.setOnClickListener(v -> {
            String result = validateDataAndModifyService();
            Toast.makeText(EditService.this, result, Toast.LENGTH_LONG).show();
            if (result.equals("Usługa została pomyślnie zmodyfikowana")) {
                Intent intent = new Intent(EditService.this, ServiceProviderMainActivity.class);
                startActivity(intent);
            }
        });

        int serviceId = service.getId() == 0 ? 6 : service.getId(); // FIXME: as now id is always equal to 0, we mock it to be 6

        viewPagerAdapter = new ViewPagerAdapter(EditService.this, serviceId);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPageTransformer(true, new PageTransformer());

        addPhoto.setOnClickListener(view -> openFileChooser());

        buildCategorySpinner();
        setValuesFromService(service);

    }

    private void setValuesFromService(Service service) {
        name.setText(service.getName());
        localization.setText(service.getLocalization());
        email.setText(service.getEmail());
        phone.setText(service.getPhoneNumber());
        description.setText(service.getDescription());
        int categoryIndex = findPositionInCategorySpinner(categories, service.getCategory().getName());
        category.setSelection(categoryIndex);

        if (service.getSubcategory() != null) {
//            List<Subcategory> possibleSubcategories = categories.get(categoryIndex-1).getSubcategories();
//            subcategory.setSelection(findPositionInSubategorySpinner(possibleSubcategories, service.getSubcategory().getName()));
            //getIndex(subcategory, service.getSubcategory().getName());
        }
        if (service.getWeddingHallDetails() != null) {
            maxsize.setText(Integer.toString(service.getWeddingHallDetails().getMaxNumberOfGuests()));
            accommodation.setChecked(service.getWeddingHallDetails().isCanSleep());
        }
        if (service.getTransportDetails() != null) {
            maxsize.setText(Integer.toString(service.getTransportDetails().getMaxSittingPlaces()));
        }

    }

    private int getIndex(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equals(myString)) {
                return i;
            }
        }

        return 0;
    }

    private int findPositionInCategorySpinner(List<Category> categories, String name) {
        int i = 0;
        for (Category c : categories) {
            i++;
            if (c.getName().equals(name)) {
                return i;
            }
        }
        return 0;
    }

    private int findPositionInSubategorySpinner(List<Subcategory> subcategories, String name) {
        int i = 0;
        for (Subcategory c : subcategories) {
            i++;
            if (c.getName().equals(name)) {
                return i;
            }
        }
        return 0;
    }

    private Category findValidCategory(String name) {
        for (Category c : categories) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    private Subcategory findValidSubcategory(String name) {
        for (Subcategory s : subcategories) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    private String validateDataAndModifyService() {
        String serviceName = name.getText().toString();
        String serviceLocalization = localization.getText().toString();
        String serviceCategory = category.getSelectedItem().toString();
        String serviceSubcategory = subcategory.getSelectedItem().toString();
        String serviceEmail = email.getText().toString();
        String servicePhone = phone.getText().toString();
        String serviceMaxSize = maxsize.getText().toString();
        String serviceDescription = description.getText().toString();
        Boolean serviceAccommodation = accommodation.isChecked();


        String[] values = {serviceName, serviceLocalization, serviceEmail, servicePhone, serviceMaxSize, serviceCategory, serviceSubcategory};
        String[] names = {"Nazwa", "Lokalizacja", "Email", "Telefon", "Liczba miejsc", "Kategoria", "Podkategoria"};
        boolean wrongData = false;


        StringBuilder result = new StringBuilder("Brakuje następujących danych: ");
        for (int i = 0; i < 4; i++) {
            if ((values[i]).equals("")) {
                result.append("\n").append(names[i]);
                wrongData = true;
            }
        }
        if ((values[5]).equals("SALE") || (values[5]).equals("TRANSPORT")) {
            if ((values[4]).equals("")) {
                result.append("\n").append(names[4]);
                wrongData = true;
            }
        }
        for (int j = 5; j < 7; j++) {
            if ((values[j]).equals("Wybierz...")) {
                result.append("\n").append(names[j]);
                wrongData = true;
            }
        }
        if (wrongData) {
            return result.toString();
        } else {
            result = new StringBuilder("Wpisano niepoprawnie: ");
            Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
            Matcher emailMatcher = emailPattern.matcher(values[2]);
            if (!emailMatcher.matches()) {
                result.append("\n").append(names[2]);
                wrongData = true;
            }
            Pattern phonePattern = Pattern.compile("(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)");
            Matcher phoneMatcher = phonePattern.matcher(values[3]);
            if (!phoneMatcher.matches()) {
                result.append("\n").append(names[3]);
                wrongData = true;
            }
            if (!wrongData) {
                Service createdService = addNewService(values, serviceDescription, serviceAccommodation);
                ServerConnector<Service> serverConnector = new ServerConnector<>(ServiceName.services);
                serverConnector.update(index, createdService);
                return "Usługa została pomyślnie zmodyfikowana";
            } else {
                return result.toString();
            }

        }
    }

    private Integer getNumber(String s) {
        try {
            Integer p = Integer.parseInt(s);
            return p;
        } catch (Exception e) {
            return null;
        }
    }

    private Service addNewService(String[] values, String description, boolean accomodation) {
        Service newService;
        switch (values[5]) {
            case "SALE":
                //newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), new WeddingHallDetails(accommodation, getNumber(values[4])), null, servicePhotos);
                newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), new WeddingHallDetails(accomodation, getNumber(values[4])), null, new ArrayList<>());
                break;
            case "TRANSPORT":
                //newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), null, new TransportDetails(getNumber(values[4])), servicePhotos);
                newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), null, new TransportDetails(getNumber(values[4])), new ArrayList<>());
                break;
            default:
                //newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), null, null, servicePhotos);
                newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), null, null, new ArrayList<>());
                break;
        }
        return newService;
    }

//    private void modifyService(String[] values, String description, boolean accommodation){
//        if(values[5].equals("SALE")){
//            currentServiceProvider.getServices().get(pos).editService(values[0], values[1], description, values[3], values[2],findValidSubcategory(values[6]),  findValidCategory(values[5]),  new WeddingHallDetails(accommodation, getNumber(values[4])), null, servicePhotos);
//        }
//        else if(values[5].equals("TRANSPORT")){
//            //newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), null, new TransportDetails(getNumber(values[4])), servicePhotos);
//            newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), null, new TransportDetails(getNumber(values[4])), new ArrayList<>());
//        }
//        else{
//            //newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), null, null, servicePhotos);
//            newService = new Service(values[0], values[1], description, values[3], values[2], findValidCategory(values[5]), findValidSubcategory(values[6]), null, null, new ArrayList<>());
//        }
//        return newService;
//    }

    private void modifyService(String[] values, String description, boolean accomodation){
        if(values[5].equals("SALE")){
            if(service.getWeddingHallDetails()!=null) {
                service.getWeddingHallDetails().setMaxNumberOfGuests(getNumber(values[4]));
                service.getWeddingHallDetails().setCanSleep(accomodation);
                service.editService(values[0], values[1], description, values[3], values[2], findValidSubcategory(values[6]), findValidCategory(values[5]), service.getWeddingHallDetails(), null, servicePhotos);
            }
            else
            {
                service.editService(values[0], values[1], description, values[3], values[2], findValidSubcategory(values[6]), findValidCategory(values[5]), new WeddingHallDetails(accomodation, getNumber(values[4])), null, servicePhotos);
            }
        }
        else if(values[5].equals("TRANSPORT")){
            if(service.getTransportDetails()!=null){
                service.getTransportDetails().setMaxSittingPlaces(getNumber(values[4]));
                service.editService(values[0], values[1], description, values[3], values[2],findValidSubcategory(values[6]), findValidCategory(values[5]), null, service.getTransportDetails(), servicePhotos);

            }
            else{
                service.editService(values[0], values[1], description, values[3], values[2],findValidSubcategory(values[6]), findValidCategory(values[5]), null, new TransportDetails(getNumber(values[4])), servicePhotos);
            }
          }
        else{
            service.editService(values[0], values[1], description, values[3], values[2], findValidSubcategory(values[6]), findValidCategory(values[5]), null, null, servicePhotos);
        }
    }

    private void setVisibilityInCaseOfCategory(String categoryName) {
        switch (categoryName) {
            case "SALE":
                maxsize.setVisibility(View.VISIBLE);
                accommodation.setVisibility(View.VISIBLE);
                accommodationText.setVisibility(View.VISIBLE);
                maxsizeText.setVisibility(View.VISIBLE);
                maxsizeText.setText("Maksymalna liczba gości:");
//            maxsize.setText("");
//            accommodation.setChecked(false);
                break;
            case "TRANSPORT":
                maxsize.setVisibility(View.VISIBLE);
                accommodation.setVisibility(View.INVISIBLE);
                accommodationText.setVisibility(View.INVISIBLE);
                maxsizeText.setVisibility(View.VISIBLE);
                maxsizeText.setText("Maksymalna liczba pasażerów:");
//            maxsize.setText("");
                break;
            default:
                maxsize.setVisibility(View.INVISIBLE);
                accommodation.setVisibility(View.INVISIBLE);
                accommodationText.setVisibility(View.INVISIBLE);
                maxsizeText.setVisibility(View.INVISIBLE);
                break;
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
            Uri mImageUri = data.getData();
            String uriString = mImageUri.toString();
            if (servicePhotos.size() == 1) {
                if (servicePhotos.get(0) == nophotos) {

                    servicePhotos.remove(0);

                }
            }

            //servicePhotos.add(new Photo(uriString));
            if (servicePhotos.size() == 2) {
                if (servicePhotos.get(0) == nophotos) {

                    servicePhotos.remove(0);

                }
            }

            notifyPhoto();


        }
    }

    private void buildCategorySpinner() {

        final ArrayList<String> helper = new ArrayList<String>();
        helper.add("Wybierz...");
        for (int i = 0; i < categories.size(); i++) {
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
                    if (categories.get(position - 1).getSubcategories() != null) {
                        subcategory.setEnabled(true);
                        buildSubcategorySpinner(categories.get(position - 1).getSubcategories());
                        if (service.getSubcategory() != null) {
                            List<Subcategory> possibleSubcategories = categories.get(position - 1).getSubcategories();
                            subcategory.setSelection(findPositionInSubategorySpinner(possibleSubcategories, service.getSubcategory().getName()));
                        }
                    } else {
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
        for (int i = 0; i < subcategories.size(); i++) {
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
        for (int i = 0; i < subcategories.size(); i++) {
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
