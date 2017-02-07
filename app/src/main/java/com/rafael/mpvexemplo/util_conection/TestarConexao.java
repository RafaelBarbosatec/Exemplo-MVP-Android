package com.rafael.mpvexemplo.util_conection;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.rafael.mpvexemplo.R;

public class TestarConexao {

	public static boolean VerificaConexao(Activity contexto, TentarNovamente t){
	      
	    ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Activity.CONNECTIVITY_SERVICE);//Pego a conectividade do contexto o qual o metodo foi chamado
	      
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();//Crio o objeto netInfo que recebe as informacoes da NEtwork  
	      
	      
	    if ( (netInfo != null) && (netInfo.isConnectedOrConnecting()) && (netInfo.isAvailable()) ) //Se o objeto for nulo ou nao tem conectividade retorna false  
	        return true;  
	    else {
			calldialog(contexto,t);
			return false;
		}
	      
	    }

	public static void calldialog(final Activity c,final TentarNovamente t) {

		Snackbar.make(c.findViewById(R.id.lay_main)
				,c.getString(R.string.txt_erro_conexao)
				, BaseTransientBottomBar.LENGTH_INDEFINITE)
				.setAction("TENTAR NOVAMENTE", new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						t.tentarNovamente();
					}
				}).show();
		/*final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);
		alertDialogBuilder.setTitle("");
		alertDialogBuilder.setMessage("Ops, ocorreu algum erro na comunicação, verifique sua conexão e tente novamente.");
		alertDialogBuilder.setPositiveButton("TENTAR NOVAMENTE", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				t.tentarNovamente();
			}
		});


		//AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialogBuilder.show();*/
	}


	/*
	*
	* public static void calldialog(final Activity c) {

		final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);
		alertDialogBuilder.setTitle("Sem conexão com a internet!");
		alertDialogBuilder.setMessage("Favor verificar sua conexão (WIFI/3G)!");
		alertDialogBuilder.setPositiveButton("Concetar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Intent it = new Intent(Settings.ACTION_WIFI_SETTINGS);
				c.startActivity(it);
			}
		});


		alertDialogBuilder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				c.finish();
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}*/
}
