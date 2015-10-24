package com.ghost.bouncingball;

import com.ghost.bouncingball.handler.SQLite;
import com.ghost.bouncingball.load.Preloader;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menue extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading_screen);
		Preloader.load(this);
		SQLite sql = new SQLite(this);
		sql.setUpMyHighscore();
		setContentView(R.layout.activity_menue);

		((Button) findViewById(R.id.startGame)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Menue.this, Game.class));
			}
		});
		((Button) findViewById(R.id.highscore)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//TODO
			}
		});
		((Button) findViewById(R.id.exit)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menue, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
}
