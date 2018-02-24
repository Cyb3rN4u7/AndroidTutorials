package io.github.cyb3rn4u7.imagepicker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int IMG_RESULT = 101 ;
    private ImageView imageView;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.display_image);
        button = findViewById(R.id.pick_img);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, IMG_RESULT);
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == IMG_RESULT && resultCode == RESULT_OK
                    && null != data) {

                // get the selected image
                Uri imageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                // display the image
                imageView.setImageBitmap(bitmap);



            }
        } catch (Exception e) {
            Toast.makeText(this,"oops something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
