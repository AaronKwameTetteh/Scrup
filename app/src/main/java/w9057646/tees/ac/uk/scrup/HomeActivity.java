package w9057646.tees.ac.uk.scrup;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 100;
    private static final int CAMERA_REQUEST = 200;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button imageButton;
    private Button saveButton;
    private EditText rcyclaItem;
    private EditText itemQuantity;
    private EditText itemColor;
    private EditText itemSize;
    private ImageView imageUser;

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        this.imageUser = (ImageView) findViewById(R.id.imageUser);

        databaseHandler = new DatabaseHandler(this);
        //byPassActivity();

        List<Item> items = databaseHandler.getAllItems();
        for (Item item : items) {
            Log.d("Home", "onCreate: " + item.getItemColor());



        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopupDialog();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    public void openGallery(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST);

    }

    public void openCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAMERA_REQUEST);
        }
    }



    public void cancel(View view) { finish();

    }


    public void save (View view) {
        Bitmap image = ((BitmapDrawable)imageUser.getDrawable()).getBitmap();

        new DatabaseHandler(this);
        finish();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            try {
                Uri imageuser = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(imageuser);
                imageUser.setImageBitmap(BitmapFactory.decodeStream(imageStream));

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap image = (Bitmap)extras.get("data");
            imageUser.setImageBitmap(image);
        }

    }

    private void byPassActivity() {
        if (databaseHandler.getItemsCount() > 0) {
            startActivity(new Intent(HomeActivity.this, ListActivity.class));
            finish();
        }
    }

    private void saveItem(View view) {
        Item item = new Item();

        String newItem = rcyclaItem.getText().toString().trim();
        String newColor = itemColor.getText().toString().trim();
        int quantity = Integer.parseInt(itemQuantity.getText().toString().trim());
        int size = Integer.parseInt(itemSize.getText().toString().trim());
        String imageUser = imageButton.getText().toString().trim();


        item.setItemName(newItem);
        item.setItemColor(newColor);
        item.setItemQuantity(quantity);
        item.setItemSize(size);


        databaseHandler.addItem(item);

        Snackbar.make(view, "Item Saved", Snackbar.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
              startActivity(new Intent(HomeActivity.this, w9057646.tees.ac.uk.scrup.ListActivity.class));

            }
        }, 1200);


    }
    private void createPopupDialog() {

        builder = new AlertDialog.Builder(this);
        final View view = getLayoutInflater().inflate(R.layout.popup, null);
        rcyclaItem = view.findViewById(R.id.rcyclaItem);
        itemQuantity = view.findViewById(R.id.itemQuantity);
        itemColor = view.findViewById(R.id.itemColor);
        itemSize = view.findViewById(R.id.itemSize);
        imageButton = view.findViewById(R.id.imageButton);
        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!rcyclaItem.getText().toString().isEmpty()
                        && !imageButton.getText().toString().isEmpty()
                        && !itemColor.getText().toString().isEmpty()
                        && !itemQuantity.getText().toString().isEmpty()
                        && !itemSize.getText().toString().isEmpty()) {
                    saveItem(v);
                } else {
                    Snackbar.make(v, "Empty Fields not Allowed", Snackbar.LENGTH_SHORT).show();

                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
                startActivity(intent);


                openCamera(view);

            }


        });




        builder.setView(view);
        dialog = builder.create();
        dialog.show();



    }
}
