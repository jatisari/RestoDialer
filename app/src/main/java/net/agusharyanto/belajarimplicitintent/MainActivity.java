package net.agusharyanto.belajarimplicitintent;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnMcDonald = (Button) findViewById(R.id.btnMcDonald);
		Button btnKFC = (Button) findViewById(R.id.btnKFC);
		Button btnBelajarAndroid = (Button) findViewById(R.id.btnBelajarAndroid);
		Button btnJadwalPuasa = (Button) findViewById(R.id.btnJadwalPuasa);
		Button btnKamera = (Button) findViewById(R.id.btnKamera);
		Button btnMap = (Button) findViewById(R.id.btnMap);
		Button btnReadBarcode = (Button) findViewById(R.id.buttonReadBarcode);


		btnMcDonald.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				//  getRequest(txtResult,txtUrl);
				CallIntent(v);
			}
		});
		btnKFC.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				//  getRequest(txtResult,txtUrl);
				CallIntent(v);
			}
		});
		btnBelajarAndroid.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				//  getRequest(txtResult,txtUrl);
				CallIntent(v);
			}
		});
		btnJadwalPuasa.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				//  getRequest(txtResult,txtUrl);
				CallIntent(v);
			}
		});
		btnKamera.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				//  getRequest(txtResult,txtUrl);
				CallIntent(v);

			}
		});
		btnMap.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				//  getRequest(txtResult,txtUrl);
				CallIntent(v);

			}
		});

		btnReadBarcode.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				//  getRequest(txtResult,txtUrl);
				CallIntent(v);

			}
		});
		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			checkCALLPHONEPermission();
		}
	}


	public void CallIntent(View view) {
		Intent intent = null;
		switch (view.getId()) {
			case R.id.btnKFC:
				//akan melakukan Call ke nomor 14022
				intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:14022"));
				startActivity(intent);
				break;
			case R.id.btnMcDonald:
				//akan melakukan Call ke nomor 14045
				if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
					if (ContextCompat.checkSelfPermission(this,
							Manifest.permission.CALL_PHONE)
							== PackageManager.PERMISSION_GRANTED) {
						intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:14045"));
						startActivity(intent);
					}
				}
				else {
					intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:14045"));
					startActivity(intent);
				}

				break;
			case R.id.btnBelajarAndroid:
				//akan memanggil browser dan menampilkan website http://agusharyanto.net
				intent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://agusharyanto.net"));
				startActivity(intent);
				break;
			case R.id.btnJadwalPuasa:
				//akan memanggil browser dan menampilkan website http://www.pkpu.or.id/imsyak/
				intent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://www.bulog.co.id/"));
				startActivity(intent);
				break;

			case R.id.btnKamera:
				//akan memanggil fungsi Camera android
				intent = new Intent("android.media.action.IMAGE_CAPTURE");
				startActivityForResult(intent, 0);
				break;
			case R.id.btnMap:
				intent = new Intent(android.content.Intent.ACTION_VIEW,
						Uri.parse("http://maps.google.com/maps?saddr=ragunan&daddr=mampang"));
				startActivity(intent);
				break;

			case R.id.buttonReadBarcode:
				try {

					intent = new Intent("com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

					startActivityForResult(intent, 1);

				} catch (Exception e) {

					Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
					Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
					startActivity(marketIntent);

				}
			default:
				break;
		}
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  if (resultCode == Activity.RESULT_OK && requestCode == 0) {
	    String result = data.toURI();
	    Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	  }else
		if (requestCode == 1) {

			if (resultCode == RESULT_OK) {
				String contents = data.getStringExtra("SCAN_RESULT");
				Toast.makeText(getBaseContext(), "Hasil :"+contents, Toast.LENGTH_SHORT).show();
				Log.d("TAG","Hasil : "+contents);
			}
			if(resultCode == RESULT_CANCELED){
				//handle cancel
			}
		}
	}


	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Closing Activity")
				.setMessage("Are you sure you want to close this activity?")
				.setPositiveButton("Yes", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}

				})
				.setNegativeButton("No", null)
				.show();
	}

	public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 99;

	public boolean checkCALLPHONEPermission(){
		if (ContextCompat.checkSelfPermission(this,
				Manifest.permission.CALL_PHONE)
				!= PackageManager.PERMISSION_GRANTED) {

			// Asking user if explanation is needed
			if (ActivityCompat.shouldShowRequestPermissionRationale(this,
					Manifest.permission.CALL_PHONE)) {

				// Show an explanation to the user *asynchronously* -- don't block
				// this thread waiting for the user's response! After the user
				// sees the explanation, try again to request the permission.

				//Prompt the user once explanation has been shown
				ActivityCompat.requestPermissions(this,
						new String[]{Manifest.permission.CALL_PHONE},
						MY_PERMISSIONS_REQUEST_CALL_PHONE);


			} else {
				// No explanation needed, we can request the permission.
				ActivityCompat.requestPermissions(this,
						new String[]{Manifest.permission.CALL_PHONE},
						MY_PERMISSIONS_REQUEST_CALL_PHONE);
			}
			return false;
		} else {
			return true;
		}
	}


	
}